package dev.SAlmeidaJr.StockManager.models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "customers")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    private String password;

    private String phoneNumber;

    @OneToMany(mappedBy = "customer")
    private List<Order> orders = new ArrayList<>();


    public Customer(String name, String email, String password){
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
