package com.torishop.user.domain;

import com.torishop.user.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    UserEntity findByUsername(String username);
    List<UserEntity> findByUserRole(UserRole userRole);
    UserEntity findByAdminEntityId(int id);
    UserEntity findByCustomerEntityId(int id);
}
