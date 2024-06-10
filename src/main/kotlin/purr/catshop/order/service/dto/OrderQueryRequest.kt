package purr.catshop.order.service.dto

import com.querydsl.core.BooleanBuilder
import com.querydsl.core.types.Predicate
import purr.catshop.base.service.BaseQueryRequest
import purr.catshop.order.domain.QOrder
import purr.catshop.order.domain.enums.OrderStatus

data class OrderQueryRequest(
    val status: OrderStatus? = null,
    val customerId: Long? = null,
) : BaseQueryRequest<Predicate>() {
    override fun toPredicate(): Predicate {
        val qOrder = QOrder.order
        val builder = BooleanBuilder()

        status?.let {
            builder.and(qOrder.status.eq(it))
        }
        customerId?.let {
            builder.and(qOrder.customerId.eq(it))
        }

        return builder
    }
}
