package com.musala.droneswebservices.utils;

import com.musala.droneswebservices.exception.DronesAPIException;
import org.springframework.http.HttpStatus;


import java.util.regex.Pattern;

public class Validators {

    public static String validateName(String name){
       if(!Pattern.compile("[A-Za-z0-9-_]").matcher(name).matches()){
           throw new DronesAPIException(HttpStatus.BAD_REQUEST,"Only letters, numbers , underscores and dashes are allowed");
       }
       return name;
    }
    public static String validateCode(String code){
        if(!Pattern.compile("[A-Z0-9-_]").matcher(code).matches()){
            throw new DronesAPIException(HttpStatus.BAD_REQUEST,"Only upper case letters, numbers , underscores and dashes are allowed");
        }
        return code;
    }
}
