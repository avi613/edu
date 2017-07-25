package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Collection;

import static java.util.Arrays.asList;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    HealthIndicator heaalthIndicator() {
        return () -> Health.status("I <3 my family").build();
    }

    @Bean
    CommandLineRunner runner(ReservationRepository reservationRepository) {
        return args -> {
            asList("Avi,Pam,Chalom,Liora,Meir,Perla".split(","))
                    .forEach(name -> reservationRepository.save(new Reservation(name)));
            reservationRepository.findAll().forEach(System.out::println);
        };
    }

}

@Component
class ReservationResourceProcessor implements ResourceProcessor<Resource<Reservation>> {
    @Override
    public Resource<Reservation> process(Resource<Reservation> reservationResource) {
        reservationResource.add(new Link("http://s3.com/imgs/" + reservationResource.getContent().getId() + ".jpg", "profile-photo"));
        return reservationResource;
    }
}

@RepositoryRestResource
interface ReservationRepository extends JpaRepository<Reservation, Long> {
    Collection<Reservation> findByReservationName(@Param("reservationName") String reservationName);
}

@Entity
class Reservation {
    @Id
    @GeneratedValue
    private Long id;

    private String reservationName;

    Reservation() {
    }

    public Reservation(String reservationName) {
        this.reservationName = reservationName;
    }

    public Long getId() {
        return id;
    }

    public String getReservationName() {
        return reservationName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setReservationName(String reservationName) {
        this.reservationName = reservationName;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", reservationName='" + reservationName + '\'' +
                '}';
    }
}