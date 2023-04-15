package br.com.ferry.financeapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ferry.financeapi.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
}
