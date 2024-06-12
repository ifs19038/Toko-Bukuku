package com.toko_bukuku.toko_bukuku.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.toko_bukuku.toko_bukuku.constant.ConstantTable;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = ConstantTable.BILL)
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "trans_date", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date transDate;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @OneToMany(mappedBy = "bill")
    @JsonManagedReference
    private List<BillDetail> billDetails;

}
