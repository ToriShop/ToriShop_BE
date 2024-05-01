package com.torishop.admin.service.impl;

import com.torishop.admin.domain.AdminEntity;
import com.torishop.admin.domain.AdminRepository;
import com.torishop.admin.dto.CreateAdminRequest;
import com.torishop.admin.service.AdminService;
import com.torishop.user.UserConverter;
import com.torishop.user.domain.UserEntity;
import com.torishop.user.domain.UserRepository;
import com.torishop.user.dto.User;
import com.torishop.user.dto.UserResponse;
import com.torishop.user.enums.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final AdminRepository adminRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserResponse createAdmin(CreateAdminRequest request) {
        AdminEntity admin = AdminEntity.builder()
                .code(request.getCode())
                .build();
        AdminEntity savedAdmin = adminRepository.save(admin);

        UserEntity userEntity = UserEntity.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .adminEntity(savedAdmin)
                .userRole(UserRole.ADMIN)
                .build();
        userRepository.save(userEntity);

        return UserResponse.builder()
                .id(userEntity.getId())
                .build();
    }

    @Override
    @Transactional
    public User getAdmin(int id) {
        UserEntity userEntity = userRepository.findByAdminEntityId(id);
        return UserConverter.entityToDto(userEntity);
    }

    @Override
    @Transactional
    public List<User> getAdmins() {
        List<UserEntity> userEntities = userRepository.findByUserRole(UserRole.ADMIN);
        return userEntities.stream().map(UserConverter::entityToDto).toList();
    }
}
