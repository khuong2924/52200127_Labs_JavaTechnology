package com.rs.demolab3.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 3, max = 128)
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private String color;

    private String country;

    private int quantity;


    @ManyToOne
    @JoinColumn(name = "manufacture_id", nullable = true)
    private Manufacture manufacture;
}
