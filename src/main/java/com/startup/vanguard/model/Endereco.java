package com.startup.vanguard.model;


import jakarta.persistence.Embeddable;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Embeddable
@Table(name = "enderecos")
public class Endereco {

    @NotBlank(message = "A rua é obrigatória")
    @Size(max = 100, message = "Rua pode ter no máximo 100 caracteres")
    private String rua;

    @NotBlank(message = "O número é obrigatório")
    @Size(max = 10, message = "Número pode ter no máximo 10 caracteres")
    private String numero;

    @Size(max = 50, message = "Complemento pode ter no máximo 50 caracteres")
    private String complemento;

    @NotBlank(message = "O bairro é obrigatório")
    @Size(max = 60, message = "Bairro pode ter no máximo 60 caracteres")
    private String bairro;

    @NotBlank(message = "A cidade é obrigatória")
    @Size(max = 60, message = "Cidade pode ter no máximo 60 caracteres")
    private String cidade;

    @NotBlank(message = "O estado é obrigatório")
    @Size(min = 2, max = 2, message = "Estado deve ter 2 caracteres (UF)")
    private String estado;

    @NotBlank(message = "O CEP é obrigatório")
    @Size(min = 8, max = 9, message = "CEP deve ter 8 dígitos (com ou sem hífen)")
    private String cep;
}
