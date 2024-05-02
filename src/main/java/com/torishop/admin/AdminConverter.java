package com.torishop.admin;

import com.torishop.admin.domain.AdminEntity;
import com.torishop.admin.dto.Admin;
import lombok.experimental.UtilityClass;

@UtilityClass
public class AdminConverter {
    public Admin entityToDto(AdminEntity adminEntity){
        return Admin.builder()
                .id(adminEntity.getId())
                .code(adminEntity.getCode())
                .build();
    }
}
