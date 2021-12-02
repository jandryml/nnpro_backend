package cz.edu.upce.fei.nnpro.model

import javax.persistence.*

@Entity
class Chauffeur(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = Long.MIN_VALUE,
    var firstname: String = "",
    var lastname: String = "",
    var drivingLicense: String = "",
    var drivingExperience: String = "",
    var phoneNumber: String = "",
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "vehicle_id")
    var vehicle: Vehicle? = null
)
