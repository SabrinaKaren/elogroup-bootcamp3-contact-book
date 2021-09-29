package com.bootcamp3.contactbook.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "bookstore")
public class BookstoreEntity {

    @Id
    private String id;
    private String title;
    private String author;
    private int pages;
    
}