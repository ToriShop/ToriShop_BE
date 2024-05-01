package com.torishop.customer.controller;

import com.torishop.customer.dto.CreateCustomerRequest;
import com.torishop.customer.dto.UpdateCustomerRequest;
import com.torishop.customer.service.CustomerService;
import com.torishop.user.dto.User;
import com.torishop.user.dto.UserResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService customerService;
    @PostMapping
    ResponseEntity<UserResponse> createCustomer(@RequestBody CreateCustomerRequest request){
        UserResponse userResponse = customerService.createCustomer(request);
        return ResponseEntity.ok(userResponse);
    }

    @GetMapping("/my")
    ResponseEntity<User> getCustomer(HttpServletRequest httpRequest){
        int id = (int) httpRequest.getAttribute("acId");
        User user = customerService.getCustomer(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping
    ResponseEntity<List<User>> getCustomers(){
        List<User> users = customerService.getCustomers();
        return ResponseEntity.ok(users);
    }

    @PutMapping
    ResponseEntity<UserResponse> updateCustomer(HttpServletRequest httpRequest, @RequestBody UpdateCustomerRequest request){
        int id = (int) httpRequest.getAttribute("acId");
        UserResponse userResponse = customerService.updateCustomer(id, request);
        return ResponseEntity.ok(userResponse);
    }

    @DeleteMapping
    ResponseEntity<UserResponse> deleteCustomer(HttpServletRequest httpRequest){
        int id = (int) httpRequest.getAttribute("acId");
        UserResponse userResponse = customerService.deleteCustomer(id);
        return ResponseEntity.ok(userResponse);
    }
}
