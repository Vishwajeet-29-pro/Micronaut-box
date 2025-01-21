package com.micronaut.kotlin_impl.repository

import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.time.LocalDate
import java.util.UUID

@Repository
interface CarRepository: JpaRepository<Car, UUID> {
}

@Entity
data class Car(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var id: UUID,
    var carName: String,
    var model: String,
    var make: LocalDate,
    var price: Double,
    var stock: Int
) {

}
