package com.rs.demolab3.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PhoneDTO {
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

}
