package com.toko_bukuku.toko_bukuku.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class BillResponse {
    private String id;
    private String customerId;
    private Date transDate;
    private List<BillDetailResponse> billDetail;
}
