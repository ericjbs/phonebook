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

    public List<ContactDTO> getAllContacts() {
        return contactRepository.findAll().stream().map(this::parseToDTO).collect(Collectors.toList());
    }

    public ContactDTO parseToDTO(Contact contact) {
        return ContactDTO.builder()
                .id(contact.getId())
                .firstName(contact.getFirstName())
                .lastName(contact.getLastName())
                .phone(contact.getPhone())
                .build();
    }

    public Contact parseToEntity(ContactDTO contactDTO) {
        return Contact.builder()
                .firstName(contactDTO.getFirstName())
                .lastName(contactDTO.getLastName())
                .phone(contactDTO.getPhone())
                .build();
    }

    public ContactDTO getContactById(Long id) {
        return contactRepository.findById(id).map(this::parseToDTO)
                .orElseThrow(() -> new RuntimeException("Contact not found with id: " + id));
    }
    public List<ContactDTO> searchContacts(String searchTerm) {
        // Convert the search term to lowercase for case-insensitive search
        searchTerm = searchTerm.toLowerCase();

        // Call the contactRepository method to search for contacts by first name or last name
        List<Contact> contacts = contactRepository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(searchTerm, searchTerm);

        // Map the contacts to ContactDTO objects
        List<ContactDTO> contactDTOs = contacts.stream()
                .map(contact -> {
                    ContactDTO contactDTO = new ContactDTO();
                    contactDTO.setId(contact.getId());
                    contactDTO.setFirstName(contact.getFirstName());
                    contactDTO.setLastName(contact.getLastName());
                    contactDTO.setPhone(contact.getPhone());
                    return contactDTO;
                })
                .collect(Collectors.toList());

        return contactDTOs;
    }

    public ContactDTO addContact(ContactDTO contactDTO) {
        return this.parseToDTO(contactRepository.save(this.parseToEntity(contactDTO)));
    }

    public ContactDTO updateContact(Long id, ContactDTO contactDTO) {
        ContactDTO existingContactDTO = getContactById(id);
        existingContactDTO.setFirstName(contactDTO.getFirstName());
        existingContactDTO.setLastName(contactDTO.getLastName());
        existingContactDTO.setPhone(contactDTO.getPhone());
        return this.parseToDTO(contactRepository.save(this.parseToEntity(existingContactDTO)));
    }

    public void deleteContact(Long id) {
        ContactDTO contactDTO = getContactById(id);
        contactRepository.delete(this.parseToEntity(contactDTO));
    }
}

