package kjkrol.eshop.customerregister

import kjkrol.eshop.customerregister.customer.create.CustomerCreator
import kjkrol.eshop.customerregister.customer.create.CustomerRegisterRequest
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient
import org.springframework.context.annotation.Bean
import springfox.documentation.swagger2.annotations.EnableSwagger2


fun main(args: Array<String>) {
    SpringApplication.run(CustomerRegisterApp::class.java, *args)
}

@EnableSwagger2
@EnableEurekaClient
@SpringBootApplication
open class CustomerRegisterApp {

    companion object {
        val log: Logger = LoggerFactory.getLogger(CustomerRegisterApp::class.java)
    }

    @Bean
    internal fun init(customerCreator: CustomerCreator): CommandLineRunner {
        log.info("populate database with initial data")
        return CommandLineRunner {
            customerCreator.register(CustomerRegisterRequest(firstName = "Karol", lastName = "Krol"))
            customerCreator.register(CustomerRegisterRequest(firstName = "Jan", lastName = "Kowalski"))
            customerCreator.register(CustomerRegisterRequest(firstName = "Karol", lastName = "Nowak"))
            customerCreator.register(CustomerRegisterRequest(firstName = "Andrzej", lastName = "Kowalski"))
            customerCreator.register(CustomerRegisterRequest(firstName = "Anna", lastName = "Nowak"))
        }
    }

}


