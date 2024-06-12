package com.toko_bukuku.toko_bukuku.dto.request;

import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BillRequest {
    private String customerId;
    private List<BillDetailRequest> billDetails;
}
