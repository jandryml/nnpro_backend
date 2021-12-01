package cz.edu.upce.fei.nnpro.repository

import cz.edu.upce.fei.nnpro.model.TrainRouteSection
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query

interface TrainRouteSectionRepository : JpaRepository<TrainRouteSection, Long> {
    @Query(value = "SELECT * FROM train_route_section WHERE station_id = ?1 AND train_route_id = ?2", nativeQuery = true)
    fun getByTrainRouteIdAndStationId(stationId: Long, trainRouteId: Long): TrainRouteSection

    @Modifying
    @Query(value = "DELETE FROM train_route_section WHERE train_route_id = ?1", nativeQuery = true)
    fun deleteAllByRouteId(trainRouteId: Long)
}
