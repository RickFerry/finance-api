package br.com.ferry.financeapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.ferry.financeapi.model.Categoria;
import br.com.ferry.financeapi.repository.CategoriaRepository;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Page<Categoria> findAll(Pageable page) {
        return categoriaRepository.findAll(page);
    }

    public Categoria save(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public Categoria getById(Long id) throws NotFoundException {
        return categoriaRepository.findById(id).orElseThrow(NotFoundException::new);
    }
}
