package com.ejbs.phonebook.repository;

import com.ejbs.phonebook.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
    List<Contact> findByLastNameContainingIgnoreCase(String lastName);
}
