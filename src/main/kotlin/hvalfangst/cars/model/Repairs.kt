package hvalfangst.cars.model
import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.*
import org.springframework.data.annotation.Id
import java.io.Serializable
import java.math.BigDecimal
import java.time.LocalDate

@Entity
@Table(name = "repairs")
data class Repairs(

    @Column(name = "car_id")
    @JsonProperty("car_id")
    val carId: Int,

    @Column(name = "repair_type")
    @JsonProperty("repair_type")
    val repairType: String,

    @Column(name = "repair_date")
    @JsonProperty("repair_date")
    val repairDate: LocalDate,

    @Column(name = "cost")
    @JsonProperty("cost")
    val cost: BigDecimal,

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("id")
    val id: Long? = null

) : Serializable