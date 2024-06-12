package com.toko_bukuku.toko_bukuku.service;

import com.toko_bukuku.toko_bukuku.dto.request.SearchBookRequest;
import com.toko_bukuku.toko_bukuku.entity.Book;
import org.springframework.data.domain.Page;

public interface BookService {

    Book create(Book book);
    Book getById(String id);
    Book update(Book menu);
    Page<Book> getAll(SearchBookRequest searchMenuRequest);
    void deleteById(String id);
}
