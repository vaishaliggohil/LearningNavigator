package com.practice.mysqlproj.exception;

import java.io.IOException;

public class SubjectNotFoundException extends IOException{

    public SubjectNotFoundException() {}

    public SubjectNotFoundException(String message) {
        super(message);
    }
}
