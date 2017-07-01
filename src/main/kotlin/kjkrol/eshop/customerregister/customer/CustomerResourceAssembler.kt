package kjkrol.eshop.customerregister.customer

import org.springframework.hateoas.EntityLinks
import org.springframework.hateoas.Link
import org.springframework.hateoas.Resource
import org.springframework.hateoas.ResourceAssembler
import org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo
import org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn
import org.springframework.stereotype.Component

@Component
internal class CustomerResourceAssembler(val entityLinks: EntityLinks) : ResourceAssembler<CustomerDto, Resource<CustomerDto>> {
    override fun toResource(customer: CustomerDto): Resource<CustomerDto> {
        val selfLink: Link = linkTo(methodOn(CustomerController::class.java).findById(customer.id)).withSelfRel()
        val allCustomersLink = entityLinks.linkToCollectionResource(CustomerDto::class.java).withRel("all-customers")
//        @Suppress("INTERFACE_STATIC_METHOD_CALL_FROM_JAVA6_TARGET")
//        val allOrdersLink: Link = linkTo(methodOn(OrderController::class.java).findCustomerOrders(customerId = customer.id,
//                pageable = Pageable.unpaged(),
//                pagedResourcesAssembler = null
//        )).withRel("all-orders")
        return Resource(customer, selfLink, allCustomersLink)
    }
}