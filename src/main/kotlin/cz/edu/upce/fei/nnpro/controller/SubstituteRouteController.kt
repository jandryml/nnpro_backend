package cz.edu.upce.fei.nnpro.controller

import cz.edu.upce.fei.nnpro.dto.ResponseDto
import cz.edu.upce.fei.nnpro.dto.SubstituteRouteDto
import cz.edu.upce.fei.nnpro.mapper.SubstituteRouteMapper
import cz.edu.upce.fei.nnpro.model.SubstituteRoute
import cz.edu.upce.fei.nnpro.service.SubstituteRouteService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/substitute-route")
class SubstituteRouteController(
    @Autowired val substituteRouteService: SubstituteRouteService,
    @Autowired val substituteRouteMapper: SubstituteRouteMapper
) {
    @GetMapping("/{id}")
    fun detail(@PathVariable id: Long): ResponseEntity<Any> {
        return try {
            ResponseEntity.ok(substituteRouteMapper.toDto(substituteRouteService.getById(id)))
        } catch (e: JpaObjectRetrievalFailureException) {
            ResponseEntity.status(542).body(ResponseDto("Substitute route with id $id not found!"))
        }
    }

    @GetMapping
    fun listAll(): List<SubstituteRoute> = substituteRouteService.getAll()

    @PostMapping
    fun save(@RequestBody substituteRouteDto: SubstituteRouteDto): ResponseEntity<Any> {
        return if (!substituteRouteService.validateVehiclesCapacity(substituteRouteDto)) {
            ResponseEntity.status(526)
                .body(ResponseDto("Vehicles capacity is less than needed!"))
        } else if (!substituteRouteService.validateVehicleAvailability(substituteRouteDto)) {
            ResponseEntity.status(526)
                .body(ResponseDto("Some of vehicles are already allocated to different sub route!"))
        } else {
            ResponseEntity.ok(substituteRouteService.save(substituteRouteDto).let(substituteRouteMapper::toDto))
        }
    }

    @DeleteMapping
    fun delete(@RequestBody substituteRoute: SubstituteRoute) = substituteRouteService.delete(substituteRoute)

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id: Long) = substituteRouteService.delete(substituteRouteService.getById(id))
}
