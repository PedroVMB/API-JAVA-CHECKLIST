package com.br.APIChecklist.domain.sindico;

import com.br.APIChecklist.domain.administrador.Administrador;

public record DadosDetalhamentoSindico(Long id, String nome, String email, String cpf, Boolean ativo) {
    public DadosDetalhamentoSindico(Sindico sindico){
        this(sindico.getId(), sindico.getNome(), sindico.getEmail(), sindico.getCpf(), sindico.getAtivo());
    }
}
