package customer_service.model;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String street;
    private int number;
    private String neighborhood;
    private String city;
    private String state;
    private String zipCode;

    @OneToMany(mappedBy = "address")
    private Set<Customer> customers = new HashSet<>();

    @CreatedDate
    private LocalDateTime onCreate;

    @LastModifiedDate
    private LocalDateTime onUpdate;

    public Address() {
    }

    public Address(Long id, int number, String neighborhood, String city, String state,
                   String zipCode, Set<Customer> customers, String street) {
        this.id = id;
        this.number = number;
        this.neighborhood = neighborhood;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.customers = customers;
        this.street = street;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public Set<Customer> getCustomers() {
        return customers;
    }

    public LocalDateTime getOnCreate() {
        return onCreate;
    }


    public LocalDateTime getOnUpdate() {
        return onUpdate;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}
