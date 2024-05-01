package com.torishop.customer.service;

import com.torishop.customer.dto.CreateCustomerRequest;
import com.torishop.customer.dto.Customer;
import com.torishop.customer.dto.UpdateCustomerRequest;
import com.torishop.user.dto.User;
import com.torishop.user.dto.UserResponse;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    UserResponse createCustomer(CreateCustomerRequest request);
    User getCustomer(int id);
    List<User> getCustomers();
    UserResponse updateCustomer(int id, UpdateCustomerRequest request);
    UserResponse deleteCustomer(int id);
}
