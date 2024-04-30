package com.torishop.admin.service;

import com.torishop.admin.dto.Admin;
import com.torishop.admin.dto.CreateAdminRequest;
import com.torishop.user.dto.User;
import com.torishop.user.dto.UserResponse;

import java.util.List;
import java.util.Optional;

public interface AdminService {
    UserResponse createAdmin(CreateAdminRequest request);
    User getAdmin(int id);
    List<User> getAdmins();
}
