package br.com.ferry.financeapi.service;

import java.beans.Beans;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import br.com.ferry.financeapi.model.Pessoa;
import br.com.ferry.financeapi.repository.PessoaRepository;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public List<Pessoa> findAll() {
        return pessoaRepository.findAll();
    }

    public Pessoa save(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    public Pessoa getById(Long id) throws NotFoundException {
        return pessoaRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    public void delete(Long id) {
        pessoaRepository.deleteById(id);
    }

    public Pessoa update(Long id, Pessoa pessoa) {
        Pessoa original = pessoaRepository.getReferenceById(id);
        BeanUtils.copyProperties(pessoa, original, "id");
        return pessoaRepository.save(original);
    }
}
