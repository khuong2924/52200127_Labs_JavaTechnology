package khuong.com.lab8_javatech.controller;

import khuong.com.lab8_javatech.dto.EmployeeDTO;
import khuong.com.lab8_javatech.entity.EmployeeEntity;
import khuong.com.lab8_javatech.repository.EmployeeRepository;
import khuong.com.lab8_javatech.service.EmployeeService;
import khuong.com.lab8_javatech.dto.ResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService emplService;
    private static final Logger logger = Logger.getLogger(EmployeeController.class.getName());

    @Autowired
    private EmployeeRepository employeeRepository;


    public void checkEmployeesData() {
        List<EmployeeEntity> employees = employeeRepository.findAll();
        if (employees.isEmpty()) {
            logger.info("No employees found in the database.");
        } else {
            logger.info("Employee list successfully retrieved from the database. Total records: " + employees.size());
            for (EmployeeEntity employee : employees) {
                logger.info(employee.toString());
            }
        }
    }


    @GetMapping
    public ResponseDTO<List<EmployeeDTO>> getAll() {
        ResponseDTO<List<EmployeeDTO>> responseDTO = new ResponseDTO<>();
        responseDTO.setData(emplService.getAll());
        responseDTO.setStatus(200);
        return responseDTO;
    }

    @PostMapping
    public ResponseDTO<Void> create(@RequestBody @Valid EmployeeDTO employeeDTO) {
        emplService.create(employeeDTO);
        ResponseDTO<Void> response =  ResponseDTO.<Void>builder()
                .status(201)
                .message("Tao thanh cong nhan vien")
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response).getBody();
    }

    @PutMapping ("/{id}")
    public ResponseDTO<Void> update(@PathVariable Long id, @RequestBody @Valid EmployeeDTO employeeDTO) {
        emplService.update(id, employeeDTO);
        return ResponseDTO.<Void>builder()
                .status(201)
                .message("Sua thanh cong nhan vien")
                .build();
    }

    @DeleteMapping("/{id}")
    public ResponseDTO<Void> delete(@PathVariable Long id){
        emplService.delete(id);
        return ResponseDTO.<Void>builder()
                .status(201)
                .message("Xóa thanh cong nhan vien")
                .build();
    }

    @GetMapping("/employees")
    public String getEmployees(Model model) {
        List<EmployeeDTO> employees = emplService.getAll();
        model.addAttribute("employees", employees);
        return "index";
    }

}
