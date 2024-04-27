package com.br.APIChecklist.domain.condominio;

public record DadosListagemCondominio (Long id, String nome, String cnpj, int quantidade_torres){
    public DadosListagemCondominio(Condominio condominio){
        this(condominio.getId(), condominio.getNome(), condominio.getCnpj(), condominio.getQuantiadade_torres());
    }
}
