package com.br.APIChecklist.domain.condominio;

import com.br.APIChecklist.domain.endereco.DadosEndereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroCondominio(
        @NotBlank
        String nome,

        @NotBlank
        String cnpj,

        @NotBlank
        int quantidade_torres,

        @NotNull @Valid
        DadosEndereco endereco
) {
}
