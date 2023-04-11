package com.ejbs.phonebook.service;

import com.ejbs.phonebook.controllers.dto.ContactDTO;
import com.ejbs.phonebook.model.Contact;
import com.ejbs.phonebook.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactService {
    @Autowired
    private ContactRepository contactRepository;

    // Obter todos os contatos
    public List<ContactDTO> getAllContacts() {
        return contactRepository.findAll().stream().map(this::parseToDTO).collect(Collectors.toList());
    }


    private ContactDTO parseToDTO(Contact contact) {
        return ContactDTO.builder()
                .firstName(contact.getFirstName())
                .lastName(contact.getLastName())
                .phone(contact.getPhone())
                .build();
    }

    private Contact parseToEntity(ContactDTO contactDTO) {
        return Contact.builder()
                .firstName(contactDTO.getFirstName())
                .lastName(contactDTO.getLastName())
                .phone(contactDTO.getPhone())
                .build();
    }

    // Obter um contato pelo ID
    public ContactDTO getContactById(Long id) {
        return contactRepository.findById(id).map(this::parseToDTO)
                .orElseThrow(() -> new RuntimeException("Contact not found with id: " + id));
    }

    // Adicionar um novo contato
    public ContactDTO addContact(ContactDTO contactDTO) {
        return this.parseToDTO(contactRepository.save(this.parseToEntity(contactDTO)));
    }

    // Atualizar um contato existente
    public ContactDTO updateContact(Long id, ContactDTO contactDTO) {
        ContactDTO existingContactDTO = getContactById(id);
        existingContactDTO.setFirstName(contactDTO.getFirstName());
        existingContactDTO.setLastName(contactDTO.getLastName());
        existingContactDTO.setPhone(contactDTO.getPhone());
        return this.parseToDTO(contactRepository.save(this.parseToEntity(existingContactDTO)));
    }

    // Deletar um contato pelo ID
    public void deleteContact(Long id) {
        ContactDTO contactDTO = getContactById(id);
        contactRepository.delete(this.parseToEntity(contactDTO));
    }
}

