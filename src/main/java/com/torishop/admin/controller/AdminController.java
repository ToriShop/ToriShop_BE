package com.torishop.admin.controller;

import com.torishop.admin.dto.CreateAdminRequest;
import com.torishop.admin.service.AdminService;
import com.torishop.user.dto.User;
import com.torishop.user.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;

    @PostMapping
    ResponseEntity<UserResponse> createAdmin(@RequestBody CreateAdminRequest request){
        UserResponse userResponse = adminService.createAdmin(request);
        return ResponseEntity.ok(userResponse);
    }

    @GetMapping("/{id}")
    ResponseEntity<User> getAdmin(@PathVariable("id") int id){
        User user = adminService.getAdmin(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping()
    ResponseEntity<List<User>> getAdmins(){
        List<User> users = adminService.getAdmins();
        return ResponseEntity.ok(users);
    }

}
