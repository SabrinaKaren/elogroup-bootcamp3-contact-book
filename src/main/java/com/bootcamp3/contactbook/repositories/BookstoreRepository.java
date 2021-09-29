package com.bootcamp3.contactbook.repositories;

import com.bootcamp3.contactbook.entities.BookstoreEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookstoreRepository extends MongoRepository<BookstoreEntity, String> {
    
}