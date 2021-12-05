package cz.edu.upce.fei.nnpro.controller

import cz.edu.upce.fei.nnpro.model.Report
import cz.edu.upce.fei.nnpro.service.ReportService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/report")
class ReportController(
    @Autowired val reportService: ReportService
) {

    @GetMapping("/chauffeurs")
    fun getChauffeursReport(): ResponseEntity<ByteArray> {
        val fileEntity: Report = reportService.generateChauffeur()
        val header = HttpHeaders()
        header.contentType = MediaType.valueOf(fileEntity.contentType)
        header.contentLength = fileEntity.data.size.toLong()
        return ResponseEntity<ByteArray>(fileEntity.data, header, HttpStatus.OK)
    }


    @GetMapping("/incidents")
    fun getIncidentReport(): ResponseEntity<ByteArray> {
        val fileEntity: Report = reportService.generateIncidentsWithNadCoverage()
        val header = HttpHeaders()
        header.contentType = MediaType.valueOf(fileEntity.contentType)
        header.contentLength = fileEntity.data.size.toLong()
        return ResponseEntity<ByteArray>(fileEntity.data, header, HttpStatus.OK)
    }
}