package cz.edu.upce.fei.nnpro.mapper

import cz.edu.upce.fei.nnpro.dto.RouteSectionDto
import cz.edu.upce.fei.nnpro.dto.SubstituteRouteDto
import cz.edu.upce.fei.nnpro.model.SubstituteRoute
import cz.edu.upce.fei.nnpro.service.SubstituteRouteSectionService
import cz.edu.upce.fei.nnpro.service.TrainRouteService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class SubstituteRouteMapper(
    @Autowired val substituteRouteSectionService: SubstituteRouteSectionService,
    @Autowired val trainRouteService: TrainRouteService
) {
    fun toModel(substituteRouteDto: SubstituteRouteDto) = substituteRouteDto.run {
        SubstituteRoute(
            id, name, trainRouteService.getById(trainRouteId), validated,
            sections
                .map { substituteRouteSectionService.getBySubstituteRouteIdAndStationId(id, it.stationId) }
        )
    }

    fun toDto(substituteRoute: SubstituteRoute) = substituteRoute.run {
        SubstituteRouteDto(id, name, concernedTrainRoute!!.id, validated, concernedTrainRoute.capacity,
            sections.map { RouteSectionDto(it.station!!.id, it.routeOrder) })
    }
}