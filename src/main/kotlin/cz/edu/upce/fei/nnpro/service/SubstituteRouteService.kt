package cz.edu.upce.fei.nnpro.service

import cz.edu.upce.fei.nnpro.dto.SubstituteRouteDto
import cz.edu.upce.fei.nnpro.model.*
import cz.edu.upce.fei.nnpro.repository.SubstituteRouteRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class SubstituteRouteService(
    @Autowired private val substituteRouteRepository: SubstituteRouteRepository,
    @Autowired private val substituteRouteSectionService: SubstituteRouteSectionService,
    @Autowired private val railService: RailService
) {
    fun save(substituteRouteDto: SubstituteRouteDto): SubstituteRoute? {
        return if (railService.validateStationSequence(substituteRouteDto.sections)) {
            substituteRouteSectionService.deleteAllByRouteId(substituteRouteDto.id)
            substituteRouteRepository.save(substituteRouteDto.run {
                SubstituteRoute(
                    id, name, concernedTrainRoute = TrainRoute(trainRouteId), validated,
                    sections.map { SubstituteRouteSection(station = Station(it.stationId), routeOrder = it.order) })
            })
        } else null
    }


    fun getById(id: Long) = substituteRouteRepository.getById(id)

    fun getAll(): List<SubstituteRoute> = substituteRouteRepository.findAll()

    fun delete(substituteRoute: SubstituteRoute) = substituteRouteRepository.delete(substituteRoute)
}
