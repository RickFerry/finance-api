package br.com.ferry.financeapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ferry.financeapi.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    Page<Pessoa> findAllByAtivoTrue(Pageable page);
}
