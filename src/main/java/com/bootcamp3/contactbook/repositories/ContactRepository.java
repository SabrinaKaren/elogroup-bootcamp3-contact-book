package com.bootcamp3.contactbook.repositories;

import java.util.List;

import com.bootcamp3.contactbook.entities.ContactEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
 
// Camada responsável pela abstração ao banco de dados. Comunica com o Entity
public interface ContactRepository extends MongoRepository<ContactEntity, String> {

    List<ContactEntity> findByNameLike(String name);

}