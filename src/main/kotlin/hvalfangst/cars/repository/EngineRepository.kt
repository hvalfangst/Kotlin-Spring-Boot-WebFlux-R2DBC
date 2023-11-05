package hvalfangst.cars.repository

import hvalfangst.cars.model.Engine
import org.springframework.data.r2dbc.repository.Modifying
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.r2dbc.repository.R2dbcRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.LocalDate

@Repository
interface EngineRepository : R2dbcRepository<Engine, Int> {

    @Query("SELECT * FROM engines")
    fun findAllEngines(): Flux<Engine>

    @Query("SELECT * FROM engines WHERE car_id = :car_id")
    fun getEngineByCarId(@Param("car_id") carId: Int): Mono<Engine>

    @Modifying
    @Query("INSERT INTO engines (car_id, type, displacement, horsepower, manufacturing_date) " +
            "VALUES (:car_id, :type, :displacement, :horsepower, :manufacturing_date)")
    fun createEngine(
        @Param("car_id") carId: Int,
        @Param("type") type: String,
        @Param("displacement") displacement: String,
        @Param("horsepower") horsepower: Int,
        @Param("manufacturing_date") manufacturingDate: LocalDate
    ): Mono<Void>


}
