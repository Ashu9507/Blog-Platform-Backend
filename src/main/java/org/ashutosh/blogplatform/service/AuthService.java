package org.ashutosh.blogplatform.service;

import org.ashutosh.blogplatform.dto.LoginRequest;
import org.ashutosh.blogplatform.dto.RegisterRequest;

public interface AuthService {

    void register(RegisterRequest request);
    String login(LoginRequest request);
}
