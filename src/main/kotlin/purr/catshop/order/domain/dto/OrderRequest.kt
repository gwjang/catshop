package purr.catshop.order.domain.dto

import purr.catshop.order.domain.enums.OrderStatus
import purr.catshop.product.domain.dto.OrderProductRequest

data class OrderRequest(
    val status: OrderStatus,
    val orderProductRequests: List<OrderProductRequest>,
    val customerId: Long,
    val payment: PaymentRequest? = null,
)
