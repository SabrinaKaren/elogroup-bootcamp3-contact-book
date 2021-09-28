package com.bootcamp3.contactbook.models;

import java.util.List;
import lombok.Data;

@Data
public class ResponseEnvelope {

    private String method;
    private String result;
    private List<Object> msgSaida;
    private List<Object> error;
    
}