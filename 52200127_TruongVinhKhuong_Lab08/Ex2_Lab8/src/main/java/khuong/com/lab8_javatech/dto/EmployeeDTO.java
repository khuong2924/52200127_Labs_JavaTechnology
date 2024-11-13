package khuong.com.lab8_javatech.dto;

import lombok.*;

@Getter
@Setter
//phthuc khoi tao:
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {
    private Long id;
    private String name;
    private String email;
    private String address;
    private String phone;
}
