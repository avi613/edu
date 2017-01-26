package hello;

import com.google.common.collect.ImmutableList;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@EnableAutoConfiguration
public class CustomerRepositoryTest {
    @Autowired
    private CustomerRepository repository;

    @Before
    public void setUp() {
        repository.deleteAll();

        repository.save(new Customer("Bob", "Dylan"));
        repository.save(new Customer("Robert", "Dylan"));
    }

    @Test
    public void should_find_all_customers() {
        List<Customer> customers = ImmutableList.of(
                new Customer("Bob", "Dylan"),
                new Customer("Robert", "Dylan"));
        customers.stream().forEach(
                customer -> assertThat(repository.findAll())
                        .usingElementComparatorIgnoringFields("id")
                        .contains(customer));
    }
}
