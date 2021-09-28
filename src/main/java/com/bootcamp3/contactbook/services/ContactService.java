package com.bootcamp3.contactbook.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.bootcamp3.contactbook.entities.ContactEntity;
import com.bootcamp3.contactbook.models.ContactDto;
import com.bootcamp3.contactbook.repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// Camada responsável por implementar as regras de negócio. Comunica com o repository
@Service
public class ContactService {

    private final ContactRepository repository;

    @Autowired
    public ContactService(ContactRepository repository) {
        this.repository = repository;
    }

    // Método responsável por preparar e salvar o objeto no banco de dados
    public String create(ContactDto contactToCreate) {

        String validateFieldsMsg = this.validateFields(contactToCreate);
        if (validateFieldsMsg.equals("")) {

            if (isEmailValid(contactToCreate.getEmail())) {

                ContactEntity newContactEntity = new ContactEntity();
                newContactEntity.setName(contactToCreate.getName());
                newContactEntity.setNickname(contactToCreate.getNickname());
                newContactEntity.setCellphoneNumber(contactToCreate.getCellphoneNumber());
                newContactEntity.setPhoneNumber(contactToCreate.getPhoneNumber());
                newContactEntity.setEmail(contactToCreate.getEmail());
        
                return this.repository.save(newContactEntity).getId();

            } else {
                return "Endereço de e-mail inválido.";
            }

        } else {
            return validateFieldsMsg;
        }
        
    }

    // Método responsável por pesquisar todos os contatos do banco de dados
    public List<ContactDto> getAll() {

        List<ContactDto> listToReturn = new ArrayList<ContactDto>();

        List<ContactEntity> allContacts = this.repository.findAll();
        allContacts.forEach((item) -> {
            listToReturn.add(new ContactDto(
                item.getId(),
                item.getName(),
                item.getNickname(),
                item.getCellphoneNumber(),
                item.getPhoneNumber(),
                item.getEmail()
            ));
        });

        return listToReturn;
        
    }

    // Método responsável por pesquisar contatos do banco de dados por um nome
    public List<ContactDto> getByName(String name) {

        List<ContactDto> listToReturn = new ArrayList<ContactDto>();

        List<ContactEntity> contactsByName = this.repository.findByNameLike(name);
        contactsByName.forEach((item) -> {
            listToReturn.add(new ContactDto(
                item.getId(),
                item.getName(),
                item.getNickname(),
                item.getCellphoneNumber(),
                item.getPhoneNumber(),
                item.getEmail()
            ));
        });

        return listToReturn;
        
    }

    // Método responsável por preparar e atualizar o objeto no banco de dados
    public String update(ContactDto contactToUpdate) {

        if (contactToUpdate.getId() == null || contactToUpdate.getId().equals("")) {
            return "Não é possível atualizar o contato sem o id";
        } else {

            String validateFieldsMsg = this.validateFields(contactToUpdate);
            if (validateFieldsMsg.equals("")) {

                if (isEmailValid(contactToUpdate.getEmail())) {

                    Optional<ContactEntity> contactOp = this.repository.findById(contactToUpdate.getId());
                    if (contactOp.isPresent()) {
                        contactOp.get().setName(contactToUpdate.getName());
                        contactOp.get().setNickname(contactToUpdate.getNickname());
                        contactOp.get().setCellphoneNumber(contactToUpdate.getCellphoneNumber());
                        contactOp.get().setPhoneNumber(contactToUpdate.getPhoneNumber());
                        contactOp.get().setEmail(contactToUpdate.getEmail());
                        return this.repository.save(contactOp.get()).getId();
                    } else {
                        return "Contato não encontrado.";
                    }
                    

                } else {
                    return "Endereço de e-mail inválido.";
                }

            } else {
                return validateFieldsMsg;
            }

        }
        
    }

    // Método responsável por deletar contato do banco de dados
    public void delete(String id) {
        this.repository.deleteById(id);
    }

    // Método que verifica os campos obrigatórios
    public String validateFields(ContactDto contactObjectToValidate) {

        String returnMsg = "";

        if (contactObjectToValidate.getName() == null || contactObjectToValidate.getName().equals("")) returnMsg += "O nome deve ser informado. ";
        if (contactObjectToValidate.getCellphoneNumber() == null || contactObjectToValidate.getCellphoneNumber().equals("")) returnMsg += "O número do celular deve ser informado. ";

        return returnMsg;

    }

    // Método que valida o endereço de e-mail
    public Boolean isEmailValid(String email) {
        if (email != null && email != "" && email.contains("@")) return true;
        return false;
    }
    
}