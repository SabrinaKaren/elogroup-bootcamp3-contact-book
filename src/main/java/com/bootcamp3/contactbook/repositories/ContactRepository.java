package com.bootcamp3.contactbook.repositories;

import com.bootcamp3.contactbook.entities.ContactEntity;

import org.springframework.data.mongodb.repository.MongoRepository;
 
public interface ContactRepository extends MongoRepository<ContactEntity, String> {
}