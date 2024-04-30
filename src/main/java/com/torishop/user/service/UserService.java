package com.torishop.user.service;

import com.torishop.user.dto.*;

public interface UserService {
    User loginUser(LoginRequest request);
}
