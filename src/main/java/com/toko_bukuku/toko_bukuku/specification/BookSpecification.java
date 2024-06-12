package com.toko_bukuku.toko_bukuku.specification;
import com.toko_bukuku.toko_bukuku.dto.request.SearchBookRequest;
import com.toko_bukuku.toko_bukuku.entity.Book;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.criteria.Predicate;
public class BookSpecification {
    public static Specification<Book> getSpecification(SearchBookRequest searchBookRequest){
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (searchBookRequest.getName() != null){
                Predicate namePredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("name")),"%"+searchBookRequest.getName().toLowerCase()+"%");
                predicates.add(namePredicate);
            }
            if (searchBookRequest.getPrice() != null) {
                Predicate mobilePhoneNoPredicate = criteriaBuilder.equal(root.get("price"), searchBookRequest.getPrice());
                predicates.add(mobilePhoneNoPredicate);
            }
            return query.where(criteriaBuilder.or(predicates.toArray(new Predicate[]{}))).getRestriction();
        };
    }
}
