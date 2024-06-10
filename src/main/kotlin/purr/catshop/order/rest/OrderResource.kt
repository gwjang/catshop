package purr.catshop.order.rest

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
import purr.catshop.order.domain.dto.OrderDTO
import purr.catshop.order.domain.dto.OrderRequest
import purr.catshop.order.domain.dto.OrderUpdateRequest
import purr.catshop.order.service.dto.OrderQueryRequest
import purr.catshop.product.service.OrderService
import java.lang.Void

@RestController
@RequestMapping(
    value = ["/api/order"],
    produces = [MediaType.APPLICATION_JSON_VALUE],
)
class OrderResource(
    private val orderService: OrderService,
) {
    @GetMapping
    fun getAllOrders(): ResponseEntity<List<OrderDTO>> = ResponseEntity.ok(orderService.findAll(request = OrderQueryRequest()))

    @GetMapping("/{id}")
    fun getOrder(
        @PathVariable(name = "id") id: Long,
    ): ResponseEntity<OrderDTO> = ResponseEntity.ok(orderService.findOne(id))

    @PostMapping
    @ApiResponse(responseCode = "201")
    fun createOrder(
        @RequestBody @Valid orderRequest: OrderRequest,
    ): ResponseEntity<OrderDTO> {
        val order = orderService.create(orderRequest)
        return ResponseEntity(order.toDTO(), HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun updateOrder(
        @PathVariable(name = "id") id: Long,
        @RequestBody @Valid orderUpdateRequest: OrderUpdateRequest,
    ): ResponseEntity<OrderDTO> {
        val order = orderService.update(id, orderUpdateRequest)
        return ResponseEntity.ok(order.toDTO())
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    fun deleteOrder(
        @PathVariable(name = "id") id: Long,
    ): ResponseEntity<Void> {
        orderService.delete(id)
        return ResponseEntity.noContent().build()
    }
}
