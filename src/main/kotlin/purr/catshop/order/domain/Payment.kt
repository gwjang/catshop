package purr.catshop.order.domain

import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.OneToOne
import purr.catshop.base.domain.BaseDTO
import purr.catshop.base.domain.BaseEntity
import purr.catshop.order.domain.dto.PaymentRequest
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
        fun create(request: PaymentRequest): Payment {
            val payment =
                Payment(
                    type = request.type,
                )
            payment.relatedOrder(request.order)
            return payment
        }
    }

    private fun relatedOrder(order: Order) {
        this.order = order
    }

    override fun toDTO(): BaseDTO {
        TODO("Not yet implemented")
    }
}
