package com.example.foodywoody.service;

import com.example.foodywoody.dto.RegisterForm;
import com.example.foodywoody.entity.User;
import java.util.List;
import java.util.Optional;

public interface UserService {
    User register(RegisterForm form);
    Optional<User> findByEmail(String email);
    List<User> findAll();
}
