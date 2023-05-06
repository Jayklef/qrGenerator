package com.jayklef.qrgenerator.model;

import lombok.Data;

import java.util.Date;

@Data
public class ErrorMessage {

    private Integer statusCode;
    private String message;
    private Date timestamp;
}
