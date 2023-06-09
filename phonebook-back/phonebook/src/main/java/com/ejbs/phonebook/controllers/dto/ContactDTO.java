package com.ejbs.phonebook.controllers.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Transient;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContactDTO {
    @Transient
    private Long id;

    @NotBlank(message = "O nome não pode estar vazio")
    @Size(max = 100, message = "O nome pode ter no máximo 100 caracteres")
    private String firstName;

    @NotBlank(message = "O sobrenome não pode estar vazio")
    @Size(max = 100, message = "O sobrenome pode ter no máximo 100 caracteres")
    private String lastName;

    @NotBlank(message = "O telefone não pode estar vazio")
    @Size(max = 15, message = "O telefone pode ter no máximo 15 caracteres")
    @Pattern(regexp = "^(\\d{11})$|^(\\d{9})$|^(\\(?\\d{2}\\)?)?\\s?([9]{1})?\\s?(\\d{4})-?(\\d{4})$",
            message = "O telefone deve estar em um formato válido")
    private String phone;

}

