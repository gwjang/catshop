package purr.catshop.order.domain.dto

import purr.catshop.base.domain.BaseDTO
import purr.catshop.order.domain.enums.PaymentType
import java.time.LocalDateTime

data class PaymentDTO(
    override val id: Long,
    override val createdDate: LocalDateTime,
    override val updatedDate: LocalDateTime,
    var type: PaymentType,
) : BaseDTO(
        id = id,
        createdDate = createdDate,
        updatedDate = updatedDate,
    )
