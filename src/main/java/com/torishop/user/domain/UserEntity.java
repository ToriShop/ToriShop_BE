package com.torishop.user.domain;

import com.torishop.admin.domain.AdminEntity;
import com.torishop.customer.domain.CustomerEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
@Table(name = "user")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
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
    @Enumerated
    private String userRole;

    @JoinColumn(name = "admin_id")
    @OneToOne(optional = true)
    private AdminEntity adminEntity;

    @JoinColumn(name = "customer_id")
    @OneToOne(optional = true)
    private CustomerEntity customerEntity;
}
