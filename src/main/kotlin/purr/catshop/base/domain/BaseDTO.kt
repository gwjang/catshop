package purr.catshop.base.domain

import java.time.LocalDateTime

abstract class BaseDTO(
    open val id: Long,
    open val createdDate: LocalDateTime,
    open val updatedDate: LocalDateTime,
)
