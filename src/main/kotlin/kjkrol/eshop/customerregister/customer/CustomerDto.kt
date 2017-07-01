package kjkrol.eshop.customerregister.customer

import org.springframework.hateoas.core.Relation
import java.util.UUID

@Relation(collectionRelation = "customers")
internal data class CustomerDto(val id: UUID, val firstName: String, val lastName: String)