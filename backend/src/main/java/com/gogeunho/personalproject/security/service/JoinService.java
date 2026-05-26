package com.gogeunho.personalproject.security.service;

import com.gogeunho.personalproject.security.dto.JoinDTO;
import com.gogeunho.personalproject.user.entity.User;
import com.gogeunho.personalproject.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class JoinService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public JoinService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public void joinProcess(JoinDTO joinDTO) {

        boolean isExist =userRepository.existsByUsername(joinDTO.getUsername());

        if (isExist) {
            log.info("[SECURITY] username already exist");
            return;
        }

        User user = new User();
        user.setUsername(joinDTO.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(joinDTO.getPassword()));
        user.setRole("ROLE_ADMIN");

        log.info("[SECURITY] new user: " + joinDTO.getUsername());

        userRepository.save(user);
    }
}
