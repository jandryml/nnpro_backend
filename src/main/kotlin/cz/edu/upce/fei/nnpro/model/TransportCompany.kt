package cz.edu.upce.fei.nnpro.model

import javax.persistence.*

@Entity
class TransportCompany(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = Long.MIN_VALUE,
    var name: String = "",
    @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true)
    @JoinColumn(name = "company_id")
    var vehicles: List<Vehicle> = mutableListOf()
)
