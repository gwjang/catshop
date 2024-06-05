package purr.catshop.customer.service.dto

import com.querydsl.core.BooleanBuilder
import com.querydsl.core.types.Predicate
import purr.catshop.base.service.BaseQueryRequest
import purr.catshop.customer.domain.QCustomer

data class CustomerQueryRequest(
    val name: String? = null,
) : BaseQueryRequest<Predicate>() {
    override fun toPredicate(): Predicate {
        val qCustomer = QCustomer.customer
        val builder = BooleanBuilder()

        name?.let {
            builder.and(qCustomer.name.eq(it))
        }

        return builder
    }
}
