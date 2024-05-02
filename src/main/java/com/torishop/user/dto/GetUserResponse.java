package com.torishop.user.dto;

import com.torishop.admin.dto.Admin;
import com.torishop.customer.dto.Customer;
import com.torishop.user.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetUserResponse {
    private int id;
    private String username;
    private UserRole userRole;
    private Admin admin;
    private Customer customer;
}
