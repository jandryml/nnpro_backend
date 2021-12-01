package cz.edu.upce.fei.nnpro.service

import cz.edu.upce.fei.nnpro.model.TrainRouteSection
import cz.edu.upce.fei.nnpro.repository.TrainRouteSectionRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class TrainRouteSectionService(
    private val trainRouteSectionRepository: TrainRouteSectionRepository
) {
    fun save(trainRouteSection: TrainRouteSection) = trainRouteSectionRepository.save(trainRouteSection)

    fun getById(id: Long) = trainRouteSectionRepository.getById(id)

    fun getByTrainRouteIdAndStationId(stationId: Long, trainRouteId: Long) =
        trainRouteSectionRepository.getByTrainRouteIdAndStationId(stationId, trainRouteId)

    fun getAll(): List<TrainRouteSection> = trainRouteSectionRepository.findAll()

    fun delete(trainRouteSection: TrainRouteSection) = trainRouteSectionRepository.delete(trainRouteSection)

    @Transactional
    fun deleteAllByRouteId(trainRouteId: Long) = trainRouteSectionRepository.deleteAllByRouteId(trainRouteId)
}
