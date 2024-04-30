package com.torishop.user;

import com.torishop.user.domain.UserEntity;
import com.torishop.user.dto.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserConverter {
    public User entityToDto(UserEntity userEntity){
        return User.builder()
                .id(userEntity.getId())
                .username(userEntity.getUsername())
                .password(userEntity.getPassword())
                .userRole(userEntity.getUserRole())
                .adminEntity(userEntity.getAdminEntity())
                .customerEntity(userEntity.getCustomerEntity())
                .build();
    }

}
