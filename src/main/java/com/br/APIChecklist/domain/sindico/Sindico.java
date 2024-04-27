package com.br.APIChecklist.domain.sindico;

import com.br.APIChecklist.domain.administrador.DadosAtualizacaoAdministrador;
import com.br.APIChecklist.domain.administrador.DadosCadastroAdministrador;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "sindico")
@Entity(name = "Sindico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Sindico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String cpf;

    private Boolean ativo;

    public Sindico(DadosCadastroSindico dados){
        this.nome = dados.nome();
        this.email = dados.email();
        this.cpf = dados.cpf();

        this.ativo = true;
    }

    public void atualizarInformacoes(DadosAtualizacaoSindico dados){
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.email() != null) {
            this.email = dados.email();
        }
        if (dados.cpf() != null) {
            this.cpf = dados.cpf();
        }
    }

    public void excluir() {
        this.ativo = false;
    }
}
