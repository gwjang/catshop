package purr.catshop.order.domain.dto

import purr.catshop.order.domain.OrderProduct
import purr.catshop.order.domain.enums.OrderStatus

data class OrderUpdateRequest(
    val status: OrderStatus,
    val products: List<OrderProduct>,
    val customerId: Int,
    val delivery: DeliveryRequest? = null,
    val payment: PaymentRequest? = null,
)
