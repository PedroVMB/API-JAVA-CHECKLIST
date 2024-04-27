package com.br.APIChecklist.domain.condominio;

import com.br.APIChecklist.domain.endereco.Endereco;

public record DadosDetalhamentoCondominio(Long id, String nome, String cnpj, int quantidade_torres, Endereco endereco, Boolean ativo) {
    public DadosDetalhamentoCondominio(Condominio condominio){
        this(condominio.getId(), condominio.getNome(), condominio.getCnpj(), condominio.getQuantidade_torres(), condominio.getEndereco(), condominio.getAtivo());
    }
}
