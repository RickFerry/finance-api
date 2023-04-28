package br.com.ferry.financeapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
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

import br.com.ferry.financeapi.model.Pessoa;
import br.com.ferry.financeapi.service.PessoaService;
import jakarta.annotation.security.RolesAllowed;
import lombok.extern.java.Log;

@Log
@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @GetMapping
    @RolesAllowed({"ADMIN", "USER"})
    public ResponseEntity<List<Pessoa>> findAll() {
        return ResponseEntity.ok().body(pessoaService.findAll());

    }

    @PostMapping
    @Transactional
    @RolesAllowed({"ADMIN"})
    public ResponseEntity<Pessoa> save(@RequestBody Pessoa pessoa,
            UriComponentsBuilder uriComponentsBuilder) {
        return ResponseEntity
                .created(
                        uriComponentsBuilder
                                .path("/pessoas/{id}")
                                .buildAndExpand(pessoa.getId()).toUri())
                .body(pessoaService.save(pessoa));
    }

    @GetMapping("/{id}")
    @RolesAllowed({"ADMIN", "USER"})
    public ResponseEntity<Pessoa> getById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok().body(pessoaService.getById(id));
        } catch (NotFoundException e) {
            log.info(e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @Transactional
    @DeleteMapping
    @RolesAllowed({"ADMIN"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(Long id) {
        pessoaService.delete(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pessoa> update(@PathVariable Long id, @RequestBody Pessoa pessoa) {
        return ResponseEntity.ok().body(pessoaService.update(id, pessoa));
    }
}
