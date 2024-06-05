package purr.catshop.product.service

import com.querydsl.core.types.PathMetadataFactory
import com.querydsl.core.types.dsl.PathBuilder
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import purr.catshop.base.service.BaseService
import purr.catshop.product.domain.Category
import purr.catshop.product.domain.dto.CategoryDTO
import purr.catshop.product.domain.dto.CategoryRequest
import purr.catshop.product.domain.dto.CategoryUpdateRequest
import purr.catshop.product.repos.CategoryRepository
import purr.catshop.product.service.dto.CategoryQueryRequest

@Service
class CategoryService(
    private val categoryRepository: CategoryRepository,
) : BaseService<Category>(
        entityClassType = Category::class.java,
        pathBuilder =
            PathBuilder<Category>(
                Category::class.java,
                PathMetadataFactory.forVariable("category"),
            ),
    ) {
    fun findAll(request: CategoryQueryRequest): List<CategoryDTO> {
        return findAll(predicate = request.toPredicate())
            .map { category -> category.toDTO() }
    }

    fun findOne(id: Long): CategoryDTO? = findOneById(id)?.toDTO()

    fun findOneElseThrow(id: Long): CategoryDTO = findOneByIdElseThrow(id).toDTO()

    @Transactional
    fun create(request: CategoryRequest): Category {
        val category =
            Category
                .create(request.name)
        return categoryRepository.save(category)
    }

    @Transactional
    fun update(
        id: Long,
        request: CategoryUpdateRequest,
    ): Category {
        val category = findOneByIdElseThrow(id)
        category.update(request = request)
        return category
    }

    @Transactional
    fun delete(id: Long) {
        categoryRepository.deleteById(id)
    }
}
