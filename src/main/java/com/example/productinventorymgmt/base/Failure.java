package com.example.productinventorymgmt.base;

import com.example.productinventorymgmt.products.models.other.ProductServiceException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@Setter
public class Failure<T> extends NetworkResponse<T> {

    private String serviceInfo;
    public Failure(ProductServiceException e) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        this.serviceInfo = e.getServiceInfo();
    }

    public ResponseEntity<NetworkResponse<T>> toResponse() {
        return ResponseEntity.internalServerError().body(this);
    }
}
