package com.rs.demolab3.service.iplm;

import com.rs.demolab3.dto.PhoneDTO;
import com.rs.demolab3.entity.Phone;
import com.rs.demolab3.repository.PhoneRepository;
import com.rs.demolab3.service.PhoneService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PhoneServiceIplm implements PhoneService {
    private final PhoneRepository phoneRepository;

    @Override
    public List<PhoneDTO> getAll() {
        List<Phone> listEntity = phoneRepository.findAll();
        return mapToDto(listEntity);
    }

    @Override
    public PhoneDTO getById(Long id) {
        Phone phone = phoneRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Phone not found with id: " + id));

        // Trả về PhoneDTO nếu tìm thấy
        return new PhoneDTO(phone.getId(), phone.getName(), phone.getPrice(), phone.getColor(), phone.getCountry(), phone.getQuantity());
    }

    @Override
    public void create(PhoneDTO phoneDTO) {
        Phone phone = new Phone();
        phone.setName(phoneDTO.getName());
        phone.setPrice(phoneDTO.getPrice());
        phone.setColor(phoneDTO.getColor());
        phone.setCountry(phoneDTO.getCountry());
        phone.setQuantity(phoneDTO.getQuantity());
        phoneRepository.save(phone);

    }

    @Override
    public void update(PhoneDTO phoneDTO, Long id) {
        Phone entity = new Phone();
        entity.setName(phoneDTO.getName());
        entity.setId(id);
        phoneRepository.save(entity); // có cả id và tên
    }

    @Override
    public void delete(Long id) {
        phoneRepository.deleteById(id);
    }

    private List<PhoneDTO> mapToDto(List<Phone> listEntity){
        List<PhoneDTO> list = new ArrayList<>();
        for(Phone entity : listEntity) {
            list.add(new PhoneDTO(entity.getId(), entity.getName(), entity.getPrice(), entity.getColor(), entity.getCountry(), entity.getQuantity()));
        }
        return list;
    }
}
