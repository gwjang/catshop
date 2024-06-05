package purr.catshop.customer.service

import com.querydsl.core.types.PathMetadataFactory
import com.querydsl.core.types.dsl.PathBuilder
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import purr.catshop.base.service.BaseService
import purr.catshop.customer.domain.Customer
import purr.catshop.customer.domain.dto.CustomerDTO
import purr.catshop.customer.domain.dto.CustomerRequest
import purr.catshop.customer.domain.dto.CustomerUpdateRequest
import purr.catshop.customer.repos.CustomerRepository
import purr.catshop.customer.service.dto.CustomerQueryRequest

@Service
class CustomerService(
    private val customerRepository: CustomerRepository,
) : BaseService<Customer>(
        entityClassType = Customer::class.java,
        pathBuilder =
            PathBuilder<Customer>(
                Customer::class.java,
                PathMetadataFactory.forVariable("customer"),
            ),
    ) {
    fun findAll(request: CustomerQueryRequest): List<CustomerDTO> {
        return findAll(predicate = request.toPredicate())
            .map { customer -> customer.toDTO() }
    }

    fun findOne(id: Long): CustomerDTO? = findOneById(id)?.toDTO()

    fun findOneElseThrow(id: Long): CustomerDTO = findOneByIdElseThrow(id).toDTO()

    @Transactional
    fun create(request: CustomerRequest): Customer {
        val customer = Customer.create(request)
        return customerRepository.save(customer)
    }

    @Transactional
    fun update(
        id: Long,
        request: CustomerUpdateRequest,
    ): Customer {
        val customer = findOneByIdElseThrow(id)
        customer.update(request = request)
        return customer
    }

    @Transactional
    fun delete(id: Long) {
        customerRepository.deleteById(id)
    }
}
