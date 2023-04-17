package br.com.ferry.financeapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.ferry.financeapi.model.Lancamento;
import br.com.ferry.financeapi.service.LancamentoService;
import lombok.extern.java.Log;

@Log
@RestController
@RequestMapping("/lancamentos")
public class LancamentoController {

    @Autowired
    private LancamentoService lancamentoService;

    @GetMapping
    public ResponseEntity<Page<Lancamento>> findAll(Pageable page) {
        return ResponseEntity.ok(lancamentoService.findAll(page));

    }

    @PostMapping
    @Transactional
    public ResponseEntity<Lancamento> save(@RequestBody Lancamento lancamento,
            UriComponentsBuilder uriComponentsBuilder) {
        return ResponseEntity
                .created(
                        uriComponentsBuilder
                                .path("/lancamentos/{id}")
                                .buildAndExpand(lancamento.getId()).toUri())
                .body(lancamentoService.save(lancamento));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Lancamento> getById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok().body(lancamentoService.getById(id));
        } catch (NotFoundException e) {
            log.info(e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @Transactional
    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(Long id) {
        lancamentoService.delete(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Lancamento> update(@PathVariable Long id, @RequestBody Lancamento lancamento) {
        return ResponseEntity.ok().body(lancamentoService.update(id, lancamento));
    }
}
