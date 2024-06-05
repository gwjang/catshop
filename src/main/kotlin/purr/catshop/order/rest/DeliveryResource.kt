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
import purr.catshop.order.domain.Delivery
import purr.catshop.order.domain.dto.DeliveryDTO
import purr.catshop.order.domain.dto.DeliveryRequest
import purr.catshop.order.domain.dto.DeliveryUpdateRequest
import purr.catshop.order.service.dto.DeliveryQueryRequest
import purr.catshop.product.service.DeliveryService
import java.lang.Void

@RestController
@RequestMapping(
    value = ["/api/delivery"],
    produces = [MediaType.APPLICATION_JSON_VALUE],
)
class DeliveryResource(
    private val deliveryService: DeliveryService,
) {
    @GetMapping
    fun getAllDeliveries(): ResponseEntity<List<DeliveryDTO>> = ResponseEntity.ok(deliveryService.findAll(request = DeliveryQueryRequest()))

    @GetMapping("/{id}")
    fun getDelivery(
        @PathVariable(name = "id") id: Long,
    ): ResponseEntity<DeliveryDTO> = ResponseEntity.ok(deliveryService.findOne(id))

    @PostMapping
    @ApiResponse(responseCode = "201")
    fun createDelivery(
        @RequestBody @Valid deliveryRequest: DeliveryRequest,
    ): ResponseEntity<Delivery> {
        val createdId = deliveryService.create(deliveryRequest)
        return ResponseEntity(createdId, HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun updateDelivery(
        @PathVariable(name = "id") id: Long,
        @RequestBody @Valid
        deliveryUpdateRequest: DeliveryUpdateRequest,
    ): ResponseEntity<Long> {
        deliveryService.update(id, deliveryUpdateRequest)
        return ResponseEntity.ok(id)
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    fun deleteDelivery(
        @PathVariable(name = "id") id: Long,
    ): ResponseEntity<Void> {
        deliveryService.delete(id)
        return ResponseEntity.noContent().build()
    }
}
