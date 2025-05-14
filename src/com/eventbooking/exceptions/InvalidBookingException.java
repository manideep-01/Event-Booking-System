package com.eventbooking.exceptions;

public class InvalidBookingException extends Exception{
    public InvalidBookingException(String message){
        super(message);
    }
}
