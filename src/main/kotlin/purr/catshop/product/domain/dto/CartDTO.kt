package purr.catshop.product.domain.dto

import purr.catshop.base.domain.BaseDTO
import java.time.LocalDateTime

data class CartDTO(
    override val id: Long,
    override val createdDate: LocalDateTime,
    override val updatedDate: LocalDateTime,
    val customerId: Long,
    val product: ProductDTO,
    val quantity: Int,
) : BaseDTO(
        id = id,
        createdDate = createdDate,
        updatedDate = updatedDate,
    )
