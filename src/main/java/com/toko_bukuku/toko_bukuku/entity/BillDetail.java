package com.toko_bukuku.toko_bukuku.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.toko_bukuku.toko_bukuku.constant.ConstantTable;
import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = ConstantTable.BILL_DETAIL)
public class BillDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "bill_id", nullable = false)
    @JsonBackReference
    private Bill bill;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @Column(name = "book_price", updatable = false, nullable = false)
    private Long bookPrice;

    @Column(name = "qty", nullable = false)
    private Integer qty;
}
