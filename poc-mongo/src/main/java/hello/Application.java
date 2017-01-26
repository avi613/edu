package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {
    @Autowired
    private CustomerRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        repository.deleteAll();

        repository.save(new Customer("Bob", "Dylan"));
        repository.save(new Customer("Robert", "Dylan"));

        System.out.println("Customers found with findAll():");
        System.out.println("-------------------------------");
        repository.findAll().forEach(
                customer -> System.out.println(customer)
        );
        System.out.println();

        // fetch an individual customer
        System.out.println("Customer found with findByFirstName('Bob'):");
        System.out.println("--------------------------------");
        System.out.println(repository.findByFirstName("Bob"));

        System.out.println("Customers found with findByLastName('Dylan'):");
        System.out.println("--------------------------------");
        repository.findByLastName("Dylan").forEach(
                customer -> System.out.println(customer)
        );
    }
}
