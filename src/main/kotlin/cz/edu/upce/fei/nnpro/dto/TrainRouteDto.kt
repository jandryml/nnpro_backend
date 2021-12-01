package cz.edu.upce.fei.nnpro.dto

class TrainRouteDto(
    var id: Long = Long.MIN_VALUE,
    var trainCode: String = "",
    var closure: Boolean = false,
    var capacity: Long = 0,
    var sections: List<RouteSectioDto> = emptyList()
)