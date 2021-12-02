package cz.edu.upce.fei.nnpro.dto

class ChauffeurDto(
    var id: Long = Long.MIN_VALUE,
    var firstname: String,
    var lastname: String,
    var drivingLicense: String,
    var drivingExperience: String,
    var phoneNumber: String,
    var vehicleId: Long
)