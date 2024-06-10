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
import purr.catshop.product.domain.dto.CategoryDTO
import purr.catshop.product.domain.dto.CategoryRequest
import purr.catshop.product.domain.dto.CategoryUpdateRequest
import purr.catshop.product.service.CategoryService
import purr.catshop.product.service.dto.CategoryQueryRequest
import java.lang.Void

@RestController
@RequestMapping(
    value = ["/api/category"],
    produces = [MediaType.APPLICATION_JSON_VALUE],
)
class CategoryResource(
    private val categoryService: CategoryService,
) {
    @GetMapping
    fun getAllCategories(): ResponseEntity<List<CategoryDTO>> = ResponseEntity.ok(categoryService.findAll(request = CategoryQueryRequest()))

    @GetMapping("/{id}")
    fun getCategory(
        @PathVariable(name = "id") id: Long,
    ): ResponseEntity<CategoryDTO> = ResponseEntity.ok(categoryService.findOne(id))

    @PostMapping
    @ApiResponse(responseCode = "201")
    fun createCategory(
        @RequestBody @Valid categoryRequest: CategoryRequest,
    ): ResponseEntity<CategoryDTO> {
        val category = categoryService.create(categoryRequest)
        return ResponseEntity(category.toDTO(), HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun updateCategory(
        @PathVariable(name = "id") id: Long,
        @RequestBody @Valid
        categoryUpdateRequest: CategoryUpdateRequest,
    ): ResponseEntity<CategoryDTO> {
        val category = categoryService.update(id, categoryUpdateRequest)
        return ResponseEntity.ok(category.toDTO())
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    fun deleteCategory(
        @PathVariable(name = "id") id: Long,
    ): ResponseEntity<Void> {
        categoryService.delete(id)
        return ResponseEntity.noContent().build()
    }
}
