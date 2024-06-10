package purr.catshop.product.domain.dto

data class OrderProductRequest(
    val productId: Long,
    val count: Int,
)
