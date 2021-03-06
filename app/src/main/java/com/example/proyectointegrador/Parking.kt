package com.example.proyectointegrador

data class Parking(val vehicles: MutableSet<Vehicle>) {
    val maximumCapacity = 20

    var checkoutControl: Pair<Int, Int> = Pair(0, 0)

    //Shows the checkin for all the vehicles
    init {
        vehicles.toList().forEach{
            val parkingSpace = ParkingSpace(it)
            parkingSpace.checkin(true)
        }
    }

    //Verifies if the Parking was abled to add the vehicle
    fun addVehicle(vehicle: Vehicle): Boolean {
        var wasInserted = false
        val parkingSpace = ParkingSpace(vehicle)
        if (vehicles.size < maximumCapacity) {
            wasInserted = vehicles.add(vehicle)
        }
        parkingSpace.checkin(wasInserted)
        return wasInserted

    }

    //Does the checkout of the vehicle only if it's in the parking, if not, it shows an error
    fun removeVehicle(plate: String) {
        val vehicleFound = vehicles.find { it.plate == plate }
        vehicleFound?.let {
            val parkingSpace = ParkingSpace(it)
            parkingSpace.checkOutVehicle(plate, this)
        } ?: println("Sorry, the check-out failed")
    }

    //Prints the total of vehicles that have been checked out and the total earnings that
    //goes with them
    fun showEarnings() {
        println("${checkoutControl.first} vehicles have checked out and have earnings of ${checkoutControl.second}")
    }

    //Prints the total of vehicles that are parked
    fun listVehicles() {
        val platesList = vehicles.map { it.plate }
        println(platesList)
    }
}
// Answer, why do we use vehicles as a Set?
// Because it allows us to modify/add/delete data easily and because with the hashCode we can
// be sure that the plates will  be unique
