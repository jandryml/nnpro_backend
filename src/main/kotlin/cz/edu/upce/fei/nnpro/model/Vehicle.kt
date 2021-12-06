package cz.edu.upce.fei.nnpro.model

import javax.persistence.*

@Entity
class Vehicle(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = Long.MIN_VALUE,
    var name: String = "",
    var capacity: Int = 0,
    var color: String = "",
    var year: Long = 1900,
    var actuation: String = "",
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "company_id")
    var company: TransportCompany? = null,
    @Lob
    var image: ByteArray? = null,
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "substitute_route_id")
    var substituteRoute: SubstituteRoute? = null
)
