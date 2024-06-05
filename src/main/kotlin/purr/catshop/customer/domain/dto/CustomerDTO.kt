package purr.catshop.customer.domain.dto

import purr.catshop.base.domain.BaseDTO
import java.time.LocalDateTime

class CustomerDTO(
    override val id: Long,
    override val createdDate: LocalDateTime,
    override val updatedDate: LocalDateTime,
    val name: String,
    val email: String,
    val username: String,
    val password: String,
) : BaseDTO(
        id = id,
        createdDate = createdDate,
        updatedDate = updatedDate,
    )
