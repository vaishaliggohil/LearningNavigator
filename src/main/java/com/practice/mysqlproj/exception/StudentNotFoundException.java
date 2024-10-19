package com.practice.mysqlproj.exception;

import java.io.IOException;

public class StudentNotFoundException extends IOException{
    
    public StudentNotFoundException() {}

    public StudentNotFoundException(String message) {
        super(message);
    }
}
