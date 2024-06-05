package purr.catshop.product.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.OneToMany
import purr.catshop.base.domain.BaseEntity
import purr.catshop.product.domain.dto.CategoryDTO
import purr.catshop.product.domain.dto.CategoryUpdateRequest

@Entity
class Category(
    @Column(nullable = false)
    var name: String,
    @JsonIgnore
    @OneToMany(
        mappedBy = "category",
        fetch = FetchType.LAZY,
        cascade = [CascadeType.ALL],
        orphanRemoval = true,
    )
    var products: MutableSet<Product> = mutableSetOf(),
) : BaseEntity() {
    companion object {
        fun create(name: String): Category {
            return Category(
                name = name,
            )
        }
    }

    fun update(request: CategoryUpdateRequest) {
        name = request.name
    }

    override fun toDTO(): CategoryDTO {
        return CategoryDTO(
            id = id,
            createdDate = createdDate,
            updatedDate = updatedDate,
            name = name,
            products = products.map { it.toDTO() },
        )
    }
}
