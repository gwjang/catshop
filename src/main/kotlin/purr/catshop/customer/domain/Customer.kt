package purr.catshop.customer.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import purr.catshop.base.domain.BaseEntity
import purr.catshop.customer.domain.dto.CustomerDTO
import purr.catshop.customer.domain.dto.CustomerRequest
import purr.catshop.customer.domain.dto.CustomerUpdateRequest

@Entity
class Customer(
    @Column(
        nullable = false,
        length = 10,
    )
    var name: String,
    @Column(
        nullable = false,
        length = 100,
    )
    var email: String,
    @Column(
        nullable = false,
        length = 30,
    )
    var username: String,
    @Column(nullable = false)
    var password: String,
) : BaseEntity() {
    companion object {
        fun create(request: CustomerRequest): Customer {
            return Customer(
                name = request.name,
                email = request.email,
                username = request.username,
                password = request.password,
            )
        }
    }

    fun update(request: CustomerUpdateRequest) {
        name = request.name
        email = request.email
        username = request.username
        password = request.password
    }

    override fun toDTO(): CustomerDTO {
        return CustomerDTO(
            id = id,
            createdDate = createdDate,
            updatedDate = updatedDate,
            name = name,
            email = email,
            username = username,
            password = password,
        )
    }
}
