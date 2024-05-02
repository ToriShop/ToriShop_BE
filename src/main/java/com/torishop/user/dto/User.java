package com.torishop.user.dto;

import com.torishop.admin.domain.AdminEntity;
import com.torishop.admin.dto.Admin;
import com.torishop.customer.domain.CustomerEntity;
import com.torishop.customer.dto.Customer;
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
    private Admin admin;
    private Customer customer;
}
