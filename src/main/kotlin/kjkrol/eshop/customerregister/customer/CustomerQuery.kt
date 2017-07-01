package kjkrol.eshop.customerregister.customer

import kjkrol.eshop.customerregister.customer.util.toDto
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.NoSuchElementException
import java.util.Optional.ofNullable
import java.util.UUID

@Service
internal open class CustomerQuery(val customerRepository: CustomerRepository) {

    @Transactional(readOnly = true)
    fun findById(id: UUID): CustomerDto = ofNullable(customerRepository
            .findOne(id))
            .orElseThrow { NoSuchElementException() }.
            toDto()

    @Transactional(readOnly = true)
    fun find(firstName: String, lastName: String, pageable: Pageable): Page<CustomerDto> = when {
        firstName.isNotBlank() && lastName.isNotBlank() -> findCustomerByFirstNameAndLastName(firstName, lastName, pageable)
        firstName.isNotBlank() -> findCustomerByFirstName(firstName, pageable)
        lastName.isNotBlank() -> findCustomerByLastName(lastName, pageable)
        else -> findAll(pageable)
    }

    private fun findAll(pageable: Pageable): Page<CustomerDto> = customerRepository
            .findAll(pageable)
            .map { it.toDto() }

    private fun findCustomerByFirstNameAndLastName(firstName: String, lastName: String, pageable: Pageable): Page<CustomerDto> = customerRepository
            .findByFirstNameAndLastName(firstName, lastName, pageable)
            .map { it.toDto() }

    private fun findCustomerByFirstName(firstName: String, pageable: Pageable): Page<CustomerDto> = customerRepository
            .findByFirstName(firstName, pageable)
            .map { it.toDto() }

    private fun findCustomerByLastName(lastName: String, pageable: Pageable): Page<CustomerDto> = customerRepository
            .findByLastName(lastName, pageable)
            .map { it.toDto() }

}