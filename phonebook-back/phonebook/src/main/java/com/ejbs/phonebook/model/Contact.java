package com.ejbs.phonebook.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome não pode estar vazio")
    @Size(max = 100, message = "O nome pode ter no máximo 100 caracteres")
    private String firstName;

    @NotBlank(message = "O sobrenome não pode estar vazio")
    @Size(max = 100, message = "O sobrenome pode ter no máximo 100 caracteres")
    private String lastName;

    @NotBlank(message = "O telefone não pode estar vazio")
    @Size(max = 15, message = "O telefone pode ter no máximo 15 caracteres")
    @Pattern(regexp = "\\+?\\d{1,3}?[-.\\s]?\\(?\\d{1,3}?\\)?[-.\\s]?\\d{1,4}[-.\\s]?\\d{1,4}[-.\\s]?\\d{1,9}",
            message = "O telefone deve estar em um formato válido")
    private String phone;
}
