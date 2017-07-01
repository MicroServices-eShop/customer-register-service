package kjkrol.eshop.customerregister.customer.create

import kjkrol.eshop.customerregister.customer.Customer
import kjkrol.eshop.customerregister.customer.CustomerDto
import kjkrol.eshop.customerregister.customer.CustomerRepository
import kjkrol.eshop.customerregister.customer.util.toDto
import kjkrol.eshop.customerregister.customer.util.toEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
internal open class CustomerCreator(val customerRepository: CustomerRepository) {

    @Transactional
    fun register(registerRequest: CustomerRegisterRequest): CustomerDto {
        val customer: Customer = customerRepository.save(registerRequest.toEntity())
        return customer.toDto()
    }

}