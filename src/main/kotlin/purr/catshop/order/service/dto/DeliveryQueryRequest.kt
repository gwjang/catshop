package purr.catshop.order.service.dto

import com.querydsl.core.BooleanBuilder
import com.querydsl.core.types.Predicate
import purr.catshop.base.service.BaseQueryRequest
import purr.catshop.order.domain.QDelivery

data class DeliveryQueryRequest(
    val address: String? = null,
) : BaseQueryRequest<Predicate>() {
    override fun toPredicate(): Predicate {
        val qDelivery = QDelivery.delivery
        val builder = BooleanBuilder()

        address?.let {
            builder.and(qDelivery.address.eq(it))
        }

        return builder
    }
}
