package purr.catshop.product.service

import com.querydsl.core.types.PathMetadataFactory
import com.querydsl.core.types.dsl.PathBuilder
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import purr.catshop.base.service.BaseService
import purr.catshop.product.domain.Cart
import purr.catshop.product.domain.dto.CartDTO
import purr.catshop.product.domain.dto.CartRequest
import purr.catshop.product.domain.dto.CartUpdateRequest
import purr.catshop.product.repos.CartRepository
import purr.catshop.product.repos.ProductRepository
import purr.catshop.product.service.dto.CartQueryRequest

@Service
class CartService(
    private val cartRepository: CartRepository,
    private val productRepository: ProductRepository,
) : BaseService<Cart>(
        entityClassType = Cart::class.java,
        pathBuilder =
            PathBuilder<Cart>(
                Cart::class.java,
                PathMetadataFactory.forVariable("cart"),
            ),
    ) {
    fun findAll(request: CartQueryRequest): List<CartDTO> {
        return findAll(predicate = request.toPredicate())
            .map { cart -> cart.toDTO() }
    }

    fun findOne(id: Long): CartDTO? = findOneById(id)?.toDTO()

    fun findOneElseThrow(id: Long): CartDTO = findOneByIdElseThrow(id).toDTO()

    @Transactional
    fun create(request: CartRequest): Cart {
        val product = productRepository.findById(request.productId).orElseThrow { IllegalArgumentException("Product not found") }
        val cart = Cart.create(customerId = request.customerId, product = product, quantity = request.quantity)
        return cartRepository.save(cart)
    }

    @Transactional
    fun update(
        id: Long,
        request: CartUpdateRequest,
    ): Cart {
        val cart = findOneByIdElseThrow(id)
        cart.update(request = request)
        return cart
    }

    @Transactional
    fun delete(id: Long) {
        cartRepository.deleteById(id)
    }
}
