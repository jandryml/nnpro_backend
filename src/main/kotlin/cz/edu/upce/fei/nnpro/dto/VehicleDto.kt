package cz.edu.upce.fei.nnpro.dto

class VehicleDto(
    var id: Long = Long.MIN_VALUE,
    var name: String,
    var capacity: Int,
    var parameters: String,
    var companyId: Long,
    var image: String = ""
)