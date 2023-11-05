package hvalfangst.cars.model

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Table
import org.springframework.data.annotation.Id
import java.time.LocalDate

@Entity
@Table(name = "owners")
class Owners(
    @Column(name = "owner_name") @JsonProperty("owner_name") var ownerName: String,
    @Column(name = "contact_info") @JsonProperty("contact_info") var contactInfo: String,
    @Column(name = "date_of_birth") @JsonProperty("date_of_birth")  var dateOfBirth: LocalDate,
    @Column(name = "id") @JsonProperty("id") @Id @GeneratedValue var id: Long? = null
)

