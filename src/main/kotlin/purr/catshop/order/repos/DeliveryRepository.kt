package purr.catshop.order.repos

import org.springframework.data.jpa.repository.JpaRepository
import purr.catshop.order.domain.Delivery

interface DeliveryRepository : JpaRepository<Delivery, Long>
