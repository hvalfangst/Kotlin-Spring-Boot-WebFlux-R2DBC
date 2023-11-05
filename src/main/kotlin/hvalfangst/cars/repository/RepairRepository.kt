package hvalfangst.cars.repository

import hvalfangst.cars.model.Repair
import org.springframework.data.r2dbc.repository.Modifying
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.r2dbc.repository.R2dbcRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.math.BigDecimal
import java.time.LocalDate

@Repository
interface RepairRepository : R2dbcRepository<Repair, Int> {

    @Query("SELECT * FROM repairs")
    fun findAllRepairs(): Flux<Repair>

    @Query("SELECT * FROM repairs WHERE car_id = :car_id")
    fun getRepairsByCarId(@Param("car_id") carId: Int): Flux<Repair>

    @Modifying
    @Query("INSERT INTO repairs (car_id, repair_type, repair_date, cost) " +
            "VALUES (:car_id, :repair_type, :repair_date, :cost)")
    fun createRepair(
        @Param("car_id") carId: Int,
        @Param("repair_type") repairType: String,
        @Param("repair_date") repairDate: LocalDate,
        @Param("cost") cost: BigDecimal
    ): Mono<Void>
}
