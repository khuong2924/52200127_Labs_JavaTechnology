package com.rs.demolab3.component;

import com.rs.demolab3.entity.Manufacture;
import com.rs.demolab3.entity.Phone;
import com.rs.demolab3.repository.ManufactureRepository;
import com.rs.demolab3.repository.PhoneRepository;
import com.rs.demolab3.service.iplm.ManufactureServiceIplm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;

import java.util.ArrayList;
import java.util.List;

public class Program {
    private PhoneRepository phoneRepo;
    private ManufactureServiceIplm manufactureSv;

    public void run(String... args) throws Exception {
        List<Phone> phones = new ArrayList<>();
        Manufacture manufacture = new Manufacture();
        Phone phone1 = new Phone(1L, "iphone", 123, "black", "USA", 1, manufacture);
        phones.add(phone1);
        Manufacture manu1 = new Manufacture(1L, "apple", "ameri", 99, phones);
        System.out.println("check");
        if (manufactureSv.checkTotalEmployee()) {
            System.out.println("Manufacture < 100 employees");
        }
        else {
            System.out.println("Manufacture > 100 employees");
        }
    }
}
