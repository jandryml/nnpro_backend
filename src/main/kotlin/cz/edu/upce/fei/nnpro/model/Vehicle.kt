package cz.edu.upce.fei.nnpro.model

import java.util.*
import javax.persistence.*

@Entity
class Vehicle(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = Long.MIN_VALUE,
    var name: String = "",
    var capacity: Int = 0,
    @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true)
    @JoinColumn(name = "vehicle_id")
    var chauffeurs: List<Chauffeur> =  mutableListOf()
)
