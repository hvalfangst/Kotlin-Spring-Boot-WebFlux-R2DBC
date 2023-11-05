package hvalfangst.cars.controller

import hvalfangst.cars.model.Repairs
import hvalfangst.cars.repository.RepairRepository
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/repairs")
class RepairController(private val repairRepository: RepairRepository) {

    @GetMapping
    fun findAll(): Flux<Repairs> {
        return repairRepository.findAll()
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): Mono<Repairs> {
        return repairRepository.findById(id)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody request: Repairs): Mono<Repairs> {
        return repairRepository.save(request)
    }
}
