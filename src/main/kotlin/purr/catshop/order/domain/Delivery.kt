package purr.catshop.order.domain

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.OneToOne
import purr.catshop.base.domain.BaseEntity
import purr.catshop.order.domain.dto.DeliveryDTO
import purr.catshop.order.domain.dto.DeliveryRequest
import purr.catshop.order.domain.dto.DeliveryUpdateRequest

@Entity
class Delivery(
    @Column
    var address: String,
) : BaseEntity() {
    @OneToOne(
        mappedBy = "delivery",
        fetch = FetchType.LAZY,
        cascade = [CascadeType.ALL],
        orphanRemoval = true,
    )
    lateinit var order: Order

    companion object {
        fun create(request: DeliveryRequest): Delivery {
            val delivery =
                Delivery(
                    address = request.address,
                )
            delivery.relatedOrder(request.order)
            return delivery
        }
    }

    private fun relatedOrder(order: Order) {
        this.order = order
    }

    fun update(request: DeliveryUpdateRequest) {
        address = request.address
    }

    override fun toDTO(): DeliveryDTO {
        return DeliveryDTO(
            id = id,
            createdDate = createdDate,
            updatedDate = updatedDate,
            address = address,
        )
    }
}
