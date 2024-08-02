package org.dasun.exceptions;

public class InvalidPhoneNumberException extends IllegalArgumentException{
    public InvalidPhoneNumberException(){
        super("Enter a valid phone number in the form +94xxxxxxxxx");
    }
}
