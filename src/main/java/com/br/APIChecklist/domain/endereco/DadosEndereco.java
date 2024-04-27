package com.br.APIChecklist.domain.endereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosEndereco(
        @NotBlank
        String bairro,
        @NotBlank
        @Pattern(regexp = "\\d{8}")
        String cep,
        @NotBlank
        String complemento,
        @NotBlank
        String numero,
        @NotBlank
        String uf,
        @NotBlank
        String cidade
) {
}
