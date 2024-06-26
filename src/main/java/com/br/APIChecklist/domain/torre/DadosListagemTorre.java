package com.br.APIChecklist.domain.torre;

public record DadosListagemTorre(Long id, Long condominioId, int numeroTorre, int quantidade_andares) {
    public DadosListagemTorre(Torre torre){
        this(torre.getId(), torre.getCondominio().getId(), torre.getNumero_torre(), torre.getQuantidade_andares());
    }
}
