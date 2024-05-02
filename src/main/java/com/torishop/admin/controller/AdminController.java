package com.torishop.admin.controller;

import com.torishop.admin.dto.CreateAdminRequest;
import com.torishop.admin.service.AdminService;
import com.torishop.user.dto.GetUserResponse;
import com.torishop.user.dto.User;
import com.torishop.user.dto.UserResponse;
import jakarta.servlet.http.HttpServletRequest;
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

    @GetMapping("/my")
    ResponseEntity<GetUserResponse> getAdmin(HttpServletRequest httpRequest){
        int id = (int) httpRequest.getAttribute("acId");
        GetUserResponse getUserResponse = adminService.getAdmin(id);
        return ResponseEntity.ok(getUserResponse);
    }

    @GetMapping
    ResponseEntity<List<GetUserResponse>> getAdmins(){
        List<GetUserResponse> getUserResponses = adminService.getAdmins();
        return ResponseEntity.ok(getUserResponses);
    }

}
