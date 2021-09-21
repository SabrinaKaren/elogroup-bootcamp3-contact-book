package com.bootcamp3.contactbook.entities;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "teste")
public class ContactEntity {

    // @Id
    public String id;
    public String name;

    public ContactEntity() {}
    
}