package com.apiexamples.payload;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class RegistrationDto {

    private Long id;

    @Size(min =2, max =12, message = "Should be more than 2 character")
    @Pattern(regexp = "^[a-zA-Z]*$" , message = "Should be alphabets only")
    private String name;

    @Email(message = "Invalid email format.")
    private String email;

    @Size(min = 10 , max = 10 , message = "Should be 10  digits.")        // @Size() can be used only on String
    private String mobile;
    private String message;


}