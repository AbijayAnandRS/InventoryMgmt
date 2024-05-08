package com.example.productinventorymgmt.base;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;


@Getter
@Setter
public class NetworkResponse<T>  {


    public NetworkResponse(HttpStatus status, String message) {
        responseCode = status.value();
        responseMessage = message;
    }
    private int responseCode;
    private String responseMessage;
}
