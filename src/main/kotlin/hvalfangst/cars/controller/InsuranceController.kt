package hvalfangst.cars.controller

import hvalfangst.cars.model.Insurance
import hvalfangst.cars.model.requests.UpsertInsuranceRequest
import hvalfangst.cars.repository.InsuranceRepository
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/insurance")
class InsuranceController(private val insuranceRepository: InsuranceRepository) {

    @GetMapping
    fun findAll(): Flux<Insurance> {
        return insuranceRepository.findAllInsurances()
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody request: UpsertInsuranceRequest): Mono<Void> {
        return insuranceRepository.createInsurance(
            request.carId,
            request.policyNumber,
            request.provider,
            request.startDate,
            request.endDate
        )
    }
}
