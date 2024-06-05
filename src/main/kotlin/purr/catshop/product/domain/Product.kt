package purr.catshop.product.domain

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import purr.catshop.base.domain.BaseEntity
import purr.catshop.product.domain.dto.ProductDTO
import purr.catshop.product.domain.dto.ProductUpdateRequest

@Entity
class Product(
    @Column(nullable = false)
    var name: String,
    @OneToMany(
        mappedBy = "product",
        fetch = FetchType.LAZY,
        cascade = [CascadeType.ALL],
        orphanRemoval = true,
    )
    var carts: MutableSet<Cart> = mutableSetOf(),
) : BaseEntity() {
    @ManyToOne(
        fetch = FetchType.LAZY,
        cascade = [CascadeType.ALL],
    )
    @JoinColumn(
        name = "categoryId",
        nullable = false,
    )
    lateinit var category: Category

    companion object {
        fun create(
            name: String,
            category: Category,
        ): Product {
            val product =
                Product(
                    name = name,
                )
            product.relateCategory(category)
            return product
        }
    }

    private fun relateCategory(category: Category) {
        this.category = category
    }

    fun update(request: ProductUpdateRequest) {
        name = request.name
    }

    override fun toDTO(): ProductDTO {
        return ProductDTO(
            id = id,
            createdDate = createdDate,
            updatedDate = updatedDate,
            name = name,
        )
    }
}
