package com.startup.vanguard.DTO.lojista;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LojistaCreateDTO(
        @Email(message = "E-mail inválido")
        @NotBlank(message = "E-mail é obrigatório")
        String email,

        @NotBlank(message = "Senha é obrigatória")
        @Size(min = 6, message = "A senha deve ter pelo menos 6 caracteres")
        String password,

        @NotBlank(message = "Telefone é obrigatório")
        String telefone,

        @NotBlank(message = "Razão Social é obrigatória")
        String razaoSocial,

        @NotBlank(message = "CNPJ é obrigatório")
        String cnpj
) {
}
