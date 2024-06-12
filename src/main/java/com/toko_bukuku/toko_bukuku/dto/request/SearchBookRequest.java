package com.toko_bukuku.toko_bukuku.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchBookRequest {
    private Integer page;
    private Integer size;
    private String sortBy;
    private String direction;
    private String name;
    private Long price;
}
