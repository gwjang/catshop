package purr.catshop.product.domain

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import purr.catshop.base.domain.BaseEntity
import purr.catshop.product.domain.dto.CartDTO
import purr.catshop.product.domain.dto.CartUpdateRequest

@Entity
class Cart(
    @Column(nullable = false)
    val customerId: Long,
    @Column(nullable = false)
    var quantity: Int,
) : BaseEntity() {
    @ManyToOne(
        fetch = FetchType.EAGER,
        cascade = [CascadeType.ALL],
    )
    @JoinColumn(
        name = "productId",
        nullable = false,
    )
    lateinit var product: Product

    companion object {
        fun create(
            customerId: Long,
            product: Product,
            quantity: Int,
        ): Cart {
            val cart =
                Cart(
                    customerId = customerId,
                    quantity = quantity,
                )
            cart.relateProduct(product)
            return cart
        }
    }

    private fun relateProduct(product: Product) {
        this.product = product
    }

    fun update(request: CartUpdateRequest) {
        quantity = request.quantity
    }

    override fun toDTO(): CartDTO {
        return CartDTO(
            id = id,
            createdDate = createdDate,
            updatedDate = updatedDate,
            customerId = customerId,
            product = product.toDTO(),
            quantity = quantity,
        )
    }
}
