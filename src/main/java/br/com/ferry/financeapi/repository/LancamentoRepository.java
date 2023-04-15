package br.com.ferry.financeapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ferry.financeapi.model.Lancamento;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {
}
