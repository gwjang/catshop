package purr.catshop.order.domain.dto

import purr.catshop.order.domain.Order
import purr.catshop.order.domain.enums.PaymentType

data class PaymentRequest(
    val type: PaymentType,
    val order: Order,
)
