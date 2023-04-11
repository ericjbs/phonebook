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

    @NotBlank(message = "First name cannot be empty")
    @Size(max = 100, message = "First name can have at most 100 characters")
    private String firstName;

    @NotBlank(message = "Last name cannot be empty")
    @Size(max = 100, message = "Last name can have at most 100 characters")
    private String lastName;

    @NotBlank(message = "Phone number cannot be empty")
    @Size(max = 15, message = "Phone number can have at most 15 characters")
    @Pattern(regexp = "^(\\d{11})$|^(\\d{9})$|^(\\(?\\d{2}\\)?)?\\s?([9]{1})?\\s?(\\d{4})-?(\\d{4})$",
            message = "Phone number must be in a valid format")
    private String phone;
}
