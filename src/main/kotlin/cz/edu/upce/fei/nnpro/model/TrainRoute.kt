package cz.edu.upce.fei.nnpro.model

import javax.persistence.*

@Entity
class TrainRoute(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = Long.MIN_VALUE,
    var trainCode: String = "",
    var capacity: Int = 0,
    //TODO asi nebude potreba, stacil by check, jestli jsou nejake Incidenty
    var closure: Boolean = false,
    @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true)
    @JoinColumn(name = "train_route_id")
    var sections: List<TrainRouteSection> = ArrayList()
)
