package purr.catshop.product.service.dto

import com.querydsl.core.BooleanBuilder
import com.querydsl.core.types.Predicate
import purr.catshop.base.service.BaseQueryRequest
import purr.catshop.product.domain.QProduct

data class ProductQueryRequest(
    val name: String? = null,
) : BaseQueryRequest<Predicate>() {
    override fun toPredicate(): Predicate {
        val qProduct = QProduct.product
        val builder = BooleanBuilder()

        name?.let {
            builder.and(qProduct.name.eq(it))
        }

        return builder
    }
}
