package kjkrol.eshop.customerregister.customer.util

import kjkrol.eshop.customerregister.customer.Customer
import kjkrol.eshop.customerregister.customer.CustomerDto
import kjkrol.eshop.customerregister.customer.create.CustomerRegisterRequest

internal fun Customer.toDto(): CustomerDto = CustomerDto(id = this.id, firstName = this.firstName, lastName = this.lastName)

internal fun CustomerRegisterRequest.toEntity(): Customer = Customer(firstName = this.firstName, lastName = this.lastName)
