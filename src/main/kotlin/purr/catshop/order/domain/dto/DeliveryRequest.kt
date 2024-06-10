package purr.catshop.order.domain.dto

data class DeliveryRequest(
    val address: String,
    val orderId: Long,
)
