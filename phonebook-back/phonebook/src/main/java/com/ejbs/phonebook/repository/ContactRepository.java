package com.ejbs.phonebook.repository;

import com.ejbs.phonebook.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
    // Você pode adicionar métodos personalizados de consulta aqui, se necessário
}
