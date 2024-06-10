package purr.catshop.order.domain

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToMany
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import purr.catshop.base.domain.BaseEntity
import purr.catshop.order.domain.dto.DeliveryRequest
import purr.catshop.order.domain.dto.OrderDTO
import purr.catshop.order.domain.dto.PaymentRequest
import purr.catshop.order.domain.enums.OrderStatus
import purr.catshop.product.domain.dto.OrderProductRequest

@Table(name = "`order`")
@Entity
class Order(
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var status: OrderStatus,
    @OneToMany(
        mappedBy = "order",
        fetch = FetchType.EAGER,
        cascade = [CascadeType.ALL],
        orphanRemoval = true,
    )
    var products: MutableSet<OrderProduct> = mutableSetOf(),
    @Column(nullable = false)
    var customerId: Long,
    @OneToOne(
        fetch = FetchType.EAGER,
        cascade = [CascadeType.ALL],
        orphanRemoval = true,
    )
    @JoinColumn(
        name = "deliveryId",
        unique = true,
        nullable = true,
    )
    var delivery: Delivery? = null,
    @OneToOne(
        fetch = FetchType.EAGER,
        cascade = [CascadeType.ALL],
        orphanRemoval = true,
    )
    @JoinColumn(
        name = "paymentId",
        unique = true,
        nullable = true,
    )
    var payment: Payment? = null,
) : BaseEntity() {
    companion object {
        fun create(
            status: OrderStatus,
            orderProductRequests: List<OrderProductRequest>,
            customerId: Long,
            payment: PaymentRequest? = null,
        ): Order {
            val order =
                Order(
                    status = status,
                    customerId = customerId,
                )
            order.products =
                orderProductRequests.map {
                    OrderProduct.create(
                        productId = it.productId,
                        count = it.count,
                        order = order,
                    )
                }.toMutableSet()
            payment?.let { order.relatePayment(it) }
            return order
        }
    }

    private fun relatePayment(paymentRequest: PaymentRequest) {
        val payment =
            Payment.create(
                paymentRequest.type,
                this,
            )
        payment.order = this
        this.payment = payment
    }

    private fun relateDelivery(address: String) {
        val delivery =
            Delivery.create(
                address = address,
                order = this,
            )
        delivery.order = this
        this.delivery = delivery
    }

    fun update(
        status: OrderStatus,
        customerId: Long,
        delivery: DeliveryRequest? = null,
        payment: PaymentRequest? = null,
    ) {
        this.status = status
        this.customerId = customerId
        delivery?.let { this.relateDelivery(it.address) }
        payment?.let { this.relatePayment(it) }
    }

    override fun toDTO(): OrderDTO {
        return OrderDTO(
            id = id,
            createdDate = createdDate,
            updatedDate = updatedDate,
            status = status,
            orderProducts = products.map { it.toDTO() },
            customerId = customerId,
            delivery = delivery?.toDTO(),
            payment = payment?.toDTO(),
        )
    }
}
