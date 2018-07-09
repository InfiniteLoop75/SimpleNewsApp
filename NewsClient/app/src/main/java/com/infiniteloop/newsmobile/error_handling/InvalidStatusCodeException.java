package com.infiniteloop.newsmobile.error_handling;

/**
 * Created by hp on 7/9/2018.
 */

public class InvalidStatusCodeException extends Exception {
    private static final String message = "Unable to connect server";


    public InvalidStatusCodeException(){
        super(message);
    }
}
