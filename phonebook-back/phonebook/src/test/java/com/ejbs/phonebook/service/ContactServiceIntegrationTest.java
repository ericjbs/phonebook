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
        ContactDTO contact = ContactDTO.builder()
                .firstName("John")
                .lastName("Doe")
                .phone("123456789")
                .build();

        ContactDTO createdContact = contactService.addContact(contact);

        assertThat(createdContact.getId()).isNotNull();
        assertThat(createdContact.getFirstName()).isEqualTo("John");
        assertThat(createdContact.getLastName()).isEqualTo("Doe");
        assertThat(createdContact.getPhone()).isEqualTo("123456789");

        Contact retrievedContact = contactRepository.findById(createdContact.getId()).orElse(null);
        assertThat(retrievedContact).isNotNull();
        assertThat(retrievedContact.getFirstName()).isEqualTo("John");
        assertThat(retrievedContact.getLastName()).isEqualTo("Doe");
        assertThat(retrievedContact.getPhone()).isEqualTo("123456789");
    }

    @Test
    public void testGetContactById() {
        Contact contact = Contact.builder()
                .firstName("John")
                .lastName("Doe")
                .phone("123456789")
                .build();

        Contact savedContact = contactRepository.save(contact);

        ContactDTO retrievedContact = contactService.getContactById(savedContact.getId());

        assertThat(retrievedContact).isNotNull();
        assertThat(retrievedContact.getFirstName()).isEqualTo("John");
        assertThat(retrievedContact.getLastName()).isEqualTo("Doe");
        assertThat(retrievedContact.getPhone()).isEqualTo("123456789");
    }
}
