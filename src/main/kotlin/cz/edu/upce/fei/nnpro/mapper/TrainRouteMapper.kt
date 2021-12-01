package cz.edu.upce.fei.nnpro.mapper

import cz.edu.upce.fei.nnpro.dto.RouteSectionDto
import cz.edu.upce.fei.nnpro.dto.TrainRouteDto
import cz.edu.upce.fei.nnpro.model.TrainRoute
import cz.edu.upce.fei.nnpro.service.TrainRouteSectionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TrainRouteMapper(
    @Autowired val trainRouteSectionService: TrainRouteSectionService

) {
    fun toModel(trainRouteDto: TrainRouteDto) = trainRouteDto.run {
        TrainRoute(
            id, trainCode, trainRouteDto.capacity, closure,
            trainRouteDto.sections
                .map { trainRouteSectionService.getByTrainRouteIdAndStationId(trainRouteDto.id, it.stationId) }
        )
    }

    fun toDto(trainRoute: TrainRoute) = trainRoute.run {
        TrainRouteDto(id, trainCode, closure, capacity, sections.map { RouteSectionDto(it.station!!.id, it.routeOrder) })
    }
}