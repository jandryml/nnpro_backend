package cz.edu.upce.fei.nnpro.service

import cz.edu.upce.fei.nnpro.model.Incident
import cz.edu.upce.fei.nnpro.model.Rail
import cz.edu.upce.fei.nnpro.repository.IncidentRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
class IncidentService(
    @Autowired private val incidentRepository: IncidentRepository,
    @Autowired private val userService: UserService,
    @Autowired private val trainRouteService: TrainRouteService,
    @Autowired private val substituteRouteService: SubstituteRouteService
) {
    fun save(incident: Incident): Incident? {
        incident.reportedBy = userService.getByUsername(SecurityContextHolder.getContext().authentication.name)
        return incident.affectedRail?.let {
            triggerCreationOfSubRoutes(it)
            incidentRepository.save(incident)
        }
    }

    private fun triggerCreationOfSubRoutes(it: Rail) {
        trainRouteService.getAllTrainRoutesWithRail(it).forEach {
            if (substituteRouteService.getByTrainRouteId(listOf(it.id)).isEmpty()) {
                substituteRouteService.createSubstituteRoute(it)
            }
        }
    }

    fun getById(id: Long) = incidentRepository.getById(id)

    fun getAll(): List<Incident> = incidentRepository.findAll()

    fun delete(incident: Incident) = incidentRepository.delete(incident)

    fun deleteById(id: Long) = incidentRepository.deleteById(id)
}
