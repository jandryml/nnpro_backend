package cz.edu.upce.fei.nnpro.repository

import cz.edu.upce.fei.nnpro.model.Rail
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface RailRepository : JpaRepository<Rail, Long> {
    @Query(value = "select * from rail where " +
            "(source_station_id = :sourceStationId AND rail.target_station_id = :targetStationId) OR " +
            "(source_station_id = :targetStationId AND rail.target_station_id = :sourceStationId);", nativeQuery = true)
    fun getRailBetween(sourceStationId: Long, targetStationId: Long): Rail?
}
