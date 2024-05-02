package com.torishop.user.service;

import com.torishop.user.dto.*;

public interface UserService {
    LoginResponse loginUser(LoginRequest request);
}
