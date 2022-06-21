package com.example.logisticapi.api.model.input;

import com.example.logisticapi.api.ValidationGroups;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ClienteInput {
    @NotNull(groups = ValidationGroups.ClienteId.class)
    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String telefone;
}
