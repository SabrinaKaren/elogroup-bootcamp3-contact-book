package com.bootcamp3.contactbook.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

// Camada responsável pelo espelhamento da tabela/coleção do banco de dados. Comunica com o Banco de dados
@Data
@Document(collection = "contact-book")
public class ContactEntity {

    @Id
    public String id;
    private String name;
    private String nickname;
    private String cellphoneNumber;
    private String phoneNumber;
    private String email;

    public ContactEntity() {}
    
}