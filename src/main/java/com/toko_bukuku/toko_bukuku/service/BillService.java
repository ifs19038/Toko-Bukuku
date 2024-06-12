package com.toko_bukuku.toko_bukuku.service;

import com.toko_bukuku.toko_bukuku.dto.request.BillRequest;
import com.toko_bukuku.toko_bukuku.dto.response.BillResponse;

import java.util.List;

public interface BillService {
    BillResponse create(BillRequest request);
    List<BillResponse> getAll();
}
