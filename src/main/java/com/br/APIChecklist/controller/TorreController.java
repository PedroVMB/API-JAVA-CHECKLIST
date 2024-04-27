package com.br.APIChecklist.controller;

import com.br.APIChecklist.domain.condominio.*;
import com.br.APIChecklist.domain.torre.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;


@RestController
@RequestMapping("torres")
public class TorreController {
    @Autowired
    private CondominioRepository condominioRepository;

    @Autowired
    private TorreRepository repository;


    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroTorre dados, UriComponentsBuilder uriBuilder) {
        Condominio condominio = condominioRepository.findById(dados.idCondominio())
                .orElseThrow(() -> new IllegalArgumentException("Condomínio não encontrado"));

        Torre torre = new Torre(dados, condominio.getId(), condominioRepository);
        repository.save(torre);

        URI uri = uriBuilder.path("/torres/{id}").buildAndExpand(torre.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoTorre(torre));
    }


    @GetMapping
    public ResponseEntity<Page<DadosListagemTorre>> listar(@PageableDefault(size = 10, sort = {"numero_torre"}) Pageable paginacao) {
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemTorre::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoTorre dados) {
        var torre = repository.getReferenceById(dados.idCondominio());
        Condominio condominio = condominioRepository.findById(dados.idCondominio())
                .orElseThrow(() -> new IllegalArgumentException("Condomínio não encontrado"));
        torre.atualizarInformacoes(dados, condominio.getId(), condominioRepository);

        return ResponseEntity.ok(new DadosDetalhamentoCondominio(condominio));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        var torre = repository.getReferenceById(id);
        torre.excluir();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        var torre = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoTorre(torre));
    }
}
