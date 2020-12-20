package com.example.demo.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public final class Response<T> {

    private String statusCode;
    private String message;
    private  T data;

    public Response() {

    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Response(T data) {
        this.data = data;
    }

    public Response(String statusCode, String message, T data) {
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;

    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public  ResponseEntity success(String message,String status,T data) {
        Response response = new Response();
        response.setData(data);
        response.setMessage(message);
        response.setStatusCode(status);
        return new ResponseEntity(response, HttpStatus.OK);
    }
    public ResponseEntity failure(String message,String status,T data) {
        Response response = new Response();
        response.setData(data);
        response.setStatusCode(status);
        response.setMessage(message);
        return new ResponseEntity(response, HttpStatus.OK);
    }


}
