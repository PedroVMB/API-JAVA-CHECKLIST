package com.br.APIChecklist.controller;

import com.br.APIChecklist.domain.condominio.Condominio;
import com.br.APIChecklist.domain.condominio.CondominioRepository;
import com.br.APIChecklist.domain.registro.*;
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
@RequestMapping("registros")
public class RegistroController {
    @Autowired
    private CondominioRepository condominioRepository;

    @Autowired
    private TorreRepository torreRepository;

    @Autowired
    private RegistroRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroRegistro dados, UriComponentsBuilder uriBuilder) {
        Condominio condominio = condominioRepository.findById(dados.idCondominio())
                .orElseThrow(() -> new IllegalArgumentException("Condomínio não encontrado"));

        Torre torre = torreRepository.findById(dados.idTorre())
                .orElseThrow(() -> new IllegalArgumentException("Condomínio não encontrado"));

        Registro registro = new Registro(dados, condominio.getId(), condominioRepository, torre.getId(), torreRepository);
        repository.save(registro);

        URI uri = uriBuilder.path("/registros/{id}").buildAndExpand(torre.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoRegistro(registro));
    }


    @GetMapping
    public ResponseEntity<Page<DadosListagemRegistro>> listar(@PageableDefault(size = 10, sort = {"tipo_problema"}) Pageable paginacao) {
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemRegistro::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity atualizar(@PathVariable Long id, @RequestBody @Valid DadosAtualizacaoRegistro dados) {
        Registro registro = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Registro não encontrado"));

        Condominio condominio = condominioRepository.findById(dados.idCondominio())
                .orElseThrow(() -> new IllegalArgumentException("Condomínio não encontrado"));

        Torre torre = torreRepository.findById(dados.idTorre())
                .orElseThrow(() -> new IllegalArgumentException("Torre não encontrada"));

        registro.atualizarInformacoes(dados, condominio.getId(), condominioRepository, torre.getId(), torreRepository);

        return ResponseEntity.ok(new DadosDetalhamentoRegistro(registro));
    }


    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        var registro = repository.getReferenceById(id);
        registro.excluir();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        var registro = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoRegistro(registro));
    }
}
