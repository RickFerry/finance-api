package br.com.ferry.financeapi.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import br.com.ferry.financeapi.exception.PessoaInexistenteOuInativaException;
import br.com.ferry.financeapi.model.Lancamento;
import br.com.ferry.financeapi.repository.LancamentoRepository;

@Service
public class LancamentoService {

    @Autowired
    private LancamentoRepository lancamentoRepository;

    public List<Lancamento> findAll() {
        return lancamentoRepository.findAll();
    }

    public Lancamento save(Lancamento lancamento) {
        if (lancamento.getPessoa() != null || lancamento.getPessoa().getAtivo()) {
            return lancamentoRepository.save(lancamento);
        }
        throw new PessoaInexistenteOuInativaException();
    }

    public Lancamento getById(Long id) throws NotFoundException {
        return lancamentoRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    public void delete(Long id) {
        lancamentoRepository.deleteById(id);
    }

    public Lancamento update(Long id, Lancamento lancamento) {
        Lancamento original = lancamentoRepository.getReferenceById(id);
        BeanUtils.copyProperties(lancamento, original, "id");
        return lancamentoRepository.save(original);
    }
}
