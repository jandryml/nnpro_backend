package cz.edu.upce.fei.nnpro.dto

class VehicleDto(
    var id: Long = Long.MIN_VALUE,
    var name: String,
    var capacity: Int,
    var color: String,
    var year: Long,
    var actuation: String,
    var companyId: Long,
    var image: String = "",
    var substituteRouteId: Long? = null
)
