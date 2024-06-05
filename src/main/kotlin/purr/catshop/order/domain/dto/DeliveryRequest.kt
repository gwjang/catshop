package purr.catshop.order.domain.dto

import purr.catshop.order.domain.Order

data class DeliveryRequest(
    val address: String,
    val order: Order,
)
