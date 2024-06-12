package com.toko_bukuku.toko_bukuku.service.Impl;

import com.toko_bukuku.toko_bukuku.dto.request.SearchCustomerRequest;
import com.toko_bukuku.toko_bukuku.entity.Customer;
import com.toko_bukuku.toko_bukuku.repository.CustomerRepository;
import com.toko_bukuku.toko_bukuku.service.CustomerService;
import com.toko_bukuku.toko_bukuku.specification.CustomerSpesification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    @Override
    public Customer create(Customer customer) {

        return customerRepository.saveAndFlush(customer);
    }

    @Override
    public Customer getById(String id) {

        return findByIdOrThrowNotFound(id);
    }

    @Override
    public Customer update(Customer customer) {
        findByIdOrThrowNotFound(customer.getId());
        return customerRepository.saveAndFlush(customer);
    }

    @Override
    public Page<Customer> getAll(SearchCustomerRequest searchCustomerRequest) {
        Specification<Customer> customerSpecification = CustomerSpesification.getSpecification(searchCustomerRequest);
        if (searchCustomerRequest.getPage() <= 0){
            searchCustomerRequest.setPage(1);
        }
        String validSortBy;
        if ("name".equalsIgnoreCase(searchCustomerRequest.getSortBy()) || "phone".equalsIgnoreCase(searchCustomerRequest.getPhone()) || "isMember".equalsIgnoreCase((searchCustomerRequest.getIsMember()).toString())){
            validSortBy = searchCustomerRequest.getSortBy();
        } else {
            validSortBy = "name";
        }

        Sort sort = Sort.by(Sort.Direction.fromString(searchCustomerRequest.getDirection()), validSortBy);

        Pageable pageable = PageRequest.of((searchCustomerRequest.getPage() - 1), searchCustomerRequest.getSize());
        if (searchCustomerRequest.getName() == null && searchCustomerRequest.getPhone() == null && searchCustomerRequest.getIsMember() == null){
            return customerRepository.findAll(pageable);
        }


        return  customerRepository.findAll(customerSpecification, pageable);
    }

    @Override
    public void deleteById(String id) {
        Customer customer = findByIdOrThrowNotFound(id);
        customerRepository.delete(customer);
    }
    public Customer findByIdOrThrowNotFound(String id) {
        return customerRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"customer not found"));
    }
}
