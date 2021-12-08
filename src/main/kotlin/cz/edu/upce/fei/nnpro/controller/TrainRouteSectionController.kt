package cz.edu.upce.fei.nnpro.controller

import cz.edu.upce.fei.nnpro.dto.ResponseDto
import cz.edu.upce.fei.nnpro.model.TrainRouteSection
import cz.edu.upce.fei.nnpro.service.TrainRouteSectionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/train-route-section")
class TrainRouteSectionController(
    @Autowired val trainRouteSectionService: TrainRouteSectionService
) {
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_USER')")
    fun detail(@PathVariable id: Long): ResponseEntity<Any> {
        return try {
            ResponseEntity.ok(trainRouteSectionService.getById(id))
        } catch (e: JpaObjectRetrievalFailureException) {
            ResponseEntity.status(542).body(ResponseDto("Train route with id $id not found!"))
        }
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_USER')")
    fun listAll(): List<TrainRouteSection> = trainRouteSectionService.getAll()

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    fun save(@RequestBody trainRouteSection: TrainRouteSection) = trainRouteSectionService.save(trainRouteSection)

    @DeleteMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    fun delete(@RequestBody trainRouteSection: TrainRouteSection) = trainRouteSectionService.delete(trainRouteSection)

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    fun deleteById(@PathVariable id: Long) = trainRouteSectionService.delete(trainRouteSectionService.getById(id))
}
