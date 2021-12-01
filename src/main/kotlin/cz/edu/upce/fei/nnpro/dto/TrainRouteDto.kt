package cz.edu.upce.fei.nnpro.dto

class TrainRouteDto(
    var id: Long = Long.MIN_VALUE,
    var trainCode: String = "",
    var closure: Boolean = false,
    var capacity: Int = 0,
    var sections: List<RouteSectionDto> = emptyList()
)