package purr.catshop.order.domain

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import purr.catshop.base.domain.BaseDTO
import purr.catshop.base.domain.BaseEntity

@Entity
class OrderProduct(
    @Column(nullable = false)
    val productId: Int,
    @Column(nullable = false)
    val count: Int,
) : BaseEntity() {
    @ManyToOne(
        fetch = FetchType.LAZY,
        cascade = [CascadeType.ALL],
    )
    @JoinColumn(
        name = "orderId",
        nullable = false,
    )
    lateinit var order: Order

    override fun toDTO(): BaseDTO {
        TODO("Not yet implemented")
    }
}
