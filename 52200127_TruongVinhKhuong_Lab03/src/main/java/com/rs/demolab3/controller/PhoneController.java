package com.rs.demolab3.controller;

import com.rs.demolab3.dto.PhoneDTO;
import com.rs.demolab3.dto.ResponseDTO;
import com.rs.demolab3.service.PhoneService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/phone")
public class PhoneController {
    // controller -> service -> repository -> thao tác với csdl

    @Autowired
    private PhoneService phoneService;

    @GetMapping("/all")
    public ResponseDTO<List<PhoneDTO>> getAll() {
        ResponseDTO<List<PhoneDTO>> responseDTO = new ResponseDTO<>();
        responseDTO.setData(phoneService.getAll());
        responseDTO.setStatus(200);
        return responseDTO;
    }

    @GetMapping("/{id}")
    public ResponseDTO<PhoneDTO> getPhoneById(@PathVariable Long id) {
        ResponseDTO<PhoneDTO> responseDTO = new ResponseDTO<>();
        responseDTO.setData(phoneService.getById(id));
        responseDTO.setStatus(200);
        return responseDTO;
    }

    @PostMapping
    public ResponseDTO<Void> create(@RequestBody @Valid PhoneDTO dto){
        phoneService.create(dto);
        return ResponseDTO.<Void>builder()
                .status(201)
                .message("Tao thanh cong ")
                .build();
    }

    @PutMapping("/{id}")
    public ResponseDTO<Void> update(@PathVariable  Long id, @RequestBody @Valid PhoneDTO dto){
        phoneService.update(dto, id);
        return ResponseDTO.<Void>builder()
                .status(201)
                .message("Sửa thanh cong ")
                .build();
    }

    @DeleteMapping("/{id}")
    public ResponseDTO<Void> delete(@PathVariable Long id){
        phoneService.delete(id);
        return ResponseDTO.<Void>builder()
                .status(201)
                .message("Xóa thanh cong")
                .build();
    }

}




