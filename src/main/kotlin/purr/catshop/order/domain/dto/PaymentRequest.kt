package purr.catshop.order.domain.dto

import purr.catshop.order.domain.enums.PaymentType

data class PaymentRequest(
    val type: PaymentType,
)
