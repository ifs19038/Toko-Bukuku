package com.toko_bukuku.toko_bukuku.specification;
import com.toko_bukuku.toko_bukuku.dto.request.SearchCustomerRequest;
import com.toko_bukuku.toko_bukuku.entity.Customer;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.criteria.Predicate;

public class CustomerSpesification {
    public static Specification<Customer> getSpecification(SearchCustomerRequest searchCustomerRequest){
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (searchCustomerRequest.getName() != null){
                Predicate namePredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%"+searchCustomerRequest.getName().toLowerCase()+"%");
                predicates.add(namePredicate);
            }
            if (searchCustomerRequest.getPhone() != null) {
                Predicate mobilePhoneNoPredicate = criteriaBuilder.equal(root.get("phone"), searchCustomerRequest.getPhone());
                predicates.add(mobilePhoneNoPredicate);
            }
            if(searchCustomerRequest.getIsMember() != null) {
                Predicate statusPredicate = criteriaBuilder.equal(root.get("isMember"), searchCustomerRequest.getIsMember());
                predicates.add(statusPredicate);
            }
            return query.where(criteriaBuilder.or(predicates.toArray(new Predicate[]{}))).getRestriction();
        };
    }

}
