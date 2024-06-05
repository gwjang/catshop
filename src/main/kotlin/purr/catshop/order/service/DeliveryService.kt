package purr.catshop.product.service

import com.querydsl.core.types.PathMetadataFactory
import com.querydsl.core.types.dsl.PathBuilder
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import purr.catshop.base.service.BaseService
import purr.catshop.order.domain.Delivery
import purr.catshop.order.domain.dto.DeliveryDTO
import purr.catshop.order.domain.dto.DeliveryRequest
import purr.catshop.order.domain.dto.DeliveryUpdateRequest
import purr.catshop.order.repos.DeliveryRepository
import purr.catshop.order.service.dto.DeliveryQueryRequest

@Service
class DeliveryService(
    private val deliveryRepository: DeliveryRepository,
) : BaseService<Delivery>(
        entityClassType = Delivery::class.java,
        pathBuilder =
            PathBuilder<Delivery>(
                Delivery::class.java,
                PathMetadataFactory.forVariable("delivery"),
            ),
    ) {
    fun findAll(request: DeliveryQueryRequest): List<DeliveryDTO> {
        return findAll(predicate = request.toPredicate())
            .map { delivery -> delivery.toDTO() }
    }

    fun findOne(id: Long): DeliveryDTO? = findOneById(id)?.toDTO()

    fun findOneElseThrow(id: Long): DeliveryDTO = findOneByIdElseThrow(id).toDTO()

    @Transactional
    fun create(request: DeliveryRequest): Delivery {
        val delivery = Delivery.create(request)
        return deliveryRepository.save(delivery)
    }

    @Transactional
    fun update(
        id: Long,
        request: DeliveryUpdateRequest,
    ): Delivery {
        val delivery = findOneByIdElseThrow(id)
        delivery.update(request = request)
        return delivery
    }

    @Transactional
    fun delete(id: Long) {
        deliveryRepository.deleteById(id)
    }
}
