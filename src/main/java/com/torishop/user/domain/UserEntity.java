package com.torishop.user.domain;

import com.torishop.admin.domain.AdminEntity;
import com.torishop.customer.domain.CustomerEntity;
import com.torishop.user.enums.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
public class UserEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "username", length = 20)
    @NotNull
    private String username;

    @Column(name = "password", length = 20)
    @NotNull
    private String password;

    @Column(name = "user_role", length = 10)
    @NotNull
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @JoinColumn(name = "admin_id")
    @OneToOne(optional = true)
    private AdminEntity adminEntity;

    @JoinColumn(name = "customer_id")
    @OneToOne(optional = true)
    private CustomerEntity customerEntity;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> roles = new HashSet<>();
        roles.add(new SimpleGrantedAuthority(userRole.getValue()));
        return roles;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
