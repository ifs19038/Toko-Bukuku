package com.toko_bukuku.toko_bukuku.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BillDetailResponse {
    private String id;
    private String bookId;
    private Long bookPrice;
    private Integer quantity;
}
