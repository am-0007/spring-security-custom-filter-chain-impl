package com.springsecuirty2023.service;

import com.springsecuirty2023.UserMapper;
import com.springsecuirty2023.entities.UserSecurity;
import com.springsecuirty2023.entities.dto.UserDto;
import com.springsecuirty2023.repo.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .map(UserSecurity::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }


    public UserDto registerUser(UserDto userDto) {
        return Optional.of(userDto)
                .map(UserMapper.USER_MAPPER_INSTANCE::toUser)
                .map(user -> {
                    user.setPassword(passwordEncoder.encode(userDto.getPassword()));
                    return userRepository.save(user);
                })
                .map(UserMapper.USER_MAPPER_INSTANCE::toUserDto)
                .orElseThrow(() -> new RuntimeException("Username already exists!!"));
    }
}
