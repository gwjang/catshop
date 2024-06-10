package purr.catshop.product.service

import com.querydsl.core.types.PathMetadataFactory
import com.querydsl.core.types.dsl.PathBuilder
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import purr.catshop.base.service.BaseService
import purr.catshop.order.domain.Order
import purr.catshop.order.domain.dto.OrderDTO
import purr.catshop.order.domain.dto.OrderRequest
import purr.catshop.order.domain.dto.OrderUpdateRequest
import purr.catshop.order.repos.OrderRepository
import purr.catshop.order.service.dto.OrderQueryRequest

@Service
class OrderService(
    private val orderRepository: OrderRepository,
) : BaseService<Order>(
        entityClassType = Order::class.java,
        pathBuilder =
            PathBuilder<Order>(
                Order::class.java,
                PathMetadataFactory.forVariable("order"),
            ),
    ) {
    fun findAll(request: OrderQueryRequest): List<OrderDTO> {
        return findAll(predicate = request.toPredicate())
            .map { order -> order.toDTO() }
    }

    fun findOne(id: Long): OrderDTO? = findOneById(id)?.toDTO()

    fun findOneElseThrow(id: Long): OrderDTO = findOneByIdElseThrow(id).toDTO()

    @Transactional
    fun create(request: OrderRequest): Order {
        val order =
            Order.create(
                status = request.status,
                orderProductRequests = request.orderProductRequests,
                customerId = request.customerId,
                payment = request.payment,
            )
        return orderRepository.save(order)
    }

    @Transactional
    fun update(
        id: Long,
        request: OrderUpdateRequest,
    ): Order {
        val order = findOneByIdElseThrow(id)
        order.update(
            status = request.status,
            customerId = request.customerId,
            delivery = request.delivery,
            payment = request.payment,
        )
        return order
    }

    @Transactional
    fun delete(id: Long) {
        orderRepository.deleteById(id)
    }
}
