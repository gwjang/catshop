package purr.catshop.product.rest

import io.swagger.v3.oas.annotations.responses.ApiResponse
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import purr.catshop.product.domain.Cart
import purr.catshop.product.domain.dto.CartDTO
import purr.catshop.product.domain.dto.CartRequest
import purr.catshop.product.domain.dto.CartUpdateRequest
import purr.catshop.product.service.CartService
import purr.catshop.product.service.dto.CartQueryRequest
import java.lang.Void

@RestController
@RequestMapping(
    value = ["/api/cart"],
    produces = [MediaType.APPLICATION_JSON_VALUE],
)
class CartResource(
    private val cartService: CartService,
) {
    @GetMapping
    fun getAllCarts(): ResponseEntity<List<CartDTO>> = ResponseEntity.ok(cartService.findAll(request = CartQueryRequest()))

    @GetMapping("/{id}")
    fun getCart(
        @PathVariable(name = "id") id: Long,
    ): ResponseEntity<CartDTO> = ResponseEntity.ok(cartService.findOne(id))

    @PostMapping
    @ApiResponse(responseCode = "201")
    fun createCart(
        @RequestBody @Valid cartRequest: CartRequest,
    ): ResponseEntity<Cart> {
        val createdId = cartService.create(cartRequest)
        return ResponseEntity(createdId, HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun updateCart(
        @PathVariable(name = "id") id: Long,
        @RequestBody @Valid cartUpdateRequest: CartUpdateRequest,
    ): ResponseEntity<Long> {
        cartService.update(id, cartUpdateRequest)
        return ResponseEntity.ok(id)
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    fun deleteCart(
        @PathVariable(name = "id") id: Long,
    ): ResponseEntity<Void> {
        cartService.delete(id)
        return ResponseEntity.noContent().build()
    }
}
