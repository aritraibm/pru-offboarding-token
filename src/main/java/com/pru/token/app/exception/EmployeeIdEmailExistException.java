package com.pru.token.app.exception;

public class EmployeeIdEmailExistException extends RuntimeException{
    public EmployeeIdEmailExistException(String msg){
        super(msg);
    }
}