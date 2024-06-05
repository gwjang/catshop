package purr.catshop.product.domain.dto

import purr.catshop.base.domain.BaseDTO
import java.time.LocalDateTime

data class ProductDTO(
    override val id: Long,
    override val createdDate: LocalDateTime,
    override val updatedDate: LocalDateTime,
    val name: String,
) : BaseDTO(
        id = id,
        createdDate = createdDate,
        updatedDate = updatedDate,
    )
