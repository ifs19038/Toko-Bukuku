package com.toko_bukuku.toko_bukuku.repository;

import com.toko_bukuku.toko_bukuku.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book,String>, JpaSpecificationExecutor<Book> {
}
