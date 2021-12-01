package cz.edu.upce.fei.nnpro.service

import cz.edu.upce.fei.nnpro.model.Vehicle
import cz.edu.upce.fei.nnpro.repository.VehicleRepository
import org.springframework.stereotype.Service

@Service
class VehicleService(
    private val vehicleRepository: VehicleRepository
) {
    fun save(vehicle: Vehicle) = vehicleRepository.save(vehicle)

    fun getById(id: Long) = vehicleRepository.getById(id)

    fun getAll(): List<Vehicle> = vehicleRepository.findAll()

    fun delete(vehicle: Vehicle) = vehicleRepository.delete(vehicle)
}
