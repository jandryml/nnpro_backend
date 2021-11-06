package cz.edu.upce.fei.nnpro.service

import cz.edu.upce.fei.nnpro.model.TrainRoute
import cz.edu.upce.fei.nnpro.repository.TrainRouteRepository
import org.springframework.stereotype.Service

@Service
class TrainRouteService(
    private val trainRouteRepository: TrainRouteRepository,
    private val railService: RailService
) {
    fun save(trainRoute: TrainRoute): TrainRoute {
        trainRoute.sections.forEachIndexed { idx, it ->
            if (trainRoute.sections.lastIndex == idx) return@forEachIndexed
            railService.getRailBetween(it.station!!.id, trainRoute.sections[idx].id)
        }
        return trainRouteRepository.save(trainRoute)
    }

    fun getById(id: Long) = trainRouteRepository.getById(id)

    fun getAll(): List<TrainRoute> = trainRouteRepository.findAll()

    fun delete(trainRoute: TrainRoute) = trainRouteRepository.delete(trainRoute)
}
