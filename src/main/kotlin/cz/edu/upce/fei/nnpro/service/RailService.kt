package cz.edu.upce.fei.nnpro.service

import cz.edu.upce.fei.nnpro.dto.RouteSectionDto
import cz.edu.upce.fei.nnpro.model.Rail
import cz.edu.upce.fei.nnpro.model.TrainRouteSection
import cz.edu.upce.fei.nnpro.repository.RailRepository
import org.springframework.stereotype.Service

@Service
class RailService(
    private val railRepository: RailRepository
) {
    fun save(rail: Rail) = railRepository.save(rail)

    fun getById(id: Long) = railRepository.getById(id)

    fun getAll(): List<Rail> = railRepository.findAll()

    fun delete(rail: Rail) = railRepository.delete(rail)

    fun deleteById(id: Long) = railRepository.deleteById(id)

    fun getRailBetween(sourceStationId: Long, targetStationId: Long) =
        railRepository.getRailBetween(sourceStationId, targetStationId)

    fun validateStationSequence(routeSections: List<RouteSectionDto>): Boolean {
        routeSections.forEachIndexed { idx, it ->
            it.order = idx
            if (routeSections.lastIndex == idx) return@forEachIndexed
            getRailBetween(it.stationId, routeSections[idx + 1].stationId) ?: return false
        }
        return true
    }

    fun isRailInTheStationSequence(rail: Rail, routeSections: List<TrainRouteSection>): Boolean {
        routeSections.forEachIndexed { idx, it ->
            if (routeSections.lastIndex == idx) return@forEachIndexed
            if (getRailBetween(it.station!!.id, routeSections[idx + 1].station!!.id)?.id == rail.id) return true
        }
        return false
    }
}
