package purr.catshop.product.domain.dto

import purr.catshop.base.domain.BaseDTO
import java.time.LocalDateTime

data class CategoryDTO(
    override val id: Long,
    override val createdDate: LocalDateTime,
    override val updatedDate: LocalDateTime,
    val name: String,
    val products: List<ProductDTO>,
) : BaseDTO(
        id = id,
        createdDate = createdDate,
        updatedDate = updatedDate,
    )
