package br.com.ferry.financeapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.ferry.financeapi.model.Categoria;
import br.com.ferry.financeapi.repository.CategoriaRepository;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public ResponseEntity<List<Categoria>> findAll() {
        return ResponseEntity.ok().body(categoriaRepository.findAll());
    }

    public ResponseEntity<Categoria> save(Categoria categoria) {
        return ResponseEntity.ok(categoriaRepository.save(categoria));
    }
}
