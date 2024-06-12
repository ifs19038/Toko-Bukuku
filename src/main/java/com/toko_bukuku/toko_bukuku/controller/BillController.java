package com.toko_bukuku.toko_bukuku.controller;
import com.toko_bukuku.toko_bukuku.constant.APIUrl;
import com.toko_bukuku.toko_bukuku.dto.request.BillRequest;
import com.toko_bukuku.toko_bukuku.dto.response.BillResponse;
import com.toko_bukuku.toko_bukuku.dto.response.CommonResponse;
import com.toko_bukuku.toko_bukuku.service.BillService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequiredArgsConstructor
@RequestMapping(path = APIUrl.BILL_API)
public class BillController {
    private final BillService billService;
    @PostMapping
    public ResponseEntity<CommonResponse<BillResponse>> createNewBill(
            @RequestBody BillRequest billRequest
    ){
        BillResponse billCreate = billService.create(billRequest);
        CommonResponse<BillResponse> response = CommonResponse.<BillResponse>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("Successfully create bill")
                .data(billCreate)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @GetMapping
    public ResponseEntity<CommonResponse<List<BillResponse>>> getAllBill(){
        List<BillResponse> billResponses = billService.getAll();
        CommonResponse<List<BillResponse>> response = CommonResponse.<List<BillResponse>>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Successfully get bill")
                .data(billResponses)
                .build();
        return ResponseEntity.ok(response);
    }
}
