package cz.edu.upce.fei.nnpro.service

import cz.edu.upce.fei.nnpro.model.Report
import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.xssf.usermodel.XSSFSheet
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.io.ByteArrayOutputStream
import java.util.*


@Service
class ReportService(
    @Autowired val vehicleService: VehicleService,
    @Autowired val incidentService: IncidentService,
    @Autowired val substituteRouteService: SubstituteRouteService,
    @Autowired val trainRouteService: TrainRouteService
) {
    fun generateVehicles(): Report {
        val data: MutableMap<String, Array<Any>> = generateVehiclesData()
        return Report("application/xlsx", generateReport(data))
    }

    fun generateIncidentsWithNadCoverage(): Report {
        val data: MutableMap<String, Array<Any>> = generateIncidentsWithNadData()
        return Report("application/xlsx", generateReport(data))
    }

    private fun generateIncidentsWithNadData(): MutableMap<String, Array<Any>> {
        val data: MutableMap<String, Array<Any>> = TreeMap()
        val incidents = incidentService.getAll()

        data["1"] = arrayOf(
            "ID", "NAME", "DESCRIPTION", "SEVERITY", "AFFECTED_RAIL", "START_DATE", "END_DATE",
            "NAD_NAME", "NAD_VALIDITY", "NAD_STATIONS_STOPS"
        )
        var index = 2
        incidents.forEach { incident ->
            val trainRoutesIds = trainRouteService.getAllTrainRoutesWithRail(incident.affectedRail!!).map { it.id }

            val substituteRoutes = substituteRouteService.getByTrainRouteId(trainRoutesIds)

            substituteRoutes.forEach {
                data[(index++).toString()] = it.run {
                    arrayOf(
                        incident.id, incident.name, incident.description, incident.severity.toString(),
                        incident.affectedRail!!.name, incident.startDate.toString(), incident.endDate?.toString() ?: "",
                        name, validated, sections.joinToString(", ") { it.station!!.name }
                    )
                }
            }
        }
        return data
    }


    private fun generateVehiclesData(): MutableMap<String, Array<Any>> {
        val data: MutableMap<String, Array<Any>> = TreeMap()
        val vehicles = vehicleService.getAll()
        data["1"] = arrayOf(
            "ID",
            "NAME",
            "CAPACITY",
            "COLOR",
            "YEAR",
            "ACTUATION",
            "COMPANY_NAME",
            "COMPANY_CODE",
            "COMPANY_CIN"
        )
        vehicles.forEachIndexed { index, vehicle ->
            data[(index + 2).toString()] = vehicle.run {
                arrayOf(id, name, capacity, color, year, actuation, company!!.name, company!!.code, company!!.cin)
            }
        }
        return data
    }

    private fun generateReport(data: MutableMap<String, Array<Any>>): ByteArray {
        val workbook = XSSFWorkbook()
        val sheet = workbook.createSheet("Employee Data")

        fillSheetWithData(data, sheet)

        val bos = ByteArrayOutputStream()

        try {
            workbook.write(bos)
        } finally {
            bos.close()
        }
        return bos.toByteArray()
    }

    private fun fillSheetWithData(data: MutableMap<String, Array<Any>>, sheet: XSSFSheet) {
        val keySet: Set<String> = data.keys
        for ((rowNum, key) in keySet.withIndex()) {
            val row: Row = sheet.createRow(rowNum)
            val objArr = data[key]!!
            var cellnum = 0
            for (obj in objArr) {
                val cell: Cell = row.createCell(cellnum++)
                if (obj is String)
                    cell.setCellValue(obj)
                else if (obj is Number)
                    cell.setCellValue(obj.toDouble())
                else if (obj is Boolean)
                    cell.setCellValue(if (obj) "True" else "False")
            }
        }
    }
}
