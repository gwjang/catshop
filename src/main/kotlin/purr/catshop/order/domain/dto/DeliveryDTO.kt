package purr.catshop.order.domain.dto

import purr.catshop.base.domain.BaseDTO
import java.time.LocalDateTime

class DeliveryDTO(
    override val id: Long,
    override val createdDate: LocalDateTime,
    override val updatedDate: LocalDateTime,
    var address: String,
) : BaseDTO(
        id = id,
        createdDate = createdDate,
        updatedDate = updatedDate,
    )
