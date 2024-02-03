package com.example.taskmanager.service;

import com.example.taskmanager.model.Role;
import com.example.taskmanager.model.User;
import com.example.taskmanager.repository.UserRepository;
import com.example.taskmanager.service.security.UserDetailsServiceImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsServiceImpl userDetailsService;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, UserDetailsServiceImpl userDetailsService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public void register(User user){

        User userReg = User.builder()
                .name(user.getName())
                .surname(user.getSurname())
                .patronymic(user.getPatronymic())
                .email(user.getEmail())
                .login(user.getLogin())
                .password(passwordEncoder.encode(user.getPassword()))
                .registrationDate(LocalDateTime.now())
                .role(Role.ROLE_CLIENT)
                .build();

        userRepository.save(userReg);
    }

    @Override
    public User getAuthUser() {
        return userDetailsService.getAuthUser();
    }

    @Override
    public void updateUser(User userOld, User userUpdate) {

        userOld.setName(userUpdate.getName());
        userOld.setSurname(userUpdate.getSurname());
        userOld.setPatronymic(userUpdate.getPatronymic());
        userOld.setEmail(userUpdate.getEmail());

        userRepository.save(userOld);
    }
}
