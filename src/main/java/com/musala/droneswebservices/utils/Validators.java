package com.musala.droneswebservices.utils;

import com.musala.droneswebservices.exception.DronesAPIException;
import org.springframework.http.HttpStatus;


import java.util.regex.Pattern;

public class Validators {

    public static String validateName(String name){
       if(!Pattern.compile("^[a-zA-Z0-9_\\-]*$").matcher(name).matches()){
           throw new DronesAPIException(HttpStatus.BAD_REQUEST,"Name field only accepts letters, numbers , underscores and dashes are allowed");
       }
       return name;
    }
    public static String validateCode(String code){
        if(!Pattern.compile("^[A-Z0-9_]*$").matcher(code).matches()){
            throw new DronesAPIException(HttpStatus.BAD_REQUEST,"Code field only accepts upper case letters, numbers and underscores are allowed");
        }
        return code;
    }
}
