package cz.edu.upce.fei.nnpro.model

import javax.persistence.*

@Entity
class SubstituteRoute(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = Long.MIN_VALUE,
    val name: String = "",
    @OneToOne
    @JoinColumn(name = "train_route_id")
    val concernedTrainRoute: TrainRoute? = null,
    val validated: Boolean = false,
    @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true)
    @JoinColumn(name = "substitute_route_id")
    var sections: List<SubstituteRouteSection> =  ArrayList()
)
