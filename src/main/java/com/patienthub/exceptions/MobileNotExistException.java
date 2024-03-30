package com.patienthub.exceptions;

public class MobileNotExistException extends RuntimeException{

    public MobileNotExistException(){
        super("Mobile Number Doesnot exits , Please register the patient with Mobile number");
    }
}
