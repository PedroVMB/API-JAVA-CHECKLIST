package com.br.APIChecklist.domain.sindico;

import com.br.APIChecklist.domain.administrador.Administrador;

public record DadosListagemSindico(Long id, String nome, String email, String cpf) {
    public DadosListagemSindico(Sindico sindico){
        this(sindico.getId(), sindico.getNome(), sindico.getEmail(), sindico.getCpf());
    }
}
