package com.ejbs.phonebook.service;

import com.ejbs.phonebook.model.Contact;
import com.ejbs.phonebook.repository.ContactRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
        Contact contact = new Contact();
        contact.setFirstName("John");
        contact.setLastName("Doe");
        contact.setPhone("123456789");

        // Chamada do serviço
        Contact createdContact = contactService.createContact(contact);

        // Verificações
        assertNotNull(createdContact.getId());
        assertThat(createdContact.getFirstName()).isEqualTo("John");
        assertThat(createdContact.getLastName()).isEqualTo("Doe");
        assertThat(createdContact.getPhone()).isEqualTo("123456789");

        // Verifica se o contato foi salvo no banco de dados
        Contact retrievedContact = contactRepository.findById(createdContact.getId()).orElse(null);
        assertNotNull(retrievedContact);
        assertThat(retrievedContact.getFirstName()).isEqualTo("John");
        assertThat(retrievedContact.getLastName()).isEqualTo("Doe");
        assertThat(retrievedContact.getPhone()).isEqualTo("123456789");
    }

    @Test
    public void testGetContactById() {
        // Dados de entrada
        Contact contact = new Contact();
        contact.setFirstName("John");
        contact.setLastName("Doe");
        contact.setPhone("123456789");

        // Salva um contato no banco de dados
        Contact savedContact = contactRepository.save(contact);

        // Chamada do serviço
        Contact retrievedContact = contactService.getContactById(savedContact.getId());

        // Verificações
        assertNotNull(retrievedContact);
        assertThat(retrievedContact.getId()).isEqualTo(savedContact.getId());
        assertThat(retrievedContact.getFirstName()).isEqualTo("John");
        assertThat(retrievedContact.getLastName()).isEqualTo("Doe");
        assertThat(retrievedContact.getPhone()).isEqualTo("123456789");
    }

    // Mais testes de integração podem ser adicionados aqui
}
