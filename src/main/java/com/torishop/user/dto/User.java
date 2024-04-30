package com.torishop.user.dto;

import com.torishop.admin.domain.AdminEntity;
import com.torishop.customer.domain.CustomerEntity;
import com.torishop.user.domain.UserEntity;
import com.torishop.user.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int id;
    private String username;
    private String password;
    private UserRole userRole;
    private AdminEntity adminEntity;
    private CustomerEntity customerEntity;

    public UserEntity toEntity(){
        return UserEntity.builder()
                .id(this.id)
                .username(this.username)
                .password(this.password)
                .userRole(this.userRole)
                .adminEntity(this.adminEntity)
                .customerEntity(this.customerEntity)
                .build();
    }
}
