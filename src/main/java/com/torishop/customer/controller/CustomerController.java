package com.torishop.customer.controller;

import com.torishop.customer.dto.CreateCustomerRequest;
import com.torishop.customer.dto.UpdateCustomerRequest;
import com.torishop.customer.service.CustomerService;
import com.torishop.user.dto.GetUserResponse;
import com.torishop.user.dto.UpdatePwRequest;
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
    ResponseEntity<GetUserResponse> getCustomer(HttpServletRequest httpRequest){
        int id = (int) httpRequest.getAttribute("acId");
        GetUserResponse getUserResponse = customerService.getCustomer(id);
        return ResponseEntity.ok(getUserResponse);
    }

    @GetMapping
    ResponseEntity<List<GetUserResponse>> getCustomers(){
        List<GetUserResponse> getUserResponses = customerService.getCustomers();
        return ResponseEntity.ok(getUserResponses);
    }

    @PutMapping("/pw")
    ResponseEntity<UserResponse> updateCustomerPw(HttpServletRequest httpRequest, @RequestBody UpdatePwRequest request){
        int id = (int) httpRequest.getAttribute("acId");
        UserResponse userResponse = customerService.updateCustomerPw(id, request);
        return ResponseEntity.ok(userResponse);
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
