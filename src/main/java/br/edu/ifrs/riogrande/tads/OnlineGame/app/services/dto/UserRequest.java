package br.edu.ifrs.riogrande.tads.OnlineGame.app.services.dto;

i

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRequest {
    @NotBlank(message = "User username cannot be null, empty or blank.")
    @Size(min = 4, max = 32, message = "User username must be of length between 4 and 32.")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "username must contain only letters, '_' or '-''.")
    String username;

    @NotNull(message = "User password cannot be null.")
    @Size(min = 4, max = 64, message = "User password must be of length between 4 and 32.")
    String password;
}
