package purr.catshop.customer.rest

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
import purr.catshop.customer.domain.Customer
import purr.catshop.customer.domain.dto.CustomerDTO
import purr.catshop.customer.domain.dto.CustomerRequest
import purr.catshop.customer.domain.dto.CustomerUpdateRequest
import purr.catshop.customer.service.CustomerService
import purr.catshop.customer.service.dto.CustomerQueryRequest
import java.lang.Void

@RestController
@RequestMapping(
    value = ["/api/customer"],
    produces = [MediaType.APPLICATION_JSON_VALUE],
)
class CustomerResource(
    private val customerService: CustomerService,
) {
    @GetMapping
    fun getAllCustomers(): ResponseEntity<List<CustomerDTO>> = ResponseEntity.ok(customerService.findAll(request = CustomerQueryRequest()))

    @GetMapping("/{id}")
    fun getCustomer(
        @PathVariable(name = "id") id: Long,
    ): ResponseEntity<CustomerDTO> = ResponseEntity.ok(customerService.findOne(id))

    @PostMapping
    @ApiResponse(responseCode = "201")
    fun createCustomer(
        @RequestBody @Valid customerRequest: CustomerRequest,
    ): ResponseEntity<Customer> {
        val createdId = customerService.create(customerRequest)
        return ResponseEntity(createdId, HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun updateCustomer(
        @PathVariable(name = "id") id: Long,
        @RequestBody @Valid
        customerUpdateRequest: CustomerUpdateRequest,
    ): ResponseEntity<Long> {
        customerService.update(id, customerUpdateRequest)
        return ResponseEntity.ok(id)
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    fun deleteCustomer(
        @PathVariable(name = "id") id: Long,
    ): ResponseEntity<Void> {
        customerService.delete(id)
        return ResponseEntity.noContent().build()
    }
}
