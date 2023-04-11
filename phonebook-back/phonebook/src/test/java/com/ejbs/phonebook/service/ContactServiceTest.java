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
        // Dados de entrada
        Contact contact = Contact.builder()
                .firstName("John")
                .lastName("Doe")
                .phone("123456789")
                .build();

        // Mock do repositório
        when(contactRepository.save(contact)).thenReturn(contact);

        // Chamada do serviço
        ContactDTO createdContact = contactService.addContact(contactService.parseToDTO(contact));

        // Verificações
        assertThat(createdContact).isNotNull();
        assertThat(createdContact.getFirstName()).isEqualTo("John");
        assertThat(createdContact.getLastName()).isEqualTo("Doe");
        assertThat(createdContact.getPhone()).isEqualTo("123456789");


        // Verifica se o método do repositório foi chamado corretamente
        verify(contactRepository, times(1)).save(contact);
    }

    @Test
    public void testGetContactById() {
        // Dados de entrada
        long contactId = 1L;
        Contact contact = new Contact();
        contact.setId(contactId);
        contact.setFirstName("John");
        contact.setLastName("Doe");
        contact.setPhone("123456789");

        // Mock do repositório
        when(contactRepository.findById(contactId)).thenReturn(Optional.of(contact));

        // Chamada do serviço
        ContactDTO retrievedContact = contactService.getContactById(contactId);

        // Verificações
        assertThat(retrievedContact).isNotNull();
        assertThat(retrievedContact.getId().longValue()).isEqualTo(contactId);
        assertThat(retrievedContact.getFirstName()).isEqualTo("John");
        assertThat(retrievedContact.getLastName()).isEqualTo("Doe");
        assertThat(retrievedContact.getPhone()).isEqualTo("123456789");

        // Verifica se o método do repositório foi chamado corretamente
        verify(contactRepository, times(1)).findById(contactId);
    }

    // Mais testes de serviço podem ser adicionados aqui
}

