package hvalfangst.cars.repository

import hvalfangst.cars.model.Owner
import org.springframework.data.r2dbc.repository.Modifying
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.r2dbc.repository.R2dbcRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.LocalDate

@Repository
interface OwnerRepository : R2dbcRepository<Owner, Int> {

    @Query("SELECT * FROM owners")
    fun findAllOwners(): Flux<Owner>

    @Query("SELECT * FROM owners WHERE id = :id")
    fun getOwnerById(@Param("id") id: Int): Mono<Owner>

    @Modifying
    @Query("INSERT INTO owners (owner_name, contact_info, date_of_birth) " +
            "VALUES (:owner_name, :contact_info, :date_of_birth)")
    fun createOwner(
        @Param("owner_name") ownerName: String,
        @Param("contact_info") contactInfo: String,
        @Param("date_of_birth") dateOfBirth: LocalDate
    ): Mono<Void>
}
