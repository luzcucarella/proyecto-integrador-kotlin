package com.example.proyectointegrador

import java.util.Calendar
import kotlin.math.ceil

data class ParkingSpace(var vehicle: Vehicle) {

    val MINUTES_IN_MILISECONDS = 60000

    // In Minutes
    val parkedTime: Long
        get() = ((Calendar.getInstance().timeInMillis) - vehicle.checkInTime.timeInMillis) / MINUTES_IN_MILISECONDS

    /*val parkedTime: Long
        get() {
            val cal = Calendar.getInstance()
            //cal.set(2022, 4, 31, 14, 40, 8)
            cal.set(Calendar.HOUR, 3) // Pone la hora en 10
            cal.set(Calendar.AM_PM, Calendar.PM)
            return (cal.timeInMillis - vehicle.checkInTime.timeInMillis) / MINUTES_IN_MILISECONDS
        }*/
        
    fun checkin(wasCarInserted: Boolean) {
        if (wasCarInserted) {
            println("Welcome to AlkeParking!")
        } else {
            println("Sorry, the check-in failed")
        }
    }

    fun checkOutVehicle(plate: String, parking: Parking) {
        //if (vehicleFound != null) { // vehicles.contains { it.plate == plate }
        var cost = calculateFee(
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
        // println("El valor es: ${cost}")
        //   }
    }

    fun calculateFee(vehicleType: VehicleType, parkedTime: Long, hasDiscountCard: Boolean): Int {
        var fee = vehicleType.precio + 5 * ceil(minutesExtra(parkedTime) / 15.0)
        if (hasDiscountCard) {
            fee = ceil(fee * 0.85)
        }
        return fee.toInt()
    }


    // Calculates de amount of extra minutes that the vehicle was parked
    // Returns 0 if the vehicle was parked less than 2hs
    fun minutesExtra(parkedTime: Long): Double {
        return maxOf((parkedTime - 120).toDouble(), 0.0)
    }

    // vehicles[plate]
    fun onSuccess(cost: Int) {
        println("Your fee is ${cost}. Come back soon.")
    }

    fun onError() {
        println("Sorry, the check-out failed")
    }

}
