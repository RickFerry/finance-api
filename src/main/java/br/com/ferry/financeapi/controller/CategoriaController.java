package br.com.ferry.financeapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.ferry.financeapi.model.Categoria;
import br.com.ferry.financeapi.service.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;
    
    @GetMapping
    public ResponseEntity<List<Categoria>> findAll() {
        return categoriaService.findAll();
    }

    @PostMapping
    public ResponseEntity<Categoria> save(@RequestParam Categoria categoria) {
        return categoriaService.save(categoria);
    }
}
