package khuong.com.lab9_10.service;

import khuong.com.lab9_10.entity.UserAccount;
import khuong.com.lab9_10.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserAccountRepository userAccountRepository;


    private PasswordEncoder passwordEncoder;

    public UserAccount register(UserAccount user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userAccountRepository.save(user);
    }


}
