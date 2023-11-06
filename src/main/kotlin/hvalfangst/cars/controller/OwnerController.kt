package hvalfangst.cars.controller

import hvalfangst.cars.model.Owners
import hvalfangst.cars.repository.OwnerRepository
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/owners")
class OwnerController(private val ownerRepository: OwnerRepository) {

    @GetMapping
    fun findAll(): Flux<Owners> {
        return ownerRepository.findAll()
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): Mono<Owners> {
        return ownerRepository.findById(id)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody request: Owners): Mono<Owners> {
           return ownerRepository.save(request)
    }
}