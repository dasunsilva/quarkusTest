package org.dasun.exceptions;

/**
 * When the user input is not a long number
 */
public class InvalidLongException extends NumberFormatException{
    public InvalidLongException(String message){
        super(message);
    }
}


