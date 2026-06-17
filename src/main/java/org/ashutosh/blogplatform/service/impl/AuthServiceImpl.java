package org.ashutosh.blogplatform.service.impl;

import lombok.RequiredArgsConstructor;
import org.ashutosh.blogplatform.dto.LoginRequest;
import org.ashutosh.blogplatform.dto.RegisterRequest;
import org.ashutosh.blogplatform.entity.Role;
import org.ashutosh.blogplatform.entity.User;
import org.ashutosh.blogplatform.repository.UserRepository;
import org.ashutosh.blogplatform.service.AuthService;
import org.ashutosh.blogplatform.utils.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl
        implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;

    @Override
    public void register(RegisterRequest request) {

        if(userRepository.findByEmail(request.getEmail())
                .isPresent()) {

            throw new RuntimeException(
                    "Email already exists");
        }

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(
                        passwordEncoder.encode(
                                request.getPassword()))
                .role(Role.USER)
                .build();

        userRepository.save(user);
    }

    @Override
    public String login(LoginRequest request) {

        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()));

        return jwtUtil.GenerateToken(
                request.getEmail());
    }
}
