package purr.catshop.order.domain.dto

import purr.catshop.base.domain.BaseDTO
import purr.catshop.order.domain.enums.OrderStatus
import purr.catshop.product.domain.dto.OrderProductDTO
import java.time.LocalDateTime

class OrderDTO(
    override val id: Long,
    override val createdDate: LocalDateTime,
    override val updatedDate: LocalDateTime,
    val status: OrderStatus,
    val orderProducts: List<OrderProductDTO>,
    val customerId: Long,
    val delivery: DeliveryDTO?,
    val payment: PaymentDTO?,
) : BaseDTO(
        id = id,
        createdDate = createdDate,
        updatedDate = updatedDate,
    )
