package com.toko_bukuku.toko_bukuku.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PagingResponse {
    private Integer totalPages;
    private Long totalElements;
    private Integer page;
    private Integer size;
    private Boolean hasNext;
    private Boolean hasPrevious;
}
