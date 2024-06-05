package purr.catshop.product.service.dto

import com.querydsl.core.BooleanBuilder
import com.querydsl.core.types.Predicate
import purr.catshop.base.service.BaseQueryRequest
import purr.catshop.product.domain.QCategory

data class CategoryQueryRequest(
    val name: String? = null,
) : BaseQueryRequest<Predicate>() {
    override fun toPredicate(): Predicate {
        val qCategory = QCategory.category
        val builder = BooleanBuilder()

        name?.let {
            builder.and(qCategory.name.eq(it))
        }

        return builder
    }
}
