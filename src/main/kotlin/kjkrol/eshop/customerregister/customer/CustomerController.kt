package kjkrol.eshop.customerregister.customer

import kjkrol.eshop.customerregister.customer.create.CustomerCreator
import kjkrol.eshop.customerregister.customer.create.CustomerRegisterRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PagedResourcesAssembler
import org.springframework.hateoas.ExposesResourceFor
import org.springframework.hateoas.MediaTypes
import org.springframework.hateoas.PagedResources
import org.springframework.hateoas.Resource
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping(path = arrayOf("/customer"), produces = arrayOf(MediaTypes.HAL_JSON_VALUE))
@ExposesResourceFor(CustomerDto::class)
internal class CustomerController(val customerCreator: CustomerCreator, val customerQuery: CustomerQuery,
                                  val customerResourceAssembler: CustomerResourceAssembler) {

    @PostMapping
    fun register(@RequestBody register: CustomerRegisterRequest): ResponseEntity<Resource<CustomerDto>> {
        val customer: CustomerDto = customerCreator.register(register)
        val resource: Resource<CustomerDto> = customerResourceAssembler.toResource(customer)
        return ResponseEntity(resource, HttpStatus.OK)
    }

    @GetMapping
    fun findAll(@RequestParam("firstName", required = false, defaultValue = "") firstName: String,
                @RequestParam("lastName", required = false, defaultValue = "") lastName: String,
                pageable: Pageable,
                pagedResourcesAssembler: PagedResourcesAssembler<CustomerDto>?)
            : ResponseEntity<PagedResources<Resource<CustomerDto>>?> {
        val page: Page<CustomerDto> = customerQuery.find(firstName, lastName, pageable)
        when {
            page.none() -> return ResponseEntity(HttpStatus.NOT_FOUND)
            else -> return ResponseEntity(pagedResourcesAssembler?.toResource(page, customerResourceAssembler), HttpStatus.OK)
        }
    }

    @GetMapping("{id}")
    fun findById(@PathVariable id: UUID): ResponseEntity<Resource<CustomerDto>> {
        val customer: CustomerDto = customerQuery.findById(id)
        val resource: Resource<CustomerDto> = customerResourceAssembler.toResource(customer)
        return ResponseEntity(resource, HttpStatus.OK)
    }

}


