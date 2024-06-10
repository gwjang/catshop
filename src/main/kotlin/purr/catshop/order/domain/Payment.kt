package purr.catshop.order.domain

import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.OneToOne
import purr.catshop.base.domain.BaseEntity
import purr.catshop.order.domain.dto.PaymentDTO
import purr.catshop.order.domain.enums.PaymentType

@Entity
class Payment(
    val type: PaymentType,
) : BaseEntity() {
    @OneToOne(
        mappedBy = "payment",
        cascade = [CascadeType.ALL],
        orphanRemoval = true,
    )
    lateinit var order: Order

    companion object {
        fun create(
            type: PaymentType,
            order: Order,
        ): Payment {
            val payment =
                Payment(
                    type = type,
                )
            payment.relatedOrder(order)
            return payment
        }
    }

    private fun relatedOrder(order: Order) {
        this.order = order
    }

    override fun toDTO(): PaymentDTO {
        return PaymentDTO(
            id = id,
            createdDate = createdDate,
            updatedDate = updatedDate,
            type = type,
        )
    }
}
