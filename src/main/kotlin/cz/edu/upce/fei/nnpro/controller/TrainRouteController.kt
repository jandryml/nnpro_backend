package cz.edu.upce.fei.nnpro.controller

import cz.edu.upce.fei.nnpro.dto.ResponseDto
import cz.edu.upce.fei.nnpro.dto.TrainRouteDto
import cz.edu.upce.fei.nnpro.mapper.TrainRouteMapper
import cz.edu.upce.fei.nnpro.model.TrainRoute
import cz.edu.upce.fei.nnpro.service.SubstituteRouteService
import cz.edu.upce.fei.nnpro.service.TrainRouteService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/train-route")
class TrainRouteController(
    @Autowired val trainRouteService: TrainRouteService,
    @Autowired val substituteRouteService: SubstituteRouteService,
    @Autowired val trainRouteMapper: TrainRouteMapper
) {
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_USER')")
    fun detail(@PathVariable id: Long) =
        trainRouteService.getById(id)
            ?.let { ResponseEntity.ok(trainRouteMapper.toDto(it)) }
            ?: ResponseEntity.status(542).body(ResponseDto("Train route with id $id not found!"))


    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_USER')")
    fun listAll(): List<TrainRoute> = trainRouteService.getAll()

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    fun save(@RequestBody trainRoute: TrainRouteDto) =
        trainRouteService.save(trainRoute) ?: ResponseEntity.status(526)
            .body(ResponseDto("Station sequence is not valid! Check that rails between stations exists!"))

    @PostMapping("/generate-sub-route/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    fun generateSubRouteManually(@PathVariable id: Long) =
        trainRouteService.getById(id)
            ?.let { substituteRouteService.createSubstituteRoute(it) }
            ?: ResponseEntity.status(563).body(ResponseDto("Train route wit $id doesn't exists!"))

    @DeleteMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    fun delete(@RequestBody trainRoute: TrainRoute) = trainRouteService.delete(trainRoute)

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    fun deleteById(@PathVariable id: Long) = trainRouteService.getById(id)?.let { trainRouteService.delete(it) }
}
