package br.edu.ifrs.riogrande.tads.OnlineGame.app.services.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GameJobRequest {
    Integer id;

    @NotBlank(message = "Job name cannot be null, empty or blank.")
    @Size(min = 4, max = 32, message = "Job name must be of length between 4 and 32.")
    String name;

    @NotBlank(message = "Job stat cannot be null, empty or blank.")
    String stat;

    @Min(value = 1, message = "Job tickToDrop cannot be less than '1'.")
    Integer tickToDrop; // Every ticks counts as 5 seconds

    Double chanceToDrop;

    // @MapValidation(keys = { "name", "description", "value",
    // "resource" }, message = "drop must contain 'String name', 'String
    // description', 'Double value' and 'Boolean resource'.")
    ItemRequest drop;
}
