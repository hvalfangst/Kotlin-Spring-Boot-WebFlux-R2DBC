package hvalfangst.cars.model

import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable
import java.time.LocalDate

data class Owner(
    @JsonProperty("id")
    val id: Int,

    @JsonProperty("owner_name")
    val ownerName: String,

    @JsonProperty("contact_info")
    val contactInfo: String,

    @JsonProperty("date_of_birth")
    val dateOfBirth: LocalDate
) : Serializable