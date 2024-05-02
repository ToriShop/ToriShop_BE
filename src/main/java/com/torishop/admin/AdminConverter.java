package com.torishop.admin;

import com.torishop.admin.domain.AdminEntity;
import com.torishop.admin.dto.Admin;
import com.torishop.user.dto.GetUserResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class AdminConverter {
    public Admin entityToDto(AdminEntity adminEntity){
        if(adminEntity==null) return null;
        return Admin.builder()
                .id(adminEntity.getId())
                .code(adminEntity.getCode())
                .build();
    }
}
