package com.rs.demolab3.controller;

import com.rs.demolab3.dto.ManufactureDTO;
import com.rs.demolab3.dto.ResponseDTO;
import com.rs.demolab3.service.ManufactureService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/manufacture")

public class ManufactureController {
    // controller -> service -> repository -> thao tác với csdl

    @Autowired
    private ManufactureService manufactureService;

    @GetMapping("/all")
    public ResponseDTO<List<ManufactureDTO>> getAll() {
        ResponseDTO<List<ManufactureDTO>> responseDTO = new ResponseDTO<>();
        responseDTO.setData(manufactureService.getAll());
        responseDTO.setStatus(200);
        return responseDTO;
    }

    @GetMapping("/{id}")
    public ResponseDTO<ManufactureDTO> getManufactureById(@PathVariable Long id) {
        ResponseDTO<ManufactureDTO> responseDTO = new ResponseDTO<>();
        responseDTO.setData(manufactureService.getById(id));
        responseDTO.setStatus(200);
        return responseDTO;
    }

    @PostMapping
    public ResponseDTO<Void> create(@RequestBody @Valid ManufactureDTO dto){
        manufactureService.create(dto);
        return ResponseDTO.<Void>builder()
                .status(201)
                .message("Tao thanh cong ")
                .build();
    }

    @PutMapping("/{id}")
    public ResponseDTO<Void> update(@PathVariable  Long id, @RequestBody @Valid ManufactureDTO dto){
        manufactureService.update(dto, id);
        return ResponseDTO.<Void>builder()
                .status(201)
                .message("Sửa thanh cong ")
                .build();
    }

    @DeleteMapping("/{id}")
    public ResponseDTO<Void> delete(@PathVariable Long id){
        manufactureService.delete(id);
        return ResponseDTO.<Void>builder()
                .status(201)
                .message("Xóa thanh cong")
                .build();
    }

}
