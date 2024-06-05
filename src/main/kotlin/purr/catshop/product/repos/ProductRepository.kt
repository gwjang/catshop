package purr.catshop.product.repos

import org.springframework.data.jpa.repository.JpaRepository
import purr.catshop.product.domain.Product

interface ProductRepository : JpaRepository<Product, Long>
