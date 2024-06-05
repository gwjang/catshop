package purr.catshop.customer.repos

import org.springframework.data.jpa.repository.JpaRepository
import purr.catshop.customer.domain.Customer


interface CustomerRepository : JpaRepository<Customer, Long>
