package com.bootcamp3.contactbook.models;

import lombok.Data;

@Data
public class ContactDto {

    private String id;
    private String name;
    private String nickname;
    private String cellphoneNumber;
    private String phoneNumber;
    private String email;

    public ContactDto() {}

    public ContactDto(String id, String name, String nickname, String cellphoneNumber, String phoneNumber, String email) {
        this.id = id;
        this.name = name;
        this.nickname = nickname;
        this.cellphoneNumber = cellphoneNumber;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }
    
}