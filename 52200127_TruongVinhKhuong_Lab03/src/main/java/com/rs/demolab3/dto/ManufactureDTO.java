package com.rs.demolab3.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ManufactureDTO {
    private Long id;
    private String name;
    private String location;
    private int Employee;
}
