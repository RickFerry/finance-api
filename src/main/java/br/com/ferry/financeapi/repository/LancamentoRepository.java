package br.com.ferry.financeapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ferry.financeapi.model.Lancamento;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {

    Page<Lancamento> findAllByAtivoTrue(Pageable page);
}
