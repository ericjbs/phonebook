package com.ejbs.phonebook.controllers.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContactDTO {
    private Long id;

    @NotBlank(message = "First name cannot be empty")
    @Size(max = 100, message = "First name can have at most 100 characters")
    private String firstName;

    @NotBlank(message = "Last name cannot be empty")
    @Size(max = 100, message = "Last name can have at most 100 characters")
    private String lastName;

    @NotBlank(message = "Phone number cannot be empty")
    @Size(max = 15, message = "Phone number can have at most 15 characters")
    @Pattern(regexp = "\\+?\\d{1,3}?[-.\\s]?\\(?\\d{1,3}?\\)?[-.\\s]?\\d{1,4}[-.\\s]?\\d{1,4}[-.\\s]?\\d{1,9}",
            message = "Phone number must be in a valid format")
    private String phone;


}

