package com.toko_bukuku.toko_bukuku.repository;

import com.toko_bukuku.toko_bukuku.entity.BillDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillDetailRepository extends JpaRepository<BillDetail, String> {
}
