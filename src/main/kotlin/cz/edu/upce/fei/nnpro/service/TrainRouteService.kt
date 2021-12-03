package cz.edu.upce.fei.nnpro.service

import cz.edu.upce.fei.nnpro.dto.TrainRouteDto
import cz.edu.upce.fei.nnpro.model.Station
import cz.edu.upce.fei.nnpro.model.TrainRoute
import cz.edu.upce.fei.nnpro.model.TrainRouteSection
import cz.edu.upce.fei.nnpro.repository.TrainRouteRepository
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException
import org.springframework.stereotype.Service

@Service
class TrainRouteService(
    private val trainRouteRepository: TrainRouteRepository,
    private val railService: RailService,
    private val trainRouteSectionService: TrainRouteSectionService
) {
    fun save(trainRouteDto: TrainRouteDto): TrainRoute? {
        return if (railService.validateStationSequence(trainRouteDto.sections)) {
            trainRouteSectionService.deleteAllByRouteId(trainRouteDto.id)
            trainRouteRepository.save(trainRouteDto.run {
                TrainRoute(
                    id, trainCode, capacity, closure,
                    sections.map { TrainRouteSection(station = Station(it.stationId), routeOrder = it.order) })
            })
        } else null
    }

    fun getById(id: Long): TrainRoute? {
        return try {
            trainRouteRepository.getById(id)
        } catch (e: JpaObjectRetrievalFailureException) {
            null
        }
    }

    fun getAll(): List<TrainRoute> = trainRouteRepository.findAll()

    fun delete(trainRoute: TrainRoute) = trainRouteRepository.delete(trainRoute)
}
