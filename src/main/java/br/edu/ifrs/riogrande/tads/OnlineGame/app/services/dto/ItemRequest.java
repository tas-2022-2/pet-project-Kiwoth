package br.edu.ifrs.riogrande.tads.OnlineGame.app.services.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ItemRequest {
    @NotBlank(message = "Item name cannot be null, empty or blank.")
    @Size(min = 4, max = 32, message = "Item name must be of length between 4 and 32.")
    String name;

    @NotBlank(message = "Item description cannot be null, empty or blank.")
    String description;

    @Positive(message = "Item value must be higher than '0'.")
    Integer value;

    @NotNull(message = "Item resource cannot be null, empty or blank.")
    Boolean resource;
}
