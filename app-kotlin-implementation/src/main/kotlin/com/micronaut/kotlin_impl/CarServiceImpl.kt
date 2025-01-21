package com.micronaut.kotlin_impl

import com.micronaut.kotlin_impl.dto.CarRequest
import com.micronaut.kotlin_impl.dto.CarResponse
import com.micronaut.kotlin_impl.repository.CarRepository
import jakarta.inject.Singleton
import java.util.*

@Singleton
class CarServiceImpl(
    private val carRepository: CarRepository
) : CarService {
    override fun addCarDetails(carRequest: CarRequest): CarResponse {
        TODO("Not yet implemented")
    }

    override fun fetchAllCars(): List<CarResponse> {
        TODO("Not yet implemented")
    }

    override fun fetchCarDetailsById(id: UUID): CarResponse {
        TODO("Not yet implemented")
    }

    override fun updateCarDetailsById(id: UUID, carRequest: CarRequest): CarResponse {
        TODO("Not yet implemented")
    }

    override fun deleteCarDetailsById(id: UUID) {
        TODO("Not yet implemented")
    }
}