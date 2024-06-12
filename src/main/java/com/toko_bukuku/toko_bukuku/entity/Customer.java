package com.toko_bukuku.toko_bukuku.entity;

import com.toko_bukuku.toko_bukuku.constant.ConstantTable;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = ConstantTable.CUSTOMER)
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "customer_name", nullable = false)
    private String name;

    @Column(name = "mobile_phone_no", nullable = false)
    private String phone;

    @Column(name = "is_member", nullable = false)
    private Boolean isMember;
}
