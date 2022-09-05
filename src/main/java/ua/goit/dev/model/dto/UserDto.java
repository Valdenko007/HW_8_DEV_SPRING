package ua.goit.dev.model.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private UUID id;
    @Email(regexp = ".+@.+\\..+", message = "Please, use valid email")
    private String email;

    private String password;
    @NotBlank(message = "Please, enter your name")
    private String firstName;
    @NotBlank(message = "Please, enter your lastname")
    private String lastName;
}
