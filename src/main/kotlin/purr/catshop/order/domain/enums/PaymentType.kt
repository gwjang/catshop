package purr.catshop.order.domain.enums

enum class PaymentType(
    val type: String,
) {
    CREDIT_CARD("credit_card"),
    CASH("cash"),
    POINT("point"),
    COUPON("coupon"),
    PAYPAL("paypal"),
    KAKAO("kakao"),
}
