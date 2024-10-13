package com.rs.demolab3.service;

import com.rs.demolab3.dto.ManufactureDTO;
import com.rs.demolab3.dto.PhoneDTO;

import java.util.List;

public interface ManufactureService {
    List<ManufactureDTO> getAll();

    ManufactureDTO getById(Long id);

    void create(ManufactureDTO manufactureDTO);

    void update(ManufactureDTO manufactureDTO, Long id);

    void delete(Long id);
}
