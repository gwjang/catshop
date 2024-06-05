package purr.catshop.order.domain.enums

enum class OrderStatus(val status: String) {
    PENDING("PENDING"),
    CONFIRMED("CONFIRMED"),
    CANCELLED("CANCELLED"),
    DELIVERED("DELIVERED"),
    RETURNED("RETURNED"),
}
