package com.example.proyectointegrador

import java.util.Calendar
import kotlin.math.ceil

data class ParkingSpace(var vehicle: Vehicle) {

    val MINUTES_IN_MILISECONDS = 60000

    // In Minutes
    val parkedTime: Long
        get() = ((Calendar.getInstance().timeInMillis) - vehicle.checkInTime.timeInMillis) / MINUTES_IN_MILISECONDS

    //Shows a warning if the car wasn't added to the parking, or the succes sign if it was
    fun checkin(wasCarInserted: Boolean) {
        if (wasCarInserted) {
            println("Welcome to AlkeParking!")
        } else {
            println("Sorry, the check-in failed")
        }
    }

    //Call the function that calculates the fee that's associated to the vehicle in case that
    //it was succesfully checked out
    // or it calls the error function
    fun checkOutVehicle(plate: String, parking: Parking) {
        val cost = calculateFee(
            vehicle.vehicleType,
            parkedTime,
            vehicle.discountCard != null
        )
        val wasRemoved = parking.vehicles.remove(vehicle)
        if (wasRemoved) {
            onSuccess(cost)
            val vehiclesCheckedOut = parking.checkoutControl.first + 1
            val totalMoneyEarned = parking.checkoutControl.second + cost
            parking.checkoutControl = Pair(vehiclesCheckedOut, totalMoneyEarned)
        } else onError()
    }

    //calculates the fee that's associated to the vehicle
    fun calculateFee(vehicleType: VehicleType, parkedTime: Long, hasDiscountCard: Boolean): Int {
        var fee = vehicleType.precio + 5 * ceil(minutesExtra(parkedTime) / 15.0)
        if (hasDiscountCard) {
            fee = ceil(fee * 0.85)
        }
        return fee.toInt()
    }


    // Calculates the amount of extra minutes that the vehicle was parked
    // Returns 0 if the vehicle was parked less than 2hs
    fun minutesExtra(parkedTime: Long): Double {
        return maxOf((parkedTime - 120).toDouble(), 0.0)
    }

    //It shows the cost of the parked time, and a come back sign
    fun onSuccess(cost: Int) {
        println("Your fee is ${cost}. Come back soon.")
    }

    //It shows a warning when the checkout failed
    fun onError() {
        println("Sorry, the check-out failed")
    }

}
