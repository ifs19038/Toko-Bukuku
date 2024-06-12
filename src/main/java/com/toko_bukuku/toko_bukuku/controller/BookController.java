package com.toko_bukuku.toko_bukuku.controller;
import com.toko_bukuku.toko_bukuku.constant.APIUrl;
import com.toko_bukuku.toko_bukuku.dto.request.SearchBookRequest;
import com.toko_bukuku.toko_bukuku.dto.response.CommonResponse;
import com.toko_bukuku.toko_bukuku.dto.response.PagingResponse;
import com.toko_bukuku.toko_bukuku.entity.Book;
import com.toko_bukuku.toko_bukuku.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequiredArgsConstructor
@RequestMapping(path = APIUrl.BOOK_API)
public class BookController {
    public final BookService bookService;
    @PostMapping
    public ResponseEntity<CommonResponse<Book>> createMenu(
            @RequestBody Book book
    ){
        Book newBook = bookService.create(book);
        CommonResponse<Book> response = CommonResponse.<Book>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("Successfully create book")
                .data(newBook)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping(path = APIUrl.PATH_ID)
    public ResponseEntity<CommonResponse<Book>> getByIdMenu(
            @PathVariable String id
    ){
        Book bookById = bookService.getById(id);
        CommonResponse<Book> response = CommonResponse.<Book>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Successfully get menu")
                .data(bookById)
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<CommonResponse<Book>> updateMenu(
            @RequestBody Book book
    ){
        Book bookUpdate = bookService.update(book);
        CommonResponse<Book> response = CommonResponse.<Book>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Successfully update menu")
                .data(bookUpdate)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<CommonResponse<List<Book>>> getAllMenu(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "size", defaultValue = "10") Integer size,
            @RequestParam(name = "sortBy", defaultValue = "name") String sortBy,
            @RequestParam(name = "direction", defaultValue = "asc") String direction,
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "price", required = false) Long price
    ){
        SearchBookRequest searchBookRequest =
                SearchBookRequest.builder()
                        .page(page)
                        .size(size)
                        .sortBy(sortBy)
                        .direction(direction)
                        .name(name)
                        .price(price)
                        .build();
        Page<Book> bookGetAll = bookService.getAll(searchBookRequest);
        PagingResponse paging = PagingResponse.builder()
                .totalPages(bookGetAll.getTotalPages())
                .totalElements(bookGetAll.getTotalElements())
                .page(bookGetAll.getPageable().getPageNumber() + 1)
                .size(bookGetAll.getPageable().getPageSize())
                .hasNext(bookGetAll.hasNext())
                .hasPrevious(bookGetAll.hasPrevious())
                .build();
        CommonResponse<List<Book>> response = CommonResponse.<List<Book>>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Successfully get all Menu")
                .data(bookGetAll.getContent())
                .paging(paging)
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(path = APIUrl.PATH_ID)
    public ResponseEntity<CommonResponse<String>> deleteByIdMenu(
            @PathVariable String id
    ){
        bookService.deleteById(id);
        CommonResponse<String> response = CommonResponse.<String>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Delete Menu Success!!!")
                .build();
        return ResponseEntity.ok(response);
    }
}
