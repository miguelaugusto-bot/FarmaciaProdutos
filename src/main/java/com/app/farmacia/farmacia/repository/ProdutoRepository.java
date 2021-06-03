package com.app.farmacia.farmacia.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.farmacia.farmacia.model.Produtos;

@Repository
public interface ProdutoRepository extends JpaRepository<Produtos, Long>{
	
	List<Produtos> findAllByNomeContaining(String nome);
	
	public Optional<Produtos> findByNome(String nome);
}
