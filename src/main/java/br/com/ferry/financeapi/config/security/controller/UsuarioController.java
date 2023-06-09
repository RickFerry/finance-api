package br.com.ferry.financeapi.config.security.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
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

import br.com.ferry.financeapi.config.security.model.Usuario;
import br.com.ferry.financeapi.service.UsuarioService;
import jakarta.annotation.security.RolesAllowed;
import lombok.extern.java.Log;

@Log
@RestController
@RequestMapping("/usuarios")
@EnableMethodSecurity(prePostEnabled = true)
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    @RolesAllowed({"ADMIN"})
    public ResponseEntity<List<Usuario>> findAll() {
        return ResponseEntity.ok().body(usuarioService.findAll());
    }

    @PostMapping
    @Transactional
    @RolesAllowed({"ADMIN"})
    public ResponseEntity<Usuario> save(@RequestBody Usuario usuario,
            UriComponentsBuilder uriComponentsBuilder) {
        return ResponseEntity
                .created(
                        uriComponentsBuilder
                                .path("/usuarios/{id}")
                                .buildAndExpand(usuario.getId()).toUri())
                .body(usuarioService.save(usuario));
    }

    @GetMapping("/{id}")
    @RolesAllowed({"ADMIN"})
    public ResponseEntity<Usuario> getById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok().body(usuarioService.getById(id));
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
        usuarioService.delete(id);
    }

    @PutMapping("/{id}")
    @RolesAllowed({"ADMIN"})
    public ResponseEntity<Usuario> update(@PathVariable Long id, @RequestBody Usuario usuario) {
        return ResponseEntity.ok().body(usuarioService.update(id, usuario));
    }
}
