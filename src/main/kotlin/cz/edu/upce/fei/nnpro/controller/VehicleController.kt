package cz.edu.upce.fei.nnpro.controller

import cz.edu.upce.fei.nnpro.dto.ResponseDto
import cz.edu.upce.fei.nnpro.model.Vehicle
import cz.edu.upce.fei.nnpro.service.VehicleService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/vehicle")
class VehicleController(
    @Autowired val vehicleService: VehicleService
) {
    @GetMapping("/{id}")
    fun detail(@PathVariable id: Long): ResponseEntity<Any> {
        return try {
            ResponseEntity.ok(vehicleService.getById(id))
        } catch (e: JpaObjectRetrievalFailureException) {
            ResponseEntity.status(542).body(ResponseDto("Vehicle with id $id not found!"))
        }
    }

    @GetMapping
    fun listAll(): List<Vehicle> = vehicleService.getAll()

    @PostMapping
    fun save(@RequestBody vehicle: Vehicle) = vehicleService.save(vehicle)

    @DeleteMapping
    fun delete(@RequestBody vehicle: Vehicle) = vehicleService.delete(vehicle)

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id: Long) = vehicleService.delete(vehicleService.getById(id))
}
