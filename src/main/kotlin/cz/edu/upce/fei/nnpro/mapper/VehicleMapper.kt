package cz.edu.upce.fei.nnpro.mapper

import cz.edu.upce.fei.nnpro.dto.VehicleDto
import cz.edu.upce.fei.nnpro.model.Vehicle
import cz.edu.upce.fei.nnpro.service.TransportCompanyService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class VehicleMapper(
    @Autowired val companyService: TransportCompanyService
) {
    fun toModel(vehicleDto: VehicleDto) = vehicleDto.run {
        val decodedImage = if (image.isNotEmpty()) {
            Base64.getDecoder().decode(image)
        } else null
        Vehicle(id, name, capacity, parameters, companyService.getById(companyId), decodedImage)
    }

    fun toDto(vehicle: Vehicle) = vehicle.run {
        val encodedImage = image?.let { Base64.getEncoder().encodeToString(it); } ?: ""
        VehicleDto(id, name, capacity, parameters, company!!.id, encodedImage)
    }
}