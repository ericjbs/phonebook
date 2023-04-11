package com.ejbs.phonebook.service;

import com.ejbs.phonebook.controllers.dto.ContactDTO;
import com.ejbs.phonebook.repository.ContactRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

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
        ContactDTO contact = new ContactDTO();
        contact.setFirstName("John");
        contact.setLastName("Doe");
        contact.setPhone("123456789");

        // Mock do repositório
        when(contactRepository.save(contact)).thenReturn(contact);

        // Chamada do serviço
        ContactDTO createdContact = contactService.createContact(contact);

        // Verificações
        assertNotNull(createdContact);
        assertEquals("John", createdContact.getFirstName());
        assertEquals("Doe", createdContact.getLastName());
        assertEquals("123456789", createdContact.getPhone());

        // Verifica se o método do repositório foi chamado corretamente
        verify(contactRepository, times(1)).save(contact);
    }

    @Test
    public void testGetContactById() {
        // Dados de entrada
        long contactId = 1L;
        ContactDTO contact = new Contact();
        contact.setId(contactId);
        contact.setFirstName("John");
        contact.setLastName("Doe");
        contact.setPhone("123456789");

        // Mock do repositório
        when(contactRepository.findById(contactId)).thenReturn(Optional.of(contact));

        // Chamada do serviço
        ContactDTO retrievedContact = contactService.getContactById(contactId);

        // Verificações
        assertNotNull(retrievedContact);
        assertEquals(contactId, retrievedContact.getId().longValue());
        assertEquals("John", retrievedContact.getFirstName());
        assertEquals("Doe", retrievedContact.getLastName());
        assertEquals("123456789", retrievedContact.getPhone());

        // Verifica se o método do repositório foi chamado corretamente
        verify(contactRepository, times(1)).findById(contactId);
    }

    // Mais testes de serviço podem ser adicionados aqui
}

