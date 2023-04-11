package com.ejbs.phonebook.service;

import com.ejbs.phonebook.controllers.dto.ContactDTO;
import com.ejbs.phonebook.model.Contact;
import com.ejbs.phonebook.repository.ContactRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ContactServiceIntegrationTest {

    @Autowired
    private ContactService contactService;

    @Autowired
    private ContactRepository contactRepository;

    @Test
    public void testCreateContact() {
        // Dados de entrada
        ContactDTO contact = ContactDTO.builder()
                .firstName("John")
                .lastName("Doe")
                .phone("123456789")
                .build();

        // Chamada do serviço
        ContactDTO createdContact = contactService.addContact(contact);

        // Verificações
        assertThat(createdContact.getId()).isNotNull();
        assertThat(createdContact.getFirstName()).isEqualTo("John");
        assertThat(createdContact.getLastName()).isEqualTo("Doe");
        assertThat(createdContact.getPhone()).isEqualTo("123456789");

        // Verifica se o contato foi salvo no banco de dados
        Contact retrievedContact = contactRepository.findById(createdContact.getId()).orElse(null);
        assertThat(retrievedContact).isNotNull();
        assertThat(retrievedContact.getFirstName()).isEqualTo("John");
        assertThat(retrievedContact.getLastName()).isEqualTo("Doe");
        assertThat(retrievedContact.getPhone()).isEqualTo("123456789");
    }

    @Test
    public void testGetContactById() {
        // Dados de entrada
        Contact contact = Contact.builder()
                .firstName("John")
                .lastName("Doe")
                .phone("123456789")
                .build();

        // Salva um contato no banco de dados
        Contact savedContact = contactRepository.save(contact);

        // Chamada do serviço
        ContactDTO retrievedContact = contactService.getContactById(savedContact.getId());

        // Verificações
        assertThat(retrievedContact).isNotNull();
        assertThat(retrievedContact.getFirstName()).isEqualTo("John");
        assertThat(retrievedContact.getLastName()).isEqualTo("Doe");
        assertThat(retrievedContact.getPhone()).isEqualTo("123456789");
    }
}
