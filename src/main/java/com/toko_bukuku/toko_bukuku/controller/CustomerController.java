package com.toko_bukuku.toko_bukuku.controller;
import com.toko_bukuku.toko_bukuku.constant.APIUrl;
import com.toko_bukuku.toko_bukuku.dto.request.SearchCustomerRequest;
import com.toko_bukuku.toko_bukuku.dto.response.CommonResponse;
import com.toko_bukuku.toko_bukuku.dto.response.PagingResponse;
import com.toko_bukuku.toko_bukuku.entity.Customer;
import com.toko_bukuku.toko_bukuku.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = APIUrl.CUSTOMER_API)
public class CustomerController {
    private final CustomerService customerService;
    @PostMapping
    public ResponseEntity<CommonResponse<Customer>> createCustomer(
            @RequestBody Customer customer
    ){
        Customer newCustomer = customerService.create(customer);
        CommonResponse<Customer> build = CommonResponse.<Customer>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("Successfully create new Customer")
                .data(newCustomer)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(build);
    }

    @GetMapping(path = APIUrl.PATH_ID)
    public ResponseEntity<CommonResponse<Customer>> getByIdCustomer(
            @PathVariable String id
    ){
        Customer customerGetById = customerService.getById(id);
        CommonResponse<Customer> response = CommonResponse.<Customer>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Successfully get customer")
                .data(customerGetById)
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<CommonResponse<Customer>> updateCustomer(
            @RequestBody Customer customer
    ){
        Customer customerUpdate =customerService.update(customer);
        CommonResponse<Customer> response = CommonResponse.<Customer>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Successfully update customer")
                .data(customerUpdate)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<CommonResponse<List<Customer>>> getAllCustomer(
            @RequestParam (name = "name", required = false ) String name,
            @RequestParam(name = "phone", required = false) String phone,
            @RequestParam(name = "sortBy", defaultValue = "name")String sortBy,
            @RequestParam(name = "direction", defaultValue = "asc")String direction,
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "size", defaultValue = "10") Integer size,
            @RequestParam(name = "isMember", required = false) Boolean isMember
    ){
        SearchCustomerRequest searchCustomerRequest =
                SearchCustomerRequest.builder()
                        .page(page)
                        .size(size)
                        .sortBy(sortBy)
                        .direction(direction)
                        .name(name)
                        .phone(phone)
                        .isMember(isMember)
                        .build();
        Page<Customer> customerGetAll = customerService.getAll(searchCustomerRequest);
        PagingResponse paging = PagingResponse.builder()
                .totalPages(customerGetAll.getTotalPages())
                .totalElements(customerGetAll.getTotalElements())
                .page(customerGetAll.getPageable().getPageNumber() + 1)
                .size(customerGetAll.getPageable().getPageSize())
                .hasNext(customerGetAll.hasNext())
                .hasPrevious(customerGetAll.hasPrevious())
                .build();
        CommonResponse<List<Customer>> build = CommonResponse.<List<Customer>>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Successfully get all customer")
                .data(customerGetAll.getContent())
                .paging(paging)
                .build();
        return ResponseEntity.ok(build);
    }

    @DeleteMapping(path = APIUrl.PATH_ID)
    public ResponseEntity<CommonResponse<?>> deleteByIdCustomer(
            @PathVariable String id
    ){
        customerService.deleteById(id);
        CommonResponse<String> response = CommonResponse.<String>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Delete Customer Success !!!!")
                .build();
        return ResponseEntity.ok(response);
    }
}
