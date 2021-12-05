package cz.edu.upce.fei.nnpro.service

import cz.edu.upce.fei.nnpro.dto.RouteSectionDto
import cz.edu.upce.fei.nnpro.dto.SubstituteRouteDto
import cz.edu.upce.fei.nnpro.model.*
import cz.edu.upce.fei.nnpro.repository.SubstituteRouteRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class SubstituteRouteService(
    @Autowired private val substituteRouteRepository: SubstituteRouteRepository,
    @Autowired private val substituteRouteSectionService: SubstituteRouteSectionService,
    @Autowired private val railService: RailService,
    @Autowired private val vehicleService: VehicleService
) {

    fun validateStationSequence(substituteRouteDto: SubstituteRouteDto) =
        railService.validateStationSequence(substituteRouteDto.sections)

    fun validateVehiclesCapacity(substituteRouteDto: SubstituteRouteDto) =
        vehicleService.validateBusesToTrainRouteCapacity(substituteRouteDto.trainRouteId, substituteRouteDto.vehicleIds)

    fun validateVehicleAvailability(substituteRouteDto: SubstituteRouteDto) =
        vehicleService.validateVehicleAvailability(substituteRouteDto)

    fun save(substituteRouteDto: SubstituteRouteDto): SubstituteRoute {
        substituteRouteSectionService.deleteAllByRouteId(substituteRouteDto.id)
        return substituteRouteRepository.save(substituteRouteDto.run {
            SubstituteRoute(
                id, name, concernedTrainRoute = TrainRoute(trainRouteId), validated,
                sections.map { SubstituteRouteSection(station = Station(it.stationId), routeOrder = it.order) })
        }).also { subRoute ->
            vehicleService.removeReferencesToSubRoute(subRoute.id)
            substituteRouteDto.vehicleIds.forEach {
                vehicleService.save(vehicleService.getById(it).apply { this.substituteRoute = subRoute })
            }
        }
    }

    fun createSubstituteRoute(trainRoute: TrainRoute) {
        save(SubstituteRouteDto(
            name = "Sub route for ${trainRoute.trainCode}",
            minimalCapacity = trainRoute.capacity, trainRouteId = trainRoute.id,
            sections = trainRoute.sections.map { RouteSectionDto(it.station!!.id, it.routeOrder) }
        ))
    }

    fun getById(id: Long) = substituteRouteRepository.getById(id)

    fun getByTrainRouteId(ids:List<Long>) = substituteRouteRepository.findByTrainRouteIds(ids)

    fun getByIncident(incident: Incident) {
        incident.affectedRail
    }

    fun getAll(): List<SubstituteRoute> = substituteRouteRepository.findAll()

    fun delete(substituteRoute: SubstituteRoute) {
        vehicleService.removeReferencesToSubRoute(substituteRoute.id)
        substituteRouteRepository.delete(substituteRoute)
    }
}
