package com.rs.demolab3.service;

import com.rs.demolab3.dto.PhoneDTO;

import java.util.List;

public interface PhoneService {
    List<PhoneDTO> getAll();

    PhoneDTO getById(Long id);

    void create(PhoneDTO phoneDTO);

    void update(PhoneDTO phoneDTO, Long id);

    void delete(Long id);
}
