package com.toko_bukuku.toko_bukuku.dto.request;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BillDetailRequest {
    private String bookId;
    private Integer qty;
}
