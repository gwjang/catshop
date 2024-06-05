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
import purr.catshop.product.domain.Product
import purr.catshop.product.domain.dto.ProductDTO
import purr.catshop.product.domain.dto.ProductRequest
import purr.catshop.product.domain.dto.ProductUpdateRequest
import purr.catshop.product.service.ProductService
import purr.catshop.product.service.dto.ProductQueryRequest
import java.lang.Void

@RestController
@RequestMapping(
    value = ["/api/product"],
    produces = [MediaType.APPLICATION_JSON_VALUE],
)
class ProductResource(
    private val productService: ProductService,
) {
    @GetMapping
    fun getAllProducts(): ResponseEntity<List<ProductDTO>> = ResponseEntity.ok(productService.findAll(request = ProductQueryRequest()))

    @GetMapping("/{id}")
    fun getProduct(
        @PathVariable(name = "id") id: Long,
    ): ResponseEntity<ProductDTO> = ResponseEntity.ok(productService.findOne(id))

    @PostMapping
    @ApiResponse(responseCode = "201")
    fun createProduct(
        @RequestBody @Valid productRequest: ProductRequest,
    ): ResponseEntity<Product> {
        val product = productService.create(productRequest)
        return ResponseEntity(product, HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun updateProduct(
        @PathVariable(name = "id") id: Long,
        @RequestBody @Valid
        productUpdateRequest: ProductUpdateRequest,
    ): ResponseEntity<Product> {
        val product = productService.update(id, productUpdateRequest)
        return ResponseEntity.ok(product)
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    fun deleteProduct(
        @PathVariable(name = "id") id: Long,
    ): ResponseEntity<Void> {
        productService.delete(id)
        return ResponseEntity.noContent().build()
    }
}
