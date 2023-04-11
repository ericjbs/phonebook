package com.ejbs.phonebook.service;

import com.ejbs.phonebook.controllers.dto.ContactDTO;
import com.ejbs.phonebook.model.Contact;
import com.ejbs.phonebook.repository.ContactRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ContactServiceTest {

    @InjectMocks
    private ContactService contactService;

    @Mock
    private ContactRepository contactRepository;

    @Test
    public void testCreateContact() {
        // Input data
        Contact contact = Contact.builder()
                .firstName("John")
                .lastName("Doe")
                .phone("123456789")
                .build();

        // Mock the repository
        when(contactRepository.save(contact)).thenReturn(contact);

        // Call the service
        ContactDTO createdContact = contactService.addContact(contactService.parseToDTO(contact));

        // Assertions
        assertThat(createdContact).isNotNull();
        assertThat(createdContact.getFirstName()).isEqualTo("John");
        assertThat(createdContact.getLastName()).isEqualTo("Doe");
        assertThat(createdContact.getPhone()).isEqualTo("123456789");

        // Verify if the repository method was called correctly
        verify(contactRepository, times(1)).save(contact);
    }

    @Test
    public void testGetContactById() {
        // Input data
        long contactId = 1L;
        Contact contact = Contact.builder()
                .id(contactId)
                .firstName("John")
                .lastName("Doe")
                .phone("123456789")
                .build();


        // Mock the repository
        when(contactRepository.findById(contactId)).thenReturn(Optional.of(contact));

        // Call the service
        ContactDTO retrievedContact = contactService.getContactById(contactId);

        // Assertions
        assertThat(retrievedContact).isNotNull();
        assertThat(retrievedContact.getId().longValue()).isEqualTo(contactId);
        assertThat(retrievedContact.getFirstName()).isEqualTo("John");
        assertThat(retrievedContact.getLastName()).isEqualTo("Doe");
        assertThat(retrievedContact.getPhone()).isEqualTo("123456789");

        // Verify if the repository method was called correctly
        verify(contactRepository, times(1)).findById(contactId);
    }
}