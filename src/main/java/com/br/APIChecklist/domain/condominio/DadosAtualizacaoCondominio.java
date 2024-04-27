package com.br.APIChecklist.domain.condominio;

import com.br.APIChecklist.domain.endereco.DadosEndereco;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoCondominio(
        @NotNull
        Long id,
        String nome,
        String cnpj,
        int quantidade_torres,
        DadosEndereco endereco
) {
}
