package com.torishop.user.service.impl;

import com.torishop.user.UserConverter;
import com.torishop.user.domain.UserEntity;
import com.torishop.user.domain.UserRepository;
import com.torishop.user.dto.*;
import com.torishop.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public User loginUser(LoginRequest request) {
        UserEntity userEntity = Optional.ofNullable(userRepository.findByUsername(request.getUsername()))
                .orElseThrow(() -> new BadCredentialsException("입력 정보를 확인해주세요."));

        if (!passwordEncoder.matches(request.getPassword(), userEntity.getPassword())) {
            throw new BadCredentialsException("비밀번호가 일치하지 않습니다.");
        }

        return UserConverter.entityToDto(userEntity);
    }
}
