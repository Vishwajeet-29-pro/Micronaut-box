package com.micronaut.kotlin_impl.service

import com.micronaut.kotlin_impl.CarService
import com.micronaut.kotlin_impl.dto.CarRequest
import com.micronaut.kotlin_impl.repository.Car
import com.micronaut.kotlin_impl.repository.CarRepository
import io.micronaut.context.annotation.Replaces
import io.micronaut.test.annotation.MockBean
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import java.time.LocalDate
import java.util.*

@MicronautTest(startApplication = false)
class CarServiceTest(
    @Inject private val carService: CarService,
    @Inject private val carRepository: CarRepository,
) {
    @MockBean(CarRepository::class)
    @Replaces(CarRepository::class)
    fun mockCarRepository(): CarRepository {
        return Mockito.mock(CarRepository::class.java)
    }

    private lateinit var car: Car
    private lateinit var carRequest: CarRequest

    @BeforeEach
    fun setup() {
        car = Car(UUID.randomUUID(), "Honda", "Amaze", LocalDate.of(2025,1,1), 1100000.0, 1000)
        carRequest = CarRequest("Honda", "Amaze", LocalDate.of(2025,1, 1), 1100000.0, 1000)
    }

    @Test
    fun `test true`() {
        assertTrue(true)
    }
}