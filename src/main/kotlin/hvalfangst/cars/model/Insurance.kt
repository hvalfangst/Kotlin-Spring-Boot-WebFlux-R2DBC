package hvalfangst.cars.model

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.*
import org.springframework.data.annotation.Id
import java.io.Serializable
import java.time.LocalDate

@Entity
@Table(name = "insurance")
data class Insurance(

    @Column(name = "car_id")
    @JsonProperty("car_id")
    val carId: Int,

    @Column(name = "policy_number")
    @JsonProperty("policy_number")
    val policyNumber: String,

    @Column(name = "provider")
    @JsonProperty("provider")
    val provider: String,

    @Column(name = "start_date")
    @JsonProperty("start_date")
    val startDate: LocalDate,

    @Column(name = "end_date")
    @JsonProperty("end_date")
    val endDate: LocalDate,

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("id")
    val id: Long? = null

) : Serializable
