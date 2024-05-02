package com.torishop.customer;

import com.torishop.customer.domain.CustomerEntity;
import com.torishop.customer.dto.Customer;
import com.torishop.tier.TierConverter;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CustomerConverter {
    public Customer entityToDto(CustomerEntity customerEntity){
        return Customer.builder()
                .id(customerEntity.getId())
                .phoneNumber(customerEntity.getPhoneNumber())
                .email(customerEntity.getEmail())
                .address(customerEntity.getAddress())
                .tier(TierConverter.entityToDto(customerEntity.getTierEntity()))
                .build();
    }
}
