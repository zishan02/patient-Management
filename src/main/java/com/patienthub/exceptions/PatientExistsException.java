package com.patienthub.exceptions;

public class PatientExistsException extends RuntimeException{

    public PatientExistsException()
    {
        super("Patient already exists or use a new number to register the patient");
    }
}
