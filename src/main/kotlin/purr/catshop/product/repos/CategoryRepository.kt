package purr.catshop.product.repos

import org.springframework.data.jpa.repository.JpaRepository
import purr.catshop.product.domain.Category

interface CategoryRepository : JpaRepository<Category, Long>
