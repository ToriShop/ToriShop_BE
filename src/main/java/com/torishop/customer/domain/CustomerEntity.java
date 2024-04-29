package com.torishop.customer.domain;

import com.torishop.tier.domain.TierEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.Date;

import static java.time.LocalTime.now;

@Entity
@Table(name = "customer")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "phone_number", length = 15)
    private String phoneNumber;

    @Column(name = "email", length = 20)
    private String email;

    @Column(name = "address", length = 50)
    private String address;

    @Column(name = "total_order_amount")
    @NotNull
    private int totalOrderAmount;

    @JoinColumn(name = "tier_id")
    @NotNull
    @OneToOne
    private TierEntity tierEntity;

    @Column(name = "join_date")
    @NotNull
    private LocalDate joinDate;

    @PrePersist
    private void prePersist(){
        this.joinDate = LocalDate.now();
    }
}
