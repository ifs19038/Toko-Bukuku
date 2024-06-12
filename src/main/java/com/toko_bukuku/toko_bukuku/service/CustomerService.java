package com.toko_bukuku.toko_bukuku.service;

import com.toko_bukuku.toko_bukuku.dto.request.SearchCustomerRequest;
import com.toko_bukuku.toko_bukuku.entity.Customer;
import org.springframework.data.domain.Page;

public interface CustomerService {
    Customer create(Customer customer);
    Customer getById(String id);
    Customer update(Customer customer);
    Page<Customer> getAll(SearchCustomerRequest searchCustomerRequest);
    void deleteById(String id);
}
