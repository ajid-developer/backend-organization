package com.bvk.test.backend_organization.services;

import com.bvk.test.backend_organization.entities.User;
import com.bvk.test.backend_organization.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByEmail(username);
    }
}
