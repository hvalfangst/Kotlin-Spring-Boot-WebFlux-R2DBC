package hvalfangst.cars.controller

import hvalfangst.cars.model.Insurance
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
        return insuranceRepository.findAll()
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): Mono<Insurance> {
        return insuranceRepository.findById(id)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody request: Insurance): Mono<Insurance> {
        return insuranceRepository.save(request)
    }
}
