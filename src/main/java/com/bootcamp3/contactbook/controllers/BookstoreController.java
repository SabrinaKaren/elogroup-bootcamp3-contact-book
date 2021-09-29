package com.bootcamp3.contactbook.controllers;

import java.util.ArrayList;

import com.bootcamp3.contactbook.models.BookstoreDto;
import com.bootcamp3.contactbook.models.ResponseEnvelope;
import com.bootcamp3.contactbook.services.BookstoreService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bookstore")
public class BookstoreController {

    private final BookstoreService service;

    public BookstoreController(BookstoreService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEnvelope create(@RequestBody BookstoreDto bookstoreToCreate) {

        ResponseEnvelope responseEnvelope = new ResponseEnvelope();
        responseEnvelope.setMethod("create");

        try {
            responseEnvelope.setResult("SUCCESS");
            responseEnvelope.setMsgSaida(new ArrayList<>());
            responseEnvelope.getMsgSaida().add(this.service.create(bookstoreToCreate));
        } catch (Exception error) {
            responseEnvelope.setResult("ERROR");
            responseEnvelope.setError(new ArrayList<>());
            responseEnvelope.getError().add(error);
        }

        return responseEnvelope;

    }
    
}