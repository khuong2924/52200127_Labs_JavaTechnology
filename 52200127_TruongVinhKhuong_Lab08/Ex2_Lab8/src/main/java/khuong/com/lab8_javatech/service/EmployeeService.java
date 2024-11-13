package khuong.com.lab8_javatech.service;

import khuong.com.lab8_javatech.dto.EmployeeDTO;
import khuong.com.lab8_javatech.entity.EmployeeEntity;
import khuong.com.lab8_javatech.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepo;

    // controller -> service -> repository -> thao tác với csdl
    private List<EmployeeDTO> mapToDto(List<EmployeeEntity> listEntity) {
        List<EmployeeDTO> list = new ArrayList<>();
        for (EmployeeEntity entity : listEntity) {
            list.add(new EmployeeDTO(entity.getId(), entity.getName(), entity.getEmail(), entity.getPhone(), entity.getAddress()));
        }
        return list;
    }

    public List<EmployeeDTO> getAll() {
        List<EmployeeEntity> employees = employeeRepo.findAll();
        return mapToDto(employees);
    }

    public void create(EmployeeDTO employeeDTO) {
        EmployeeEntity empl = new EmployeeEntity();
        empl.setName(employeeDTO.getName());
        empl.setEmail(employeeDTO.getEmail());
        empl.setPhone(employeeDTO.getPhone());
        empl.setAddress(employeeDTO.getAddress());
        employeeRepo.save(empl);
    }
    public void update(Long id, EmployeeDTO employeeDTO) {
        EmployeeEntity empl = employeeRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Khong tim thay nhan vien"));

        empl.setName(employeeDTO.getName());
        empl.setEmail(employeeDTO.getEmail());
        empl.setPhone(employeeDTO.getPhone());
        empl.setAddress(employeeDTO.getAddress());

        employeeRepo.save(empl);
    }


    public EmployeeDTO getById(Long id) {
        EmployeeEntity empl = employeeRepo.findById(id).orElse(null);
        if (empl == null) {
            throw new RuntimeException("Khong tim thay nhan vien");
        }
        return new EmployeeDTO(empl.getId(), empl.getName(), empl.getEmail(), empl.getAddress(), empl.getPhone());
    }

    public void delete(Long id) {
        employeeRepo.findById(id).orElseThrow();
        employeeRepo.deleteById(id);
    }
}
