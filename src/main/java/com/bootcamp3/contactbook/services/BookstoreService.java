package com.bootcamp3.contactbook.services;

import com.bootcamp3.contactbook.entities.BookstoreEntity;
import com.bootcamp3.contactbook.models.BookstoreDto;
import com.bootcamp3.contactbook.repositories.BookstoreRepository;

import org.springframework.stereotype.Service;

@Service
public class BookstoreService {

    private final BookstoreRepository repository;

    public BookstoreService(BookstoreRepository repository) {
        this.repository = repository;
    }

    public String create(BookstoreDto bookstoreToCreate) {

        String returnMsg = validate(bookstoreToCreate);
        if (returnMsg.equals("")) {

            BookstoreEntity newBookstoreEntity = new BookstoreEntity();
            newBookstoreEntity.setTitle(bookstoreToCreate.getTitle());
            newBookstoreEntity.setAuthor(bookstoreToCreate.getAuthor());
            newBookstoreEntity.setPages(bookstoreToCreate.getPages());

            returnMsg = repository.save(newBookstoreEntity).getId();

        }

        return returnMsg;

    }

    public String validate(BookstoreDto bookstoreToValidate) {

        String returnMsg = "";

        if (bookstoreToValidate.getAuthor() == null || bookstoreToValidate.getAuthor().equals("")) returnMsg += "O autor deve ser informado";
        if (bookstoreToValidate.getTitle() == null || bookstoreToValidate.getTitle().equals("")) returnMsg += "O nome do livro deve ser informado";

        return returnMsg;

    }
    
}