package cz.edu.upce.fei.nnpro.model

import javax.persistence.*

@Entity
class TransportCompany(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = Long.MIN_VALUE,
    var code: String = "",
    var name: String = "",
    var cin: String = ""
)
