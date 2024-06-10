package purr.catshop.product.domain.dto

data class CartRequest(
    val customerId: Long,
    val productId: Long,
    val quantity: Int,
)
