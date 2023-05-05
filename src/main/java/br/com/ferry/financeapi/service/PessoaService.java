package br.com.ferry.financeapi.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.ferry.financeapi.model.Pessoa;
import br.com.ferry.financeapi.repository.PessoaRepository;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public Page<Pessoa> findAll(Pageable page) {
        return pessoaRepository.findAllByAtivoTrue(page);
    }

    public Pessoa save(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    public Pessoa getById(Long id) throws NotFoundException {
        return pessoaRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    public void delete(Long id) {
        Pessoa pessoa = pessoaRepository.getReferenceById(id);
        pessoa.inativar();
    }

    public Pessoa update(Long id, Pessoa pessoa) {
        Pessoa original = pessoaRepository.getReferenceById(id);
        BeanUtils.copyProperties(pessoa, original, "id");
        return pessoaRepository.save(original);
    }
}
