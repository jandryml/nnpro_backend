package cz.edu.upce.fei.nnpro.repository

import cz.edu.upce.fei.nnpro.model.SubstituteRouteSection
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query

interface SubstituteRouteSectionRepository : JpaRepository<SubstituteRouteSection, Long> {
    @Query(value = "SELECT * FROM substitute_route_section WHERE station_id = ?1 AND substitute_route_id = ?2", nativeQuery = true)
    fun getByTrainRouteIdAndStationId(stationId: Long, substituteRouteId: Long): SubstituteRouteSection

    @Modifying
    @Query(value = "DELETE FROM substitute_route_section WHERE substitute_route_id = ?1", nativeQuery = true)
    fun deleteAllByRouteId(substituteRouteId: Long)
}
