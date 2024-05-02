package com.torishop.customer.service.impl;

import com.torishop.customer.domain.CustomerEntity;
import com.torishop.customer.domain.CustomerRepository;
import com.torishop.customer.dto.CreateCustomerRequest;
import com.torishop.customer.dto.UpdateCustomerRequest;
import com.torishop.customer.service.CustomerService;
import com.torishop.tier.domain.TierRepository;
import com.torishop.user.UserConverter;
import com.torishop.user.domain.UserEntity;
import com.torishop.user.domain.UserRepository;
import com.torishop.user.dto.User;
import com.torishop.user.dto.UserResponse;
import com.torishop.user.enums.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.time.LocalDate.now;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final TierRepository tierRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserResponse createCustomer(CreateCustomerRequest request) {
        CustomerEntity customer = CustomerEntity.builder()
                .phoneNumber(request.getPhoneNumber())
                .email(request.getEmail())
                .address(request.getAddress())
                .totalOrderAmount(0)
                .tierEntity(tierRepository.findById(1).get())
                .joinDate(now())
                .build();
        CustomerEntity savedCustomer = customerRepository.save(customer);

        UserEntity userEntity = UserEntity.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .customerEntity(savedCustomer)
                .userRole(UserRole.CUSTOMER)
                .build();
        userRepository.save(userEntity);

        return UserResponse.builder()
                .id(userEntity.getId())
                .build();
    }

    @Override
    @Transactional
    public User getCustomer(int id) {
        UserEntity user = userRepository.findByCustomerEntityId(id);
        return UserConverter.entityToDto(user);
    }

    @Override
    @Transactional
    public List<User> getCustomers() {
        List<UserEntity> userEntities = userRepository.findByUserRole(UserRole.CUSTOMER);
        return userEntities.stream().map(UserConverter::entityToDto).toList();
    }

    @Override
    @Transactional
    public UserResponse updateCustomer(int id, UpdateCustomerRequest request) {
        CustomerEntity customer = customerRepository.findById(id).get();
        UserEntity user = userRepository.findByCustomerEntityId(id);

        customer.setPhoneNumber(request.getPhoneNumber());
        customer.setEmail(request.getEmail());
        customer.setAddress(request.getAddress());

        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setCustomerEntity(customer);  //cascade 작동할 것 같긴 해.

        return UserResponse.builder()
                .id(user.getId())
                .build();
    }

    @Override
    @Transactional
    public UserResponse deleteCustomer(int id) {
        UserEntity user = userRepository.findByCustomerEntityId(id);
        customerRepository.deleteById(id);

        return UserResponse.builder()
                .id(user.getId())
                .build();
    }
}
