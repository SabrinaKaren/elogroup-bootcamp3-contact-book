package com.bootcamp3.contactbook.controllers;

import java.util.ArrayList;
import java.util.List;

import com.bootcamp3.contactbook.models.ContactDto;
import com.bootcamp3.contactbook.models.ResponseEnvelope;
import com.bootcamp3.contactbook.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Camada responsável pelo mapeamento de entrada e saída de dados. Comunica com o Service
@RestController
@RequestMapping("/contacts")
public class ContactController {

    private final ContactService service;

    @Autowired
    public ContactController(ContactService service) {
        this.service = service;
    }

    // Criar contato
    @PostMapping
    public ResponseEnvelope create(@RequestBody ContactDto contactToCreate) {

        ResponseEnvelope responseEnvelope = new ResponseEnvelope();
        responseEnvelope.setMethod("create");

        try {
            responseEnvelope.setResult("SUCCESS");
            responseEnvelope.setMsgSaida(new ArrayList<>());
            responseEnvelope.getMsgSaida().add(this.service.create(contactToCreate));
        } catch (Exception error) {
            responseEnvelope.setResult("ERROR");
            responseEnvelope.setError(new ArrayList<>());
            responseEnvelope.getError().add(error);
        }

        return responseEnvelope;

    }

    // Listar todos os contatos
    @GetMapping
    public ResponseEnvelope getAll() {

        ResponseEnvelope responseEnvelope = new ResponseEnvelope();
        responseEnvelope.setMethod("getAll");

        try {

            responseEnvelope.setResult("SUCCESS");
            responseEnvelope.setMsgSaida(new ArrayList<>());

            List<ContactDto> allContacts = this.service.getAll();
            allContacts.forEach((item)->{
                responseEnvelope.getMsgSaida().add(item);
            });

        } catch (Exception error) {

            responseEnvelope.setResult("ERROR");
            responseEnvelope.setError(new ArrayList<>());
            responseEnvelope.getError().add(error);

        }

        return responseEnvelope;

    }

    // Filtrar contatos por nome
    @GetMapping("/filter/{name}")
    public ResponseEnvelope getByName(@PathVariable String name) {

        ResponseEnvelope responseEnvelope = new ResponseEnvelope();
        responseEnvelope.setMethod("getByName");

        try {

            responseEnvelope.setResult("SUCCESS");
            responseEnvelope.setMsgSaida(new ArrayList<>());

            List<ContactDto> allContacts = this.service.getByName(name);
            allContacts.forEach((item)->{
                responseEnvelope.getMsgSaida().add(item);
            });

        } catch (Exception error) {

            responseEnvelope.setResult("ERROR");
            responseEnvelope.setError(new ArrayList<>());
            responseEnvelope.getError().add(error);

        }

        return responseEnvelope;

    }

    // Atualizar contato
    @PutMapping
    public ResponseEnvelope update(@RequestBody ContactDto contactToUpdate) {

        ResponseEnvelope responseEnvelope = new ResponseEnvelope();
        responseEnvelope.setMethod("update");

        try {
            responseEnvelope.setResult("SUCCESS");
            responseEnvelope.setMsgSaida(new ArrayList<>());
            responseEnvelope.getMsgSaida().add(this.service.update(contactToUpdate));
        } catch (Exception error) {
            responseEnvelope.setResult("ERROR");
            responseEnvelope.setError(new ArrayList<>());
            responseEnvelope.getError().add(error);
        }

        return responseEnvelope;

    }

    // Deletar contato
    @DeleteMapping("/{id}")
    public ResponseEnvelope delete(@PathVariable String id) {

        ResponseEnvelope responseEnvelope = new ResponseEnvelope();
        responseEnvelope.setMethod("delete");

        try {

            responseEnvelope.setResult("SUCCESS");
            responseEnvelope.setMsgSaida(new ArrayList<>());

            this.service.delete(id);
            responseEnvelope.getMsgSaida().add("Contato excluído com sucesso.");

        } catch (Exception error) {

            responseEnvelope.setResult("ERROR");
            responseEnvelope.setError(new ArrayList<>());
            responseEnvelope.getError().add(error);

        }

        return responseEnvelope;

    }
    
}