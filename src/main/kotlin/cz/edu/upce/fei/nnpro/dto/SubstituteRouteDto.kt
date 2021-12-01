package cz.edu.upce.fei.nnpro.dto

class SubstituteRouteDto(
    var id: Long = Long.MIN_VALUE,
    var name: String,
    var trainRouteId: Long,
    var validated: Boolean = false,
    var minimalCapacity: Int,
    var sections: List<RouteSectionDto> = emptyList()
)