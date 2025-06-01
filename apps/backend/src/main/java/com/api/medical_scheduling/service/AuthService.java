package com.api.medical_scheduling.service;

import com.api.medical_scheduling.dto.AuthRequestDTO;
import com.api.medical_scheduling.dto.UserRequestDTO;
import com.api.medical_scheduling.exception.InvalidLoginException;
import com.api.medical_scheduling.model.User;
import com.api.medical_scheduling.repository.UserRepository;
import com.api.medical_scheduling.validations.NewUserValidator;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final List<NewUserValidator> newUserValidators;


    public String authenticate(@NotNull AuthRequestDTO authRequestDTO) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequestDTO.getUsername(), authRequestDTO.getPassword())
            );
        } catch (AuthenticationException e) {
            throw new InvalidLoginException("Invalid login or password");
        }

        final User user = (User) userDetailsService
                .loadUserByUsername(authRequestDTO.getUsername());

        return jwtService.generateToken(user);
    }

    public User register(@NotNull UserRequestDTO newUserRequestDTO) {
        validateNewUser(newUserRequestDTO);

        User user = User
                .builder()
                .name(newUserRequestDTO.getName())
                .email(newUserRequestDTO.getEmail())
                .username(newUserRequestDTO.getUsername())
                .password(passwordEncoder.encode(newUserRequestDTO.getPassword()))
                .medicProfile(newUserRequestDTO.getMedicProfile())
                .admin(false)
                .build();

        return userRepository.save(user);
    }

    public void validateNewUser(@NotNull UserRequestDTO newUserRequestDTO) {
        newUserValidators.forEach(validator -> validator.validate(newUserRequestDTO));
    }
}
