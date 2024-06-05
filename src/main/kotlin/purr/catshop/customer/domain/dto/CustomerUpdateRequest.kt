package purr.catshop.customer.domain.dto

data class CustomerUpdateRequest(
    var name: String,
    var email: String,
    var username: String,
    var password: String,
)
