package purr.catshop.order.domain.dto

import purr.catshop.base.domain.BaseDTO
import purr.catshop.order.domain.Delivery
import purr.catshop.order.domain.OrderProduct
import purr.catshop.order.domain.Payment
import purr.catshop.order.domain.enums.OrderStatus
import java.time.LocalDateTime

class OrderDTO(
    override val id: Long,
    override val createdDate: LocalDateTime,
    override val updatedDate: LocalDateTime,
    val status: OrderStatus,
    val products: List<OrderProduct>,
    val customerId: Int,
    val delivery: Delivery?,
    val payment: Payment?,
) : BaseDTO(
        id = id,
        createdDate = createdDate,
        updatedDate = updatedDate,
    )
