package purr.catshop.order.domain.dto

import purr.catshop.order.domain.enums.OrderStatus

data class OrderUpdateRequest(
    val status: OrderStatus,
    val customerId: Long,
    val delivery: DeliveryRequest? = null,
    val payment: PaymentRequest? = null,
)
