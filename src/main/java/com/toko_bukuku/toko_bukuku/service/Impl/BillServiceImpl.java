package com.toko_bukuku.toko_bukuku.service.Impl;
import com.toko_bukuku.toko_bukuku.dto.request.BillRequest;
import com.toko_bukuku.toko_bukuku.dto.response.BillDetailResponse;
import com.toko_bukuku.toko_bukuku.dto.response.BillResponse;
import com.toko_bukuku.toko_bukuku.entity.Bill;
import com.toko_bukuku.toko_bukuku.entity.BillDetail;
import com.toko_bukuku.toko_bukuku.entity.Book;
import com.toko_bukuku.toko_bukuku.entity.Customer;
import com.toko_bukuku.toko_bukuku.repository.BillRepository;
import com.toko_bukuku.toko_bukuku.service.BillDetailService;
import com.toko_bukuku.toko_bukuku.service.BillService;
import com.toko_bukuku.toko_bukuku.service.BookService;
import com.toko_bukuku.toko_bukuku.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
@Service
@RequiredArgsConstructor
public class BillServiceImpl implements BillService {
    private final BillRepository billRepository;
    private final CustomerService customerService;
    private final BookService bookService;
    private final BillDetailService billDetailService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public BillResponse create(BillRequest request) {
        Customer customer = customerService.getById(request.getCustomerId());

        Bill bill = Bill.builder()
                .transDate(new Date())
                .customer(customer)
                .build();
        billRepository.saveAndFlush(bill);

        List<BillDetail> billDetails = request.getBillDetails().stream()
                .map(detailRequest ->{
                    Book book = bookService.getById(detailRequest.getBookId());
                    return BillDetail.builder()
                            .book(book)
                            .bill(bill)
                            .qty(detailRequest.getQty())
                            .bookPrice(book.getPrice())
                            .build();
                }).toList();

        billDetailService.createBluk(billDetails);
        bill.setBillDetails(billDetails);

        List<BillDetailResponse> billDetailResponses = billDetails.stream()
                .map(detail ->{
                    return BillDetailResponse.builder()
                            .id(detail.getId())
                            .bookId(detail.getBook().getId())
                            .bookPrice(detail.getBookPrice())
                            .quantity(detail.getQty())
                            .build();
                }).toList();

        return BillResponse.builder()
                .id(bill.getId())
                .customerId(bill.getCustomer().getId())
                .transDate(bill.getTransDate())
                .billDetail(billDetailResponses)
                .build();
    }

    @Override
    public List<BillResponse> getAll() {
        List<Bill> bills = billRepository.findAll();

        return bills.stream().map(bill -> {
            List<BillDetailResponse> billDetailResponses = bill.getBillDetails().stream().map(billDetail ->{
                return BillDetailResponse.builder()
                        .id(billDetail.getId())
                        .bookId(billDetail.getBook().getId())
                        .bookPrice(billDetail.getBookPrice())
                        .quantity(billDetail.getQty())
                        .build();
            }).toList();
            return BillResponse.builder()
                    .id(bill.getId())
                    .customerId(bill.getCustomer().getId())
                    .transDate(bill.getTransDate())
                    .billDetail(billDetailResponses)
                    .build();
        }).toList();
    }
}
