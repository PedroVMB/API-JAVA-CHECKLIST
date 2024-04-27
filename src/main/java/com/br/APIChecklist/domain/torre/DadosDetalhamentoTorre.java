package com.br.APIChecklist.domain.torre;

public record DadosDetalhamentoTorre(Long id, Long condominioId, int numeroTorre, int quantidade_andares, int quantidade_garagens, int quantidade_salao_de_festas,int quantidade_guaritas, int quantidade_terracos, Boolean ativo) {
    public DadosDetalhamentoTorre(Torre torre){
        this(torre.getId(), torre.getCondominio().getId(), torre.getNumero_torre(), torre.getQuantidade_andares(), torre.getQuantidade_garagens(), torre.getQuantidade_salao_de_festas(), torre.getQuantidade_guaritas(), torre.getQuantidade_terracos(), torre.getAtivo());
    }
}
