package purr.catshop.order.domain

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import purr.catshop.base.domain.BaseEntity
import purr.catshop.product.domain.dto.OrderProductDTO

@Entity
class OrderProduct(
    @Column(nullable = false)
    val productId: Long,
    @Column(nullable = false)
    val count: Int,
) : BaseEntity() {
    @ManyToOne(
        fetch = FetchType.EAGER,
        cascade = [CascadeType.ALL],
    )
    @JoinColumn(
        name = "orderId",
        nullable = false,
    )
    lateinit var order: Order

    companion object {
        fun create(
            productId: Long,
            count: Int,
            order: Order,
        ): OrderProduct {
            val orderProduct =
                OrderProduct(
                    productId = productId,
                    count = count,
                )
            orderProduct.relatedOrder(order)
            return orderProduct
        }
    }

    fun relatedOrder(order: Order) {
        this.order = order
    }

    override fun toDTO(): OrderProductDTO {
        return OrderProductDTO(
            id = id,
            productId = productId,
            count = count,
            createdDate = createdDate,
            updatedDate = updatedDate,
        )
    }
}
