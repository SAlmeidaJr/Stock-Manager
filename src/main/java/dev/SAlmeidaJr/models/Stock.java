package dev.SAlmeidaJr.models;

import java.util.UUID;

import org.springframework.data.annotation.Id;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "stock")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Stock {

    @Id
    @GeneratedValue
    private UUID id;

    private String name;

    private int quantity;


}
