package com.br.APIChecklist.domain.condominio;

import com.br.APIChecklist.domain.endereco.Endereco;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "condominio")
@Entity(name = "Condominio")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Condominio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cnpj;

    @Embedded
    private Endereco endereco;

    private int quantidade_torres;
    private Boolean ativo;

    public Condominio(DadosCadastroCondominio dados){
        this.nome = dados.nome();
        this.cnpj = dados.cnpj();
        this.endereco = new Endereco(dados.endereco());
        this.quantidade_torres = dados.quantidade_torres();
        this.ativo = true;

    }

    public void atualizarInformacoes(DadosAtualizacaoCondominio dados){
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.cnpj() != null) {
            this.cnpj = dados.cnpj();
        }
        if (dados.quantidade_torres() >= 1) {
            this.quantidade_torres = dados.quantidade_torres();
        }
        if (dados.endereco() != null) {
            this.endereco.atualizarInformacoes(dados.endereco());
        }
    }



    public void excluir() {
        this.ativo = false;
    }
}
