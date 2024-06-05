package purr.catshop.base.service

import com.querydsl.core.types.Predicate
import com.querydsl.core.types.dsl.PathBuilder
import com.querydsl.jpa.impl.JPAQuery
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.support.Querydsl
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import org.springframework.stereotype.Service
import purr.catshop.base.domain.BaseEntity

@Service
abstract class BaseService<T : BaseEntity>(
    entityClassType: Class<T>,
    val pathBuilder: PathBuilder<T>,
) :
    QuerydslRepositorySupport(entityClassType) {
    fun getBaseFindQuery(): JPAQuery<T> {
        return JPAQuery<T>(entityManager)
            .select(pathBuilder)
            .from(pathBuilder)
    }

    fun findOneByIdElseThrow(id: Long): T {
        return getBaseFindQuery()
            .where(pathBuilder.get("id").eq(id))
            .fetchOne() ?: throw IllegalArgumentException("Not found")
    }

    fun findOneById(id: Long): T? {
        return getBaseFindQuery()
            .where(pathBuilder.get("id").eq(id))
            .fetchOne()
    }

    fun findAllById(ids: List<Long>): List<T> {
        return getBaseFindQuery()
            .where(pathBuilder.get("id").`in`(ids))
            .fetch()
    }

    fun findAll(predicate: Predicate): List<T> {
        return getBaseFindQuery()
            .where(predicate)
            .fetch()
    }

    fun findAllWithSort(
        predicate: Predicate,
        sort: Sort,
    ): List<T> {
        val query = getBaseFindQuery().where(predicate)
        val querydsl = Querydsl(entityManager!!, pathBuilder)
        val applySortingQuery = querydsl.applySorting(sort, query)
        return applySortingQuery.fetch()
    }

    fun findAllWithPagination(
        predicate: Predicate,
        page: Int,
        size: Int,
    ): Page<T> {
        val pageable = PageRequest.of(page, size)
        val query = getBaseFindQuery().where(predicate)
        val results = query.offset(pageable.offset).limit(pageable.pageSize.toLong()).fetch()
        return PageImpl(results, pageable, results.count().toLong())
    }

    fun findAllWithPagination(
        predicate: Predicate,
        page: Int,
        size: Int,
        sort: Sort,
    ): Page<T> {
        val pageable = PageRequest.of(page, size, sort)
        val query = getBaseFindQuery().where(predicate)
        val results = query.offset(pageable.offset).limit(pageable.pageSize.toLong()).fetch()
        return PageImpl(results, pageable, results.count().toLong())
    }
}
