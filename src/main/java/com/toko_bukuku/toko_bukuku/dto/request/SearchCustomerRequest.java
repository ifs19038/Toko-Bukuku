package com.toko_bukuku.toko_bukuku.dto.request;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchCustomerRequest {
    private Integer page;
    private Integer size;
    private String sortBy;
    private String direction;
    private String name;
    private String phone;
    private Boolean isMember;
}
