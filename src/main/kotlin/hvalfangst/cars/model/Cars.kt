package hvalfangst.cars.model

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.*
import org.springframework.data.annotation.Id
import java.io.Serializable
import java.time.LocalDate

@Entity
@Table(name = "cars")
data class Cars(

    @Column(name = "make")
    @JsonProperty("make")
    val make: String,

    @Column(name = "model")
    @JsonProperty("model")
    val model: String,

    @Column(name = "year")
    @JsonProperty("year")
    val year: Int,

    @Column(name = "vin")
    @JsonProperty("vin")
    val vin: String,

    @Column(name = "color")
    @JsonProperty("color")
    val color: String,

    @Column(name = "purchase_date")
    @JsonProperty("purchase_date")
    val purchaseDate: LocalDate,

    @Column(name = "owner_id")
    @JsonProperty("owner_id")
    val ownerId: Int,

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("id")
    val id: Long? = null

) : Serializable
