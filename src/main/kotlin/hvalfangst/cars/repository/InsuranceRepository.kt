package hvalfangst.cars.repository

import hvalfangst.cars.model.Insurance
import org.springframework.data.r2dbc.repository.Modifying
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.r2dbc.repository.R2dbcRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.LocalDate

@Repository
interface InsuranceRepository : R2dbcRepository<Insurance, Int> {

    @Query("SELECT * FROM insurance")
    fun findAllInsurances(): Flux<Insurance>

    @Query("SELECT * FROM insurance WHERE car_id = :car_id")
    fun getInsurancesByCarId(@Param("car_id") carId: Int): Flux<Insurance>

    @Modifying
    @Query("INSERT INTO insurance (car_id, policy_number, provider, start_date, end_date) " +
            "VALUES (:car_id, :policy_number, :provider, :start_date, :end_date)")
    fun createInsurance(
        @Param("car_id") carId: Int,
        @Param("policy_number") policyNumber: String,
        @Param("provider") provider: String,
        @Param("start_date") startDate: LocalDate,
        @Param("end_date") endDate: LocalDate
    ): Mono<Void>
}
