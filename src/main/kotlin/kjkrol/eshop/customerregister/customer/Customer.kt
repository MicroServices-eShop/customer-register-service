package kjkrol.eshop.customerregister.customer

import org.hibernate.annotations.Type
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.query.Param
import java.util.UUID
import java.util.UUID.randomUUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "customers")
data class Customer(@Id @Type(type = "uuid-char") val id: UUID = randomUUID(),
                    @Column(name = "firstname") val firstName: String,
                    @Column(name = "lastname") val lastName: String)

interface CustomerRepository : JpaRepository<Customer, UUID> {
    fun findByFirstNameAndLastName(@Param("firstName") firstName: String,
                                   @Param("lastName") lastName: String, pageable: Pageable): Page<Customer>
    fun findByFirstName(firstName: String, pageable: Pageable): Page<Customer>
    fun findByLastName(lastName: String, pageable: Pageable): Page<Customer>
}