package purr.catshop.product.domain.dto

import purr.catshop.product.domain.Product

data class CartRequest(
    val customerId: Long,
    val product: Product,
    val quantity: Int,
)
