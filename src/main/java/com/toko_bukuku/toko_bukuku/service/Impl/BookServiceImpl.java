package com.toko_bukuku.toko_bukuku.service.Impl;

import com.toko_bukuku.toko_bukuku.dto.request.SearchBookRequest;
import com.toko_bukuku.toko_bukuku.entity.Book;
import com.toko_bukuku.toko_bukuku.repository.BookRepository;
import com.toko_bukuku.toko_bukuku.service.BookService;
import com.toko_bukuku.toko_bukuku.specification.BookSpecification;
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
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    @Override
    public Book create(Book book) {

        return bookRepository.saveAndFlush(book);
    }

    @Override
    public Book getById(String id) {
        return findByIdOrThrowNotFound(id);
    }

    @Override
    public Book update(Book book) {
        findByIdOrThrowNotFound(book.getId());
        return bookRepository.saveAndFlush(book);
    }

    @Override
    public Page<Book> getAll(SearchBookRequest searchBookRequest) {
        Specification<Book> bookSpecification = BookSpecification.getSpecification(searchBookRequest);
        if(searchBookRequest.getPage() <= 0) {
            searchBookRequest.setPage(1);
        }
        String validSortBy;
        if("name".equalsIgnoreCase(searchBookRequest.getSortBy()) || "price".equalsIgnoreCase(searchBookRequest.getSortBy())){
            validSortBy = searchBookRequest.getSortBy();
        }else {
            validSortBy = "name";
        }
        Sort sort = Sort.by(Sort.Direction.fromString(searchBookRequest.getDirection()), validSortBy);
        Pageable pageable = PageRequest.of((searchBookRequest.getPage() - 1), searchBookRequest.getSize());
        if(searchBookRequest.getName() == null && searchBookRequest.getPrice() == null){
            return bookRepository.findAll(pageable);
        }
        return bookRepository.findAll(bookSpecification, pageable );
    }

    @Override
    public void deleteById(String id) {
        Book menuFindById = findByIdOrThrowNotFound(id);
        bookRepository.delete(menuFindById);

    }
    public Book findByIdOrThrowNotFound(String id) {
        return bookRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Book not found"));
    }
}
