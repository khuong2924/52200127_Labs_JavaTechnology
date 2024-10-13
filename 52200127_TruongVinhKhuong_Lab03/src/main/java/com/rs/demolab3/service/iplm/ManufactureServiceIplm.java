package com.rs.demolab3.service.iplm;

import com.rs.demolab3.dto.ManufactureDTO;
import com.rs.demolab3.entity.Manufacture;
import com.rs.demolab3.repository.ManufactureRepository;
import com.rs.demolab3.service.ManufactureService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ManufactureServiceIplm implements ManufactureService {
    private final ManufactureRepository manufactureRepository;

    @Override
    public List<ManufactureDTO> getAll() {
        List<Manufacture> listEntity = manufactureRepository.findAll();
        return mapToDto(listEntity);
    }

    @Override
    public ManufactureDTO getById(Long id) {
        Manufacture manufacture = manufactureRepository.findById(id).orElse(null);
        return new ManufactureDTO(manufacture.getId(), manufacture.getName(), manufacture.getLocation(), manufacture.getEmployee());
    }

    @Override
    public void create(ManufactureDTO manufactureDTO) {
        Manufacture manufacture = new Manufacture();
        manufacture.setName(manufactureDTO.getName());
        manufacture.setLocation(manufactureDTO.getLocation());
        manufacture.setEmployee(manufactureDTO.getEmployee());
        manufactureRepository.save(manufacture);

    }

    @Override
    public void update(ManufactureDTO manufactureDTO, Long id) {
        Manufacture entity = new Manufacture();
        entity.setId(manufactureDTO.getId());
        entity.setId(id);
        manufactureRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        manufactureRepository.deleteById(id);
    }

    private List<ManufactureDTO> mapToDto(List<Manufacture> listEntity){
        List<ManufactureDTO> list = new ArrayList<>();
        for(Manufacture entity : listEntity) {
            list.add(new ManufactureDTO(entity.getId(), entity.getName(), entity.getLocation(), entity.getEmployee()));
        }
        return list;
    }

    public boolean checkTotalEmployee() {
        List<ManufactureDTO> list = mapToDto(manufactureRepository.findAll());
        for (ManufactureDTO man : list) {
            if (man.getEmployee() < 100)
                return false;
        }
        return true;
    }
    public long totalEmployee() {
        int total = 0;
        List<ManufactureDTO> list = mapToDto(manufactureRepository.findAll());
        for (ManufactureDTO man : list) {
            total += man.getEmployee();
        }
        return total;

    }
}
