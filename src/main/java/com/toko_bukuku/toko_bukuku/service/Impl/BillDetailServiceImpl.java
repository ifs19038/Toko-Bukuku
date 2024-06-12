package com.toko_bukuku.toko_bukuku.service.Impl;

import com.toko_bukuku.toko_bukuku.entity.BillDetail;
import com.toko_bukuku.toko_bukuku.repository.BillDetailRepository;
import com.toko_bukuku.toko_bukuku.service.BillDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BillDetailServiceImpl implements BillDetailService {
    private final BillDetailRepository billDetailRepository;
    @Override
    public List<BillDetail> createBluk(List<BillDetail> billDetails) {
        return billDetailRepository.saveAllAndFlush(billDetails);
    }
}
