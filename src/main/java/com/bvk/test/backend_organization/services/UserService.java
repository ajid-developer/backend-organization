package com.bvk.test.backend_organization.services;

import com.bvk.test.backend_organization.entities.User;
import com.bvk.test.backend_organization.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User save(User user) {
        return userRepository.save(user);
    }
}
