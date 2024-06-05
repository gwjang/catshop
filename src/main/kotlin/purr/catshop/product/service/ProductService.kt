package purr.catshop.product.service

import com.querydsl.core.types.PathMetadataFactory
import com.querydsl.core.types.dsl.PathBuilder
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import purr.catshop.base.service.BaseService
import purr.catshop.product.domain.Product
import purr.catshop.product.domain.dto.ProductDTO
import purr.catshop.product.domain.dto.ProductRequest
import purr.catshop.product.domain.dto.ProductUpdateRequest
import purr.catshop.product.repos.ProductRepository
import purr.catshop.product.service.dto.ProductQueryRequest

@Service
class ProductService(
    private val productRepository: ProductRepository,
    private val categoryService: CategoryService,
) : BaseService<Product>(
        entityClassType = Product::class.java,
        pathBuilder =
            PathBuilder<Product>(
                Product::class.java,
                PathMetadataFactory.forVariable("product"),
            ),
    ) {
    fun findAll(request: ProductQueryRequest): List<ProductDTO> {
        return findAll(predicate = request.toPredicate())
            .map { category -> category.toDTO() }
    }

    fun findOne(id: Long): ProductDTO? = findOneById(id)?.toDTO()

    fun findOneElseThrow(id: Long): ProductDTO = findOneByIdElseThrow(id).toDTO()

    @Transactional
    fun create(request: ProductRequest): Product {
        val category = categoryService.findOneByIdElseThrow(request.categoryId)
        val product = Product.create(request.name, category)
        return productRepository.save(product)
    }

    @Transactional
    fun update(
        id: Long,
        request: ProductUpdateRequest,
    ): Product {
        val product = findOneByIdElseThrow(id)
        product.update(request = request)
        return product
    }

    @Transactional
    fun delete(id: Long) {
        productRepository.deleteById(id)
    }
}
