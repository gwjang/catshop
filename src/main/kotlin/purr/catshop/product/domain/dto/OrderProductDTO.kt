package purr.catshop.product.domain.dto

import purr.catshop.base.domain.BaseDTO
import java.time.LocalDateTime

data class OrderProductDTO(
    override val id: Long,
    override val createdDate: LocalDateTime,
    override val updatedDate: LocalDateTime,
    val productId: Long,
    val count: Int,
) : BaseDTO(
        id = id,
        createdDate = createdDate,
        updatedDate = updatedDate,
    )
