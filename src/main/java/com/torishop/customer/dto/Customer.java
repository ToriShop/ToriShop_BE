package com.torishop.customer.dto;

import com.torishop.tier.domain.TierEntity;
import com.torishop.tier.dto.Tier;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    private int id;
    private String phoneNumber;
    private String email;
    private String address;
    private int totalOrderAmount;
    private Tier tier;
    private LocalDate joinDate;
}
