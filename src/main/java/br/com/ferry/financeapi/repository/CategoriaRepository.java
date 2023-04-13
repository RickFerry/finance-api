package br.com.ferry.financeapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ferry.financeapi.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{
}
