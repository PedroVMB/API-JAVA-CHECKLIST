package com.br.APIChecklist.domain.torre;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroTorre(
        @NotNull
        Long idCondominio,
        @NotNull
        int numero_torre,
        @NotNull
        int quantidade_andares,
        @NotNull
        int quantidade_garagens,
        @NotNull
        int quantidade_salao_de_festas,
        @NotNull
        int quantidade_guaritas,
        @NotNull
        int quantidade_terracos

) {
}
