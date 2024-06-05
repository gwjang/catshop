package purr.catshop.product.domain.dto

data class ProductRequest(
    val name: String,
    val categoryId: Long,
)
