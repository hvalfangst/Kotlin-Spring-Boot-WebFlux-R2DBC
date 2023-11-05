package hvalfangst.cars.model

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.*
import org.springframework.data.annotation.Id
import java.io.Serializable
import java.time.LocalDate

@Entity
@Table(name = "engines")
data class Engines(

    @Column(name = "car_id")
    @JsonProperty("car_id")
    val carId: Int,

    @Column(name = "type")
    @JsonProperty("type")
    val type: String,

    @Column(name = "displacement")
    @JsonProperty("displacement")
    val displacement: String,

    @Column(name = "horsepower")
    @JsonProperty("horsepower")
    val horsepower: Int,

    @Column(name = "manufacturing_date")
    @JsonProperty("manufacturing_date")
    val manufacturingDate: LocalDate,

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("id")
    val id: Long? = null

) : Serializable