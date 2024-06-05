package purr.catshop.product.service.dto

import com.querydsl.core.BooleanBuilder
import com.querydsl.core.types.Predicate
import purr.catshop.base.service.BaseQueryRequest
import purr.catshop.product.domain.QCart

data class CartQueryRequest(
    val customerId: Long? = null,
) : BaseQueryRequest<Predicate>() {
    override fun toPredicate(): Predicate {
        val qCart = QCart.cart
        val builder = BooleanBuilder()

        customerId?.let {
            builder.and(qCart.customerId.eq(it))
        }

        return builder
    }
}
