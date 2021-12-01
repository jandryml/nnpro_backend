package cz.edu.upce.fei.nnpro.service

import cz.edu.upce.fei.nnpro.dto.RouteSectioDto
import cz.edu.upce.fei.nnpro.model.Rail
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

    fun validateStationSequence(routeSections: List<RouteSectioDto>): Boolean {
        routeSections.forEachIndexed { idx, it ->
            it.order = idx
            if (routeSections.lastIndex == idx) return@forEachIndexed
            getRailBetween(it.stationId, routeSections[idx + 1].stationId) ?: return false
        }
        return true
    }
}
