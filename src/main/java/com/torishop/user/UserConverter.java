package com.torishop.user;

import com.torishop.admin.AdminConverter;
import com.torishop.customer.CustomerConverter;
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
                .admin(AdminConverter.entityToDto(userEntity.getAdminEntity()))
                .customer(CustomerConverter.entityToDto(userEntity.getCustomerEntity()))
                .build();
    }
}
