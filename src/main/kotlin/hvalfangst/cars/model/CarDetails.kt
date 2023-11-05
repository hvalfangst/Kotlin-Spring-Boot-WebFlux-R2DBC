package hvalfangst.cars.model
import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable

data class CarDetails(
    @JsonProperty("car")
    val cars: Cars,
    @JsonProperty("owner")
    val owners: Owners,
    @JsonProperty("tires")
    val tires: List<Tires>,
    @JsonProperty("insurances")
    val insurance: List<Insurance>,
    @JsonProperty("repairs")
    val repairs: List<Repairs>,
    @JsonProperty("engine")
    val engines: Engines
): Serializable
