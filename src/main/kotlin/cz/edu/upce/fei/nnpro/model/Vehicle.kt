package cz.edu.upce.fei.nnpro.model

import javax.persistence.*

@Entity
class Vehicle(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = Long.MIN_VALUE,
    var name: String = "",
    var capacity: Int = 0,
    var parameters: String = "",
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "company_id")
    var company: TransportCompany? = null,
    @Lob
    var image: ByteArray? = null
)
