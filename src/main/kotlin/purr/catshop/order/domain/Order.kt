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
import purr.catshop.order.domain.dto.OrderRequest
import purr.catshop.order.domain.dto.OrderUpdateRequest
import purr.catshop.order.domain.dto.PaymentRequest
import purr.catshop.order.domain.enums.OrderStatus

@Table(name = "`order`")
@Entity
class Order(
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var status: OrderStatus,
    @OneToMany(
        mappedBy = "order",
        fetch = FetchType.LAZY,
        cascade = [CascadeType.ALL],
        orphanRemoval = true,
    )
    var products: MutableSet<OrderProduct> = mutableSetOf(),
    @Column(nullable = false)
    var customerId: Int,
    @OneToOne(
        fetch = FetchType.LAZY,
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
        fetch = FetchType.LAZY,
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
        fun create(request: OrderRequest): Order {
            val order =
                Order(
                    status = OrderStatus.PENDING,
                    products = request.products.toMutableSet(),
                    customerId = request.customerId,
                )
            request.delivery?.let { order.relateDelivery(it) }
            request.payment?.let { order.relatePayment(it) }
            return order
        }
    }

    private fun relatePayment(paymentRequest: PaymentRequest) {
        val payment =
            Payment.create(
                request =
                    PaymentRequest(
                        type = paymentRequest.type,
                        order = this,
                    ),
            )
        payment.order = this
        this.payment = payment
    }

    private fun relateDelivery(deliveryRequest: DeliveryRequest) {
        val delivery =
            Delivery.create(
                request =
                    DeliveryRequest(
                        address = deliveryRequest.address,
                        order = this,
                    ),
            )
        delivery.order = this
        this.delivery = delivery
    }

    fun update(request: OrderUpdateRequest) {
        this.status = request.status
        this.products = request.products.toMutableSet()
        this.customerId = request.customerId
        request.delivery?.let { this.relateDelivery(it) }
        request.payment?.let { this.relatePayment(it) }
    }

    override fun toDTO(): OrderDTO {
        return OrderDTO(
            id = id,
            createdDate = createdDate,
            updatedDate = updatedDate,
            status = status,
            products = products.toList(),
            customerId = customerId,
            delivery = delivery,
            payment = payment,
        )
    }
}
