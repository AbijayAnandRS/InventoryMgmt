package com.example.productinventorymgmt.base;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@Setter
public class Success<T> extends NetworkResponse<T> {
    T data;

    public Success(T data) {
        super(HttpStatus.OK, "SUCCESS");
        this.data = data;
    }

    public ResponseEntity<NetworkResponse<T>> toResponse() {
        return ResponseEntity.ok(this);
    }
}
