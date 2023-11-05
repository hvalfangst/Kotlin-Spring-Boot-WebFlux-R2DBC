package hvalfangst.cars.repository

import hvalfangst.cars.model.Engines
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.r2dbc.repository.R2dbcRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
interface EngineRepository : R2dbcRepository<Engines, Long> {
    @Query("SELECT * FROM engines WHERE car_id = :car_id")
    fun findByCarId(@Param("car_id") carId: Long): Mono<Engines>
}
