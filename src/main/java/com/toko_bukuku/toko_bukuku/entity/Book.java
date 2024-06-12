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
@Table(name = ConstantTable.BOOK)
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "menu_name", nullable = false)
    private String name;

    @Column(name = "price" , nullable = false, columnDefinition = "BIGINT CHECK (price >= 0)")
    private Long price;
}
