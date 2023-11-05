package hvalfangst.cars.model

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.*
import org.springframework.data.annotation.Id
import java.io.Serializable
import java.time.LocalDate

@Entity
@Table(name = "tires")
data class Tires(

    @Column(name = "car_id")
    @JsonProperty("car_id")
    val carId: Int,

    @Column(name = "brand")
    @JsonProperty("brand")
    val brand: String,

    @Column(name = "size")
    @JsonProperty("size")
    val size: String,

    @Column(name = "manufacturing_date")
    @JsonProperty("manufacturing_date")
    val manufacturingDate: LocalDate,

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("id")
    val id: Long? = null

) : Serializable