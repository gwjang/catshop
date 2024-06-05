package purr.catshop.customer.domain.dto

data class CustomerRequest(
    var name: String,
    var email: String,
    var username: String,
    var password: String,
)
