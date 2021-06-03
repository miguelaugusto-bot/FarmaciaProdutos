package com.app.farmacia.farmacia.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.app.farmacia.farmacia.model.Produtos;
import com.app.farmacia.farmacia.repository.ProdutoRepository;

@Service
public class ProdutoServices {

	private @Autowired ProdutoRepository repository;
	
	public ResponseEntity<List<Produtos>> findAllProdutos(){
		List<Produtos> listaDeProdutos = repository.findAll();
		if(!listaDeProdutos.isEmpty()) {
			return ResponseEntity.status(200).body(listaDeProdutos);
		}
		else {
			return ResponseEntity.status(201).build();
		}
	}
	
	public ResponseEntity<Produtos> findByProdutosId(long id){
		return repository.findById(id)
				.map(encontrado -> ResponseEntity.status(200).body(encontrado))
				.orElse(ResponseEntity.status(204).build());
	}
	
	public ResponseEntity<List<Produtos>> findByNomeProdutos(String nome){
		List<Produtos> findProdutos = repository.findAllByNomeContaining(nome);
		if(!findProdutos.isEmpty()) {
			return ResponseEntity.status(302).body(findProdutos);
		}
		else {
			return ResponseEntity.status(204).build();
		}
	}
	
	public ResponseEntity<Optional<Produtos>> postProdutos(Produtos produtos){
		Optional<Produtos> adicionarProdutos = repository.findByNome(produtos.getNome());
		if(adicionarProdutos.isPresent()) {
			return ResponseEntity.status(400).build();
		}else {
			return ResponseEntity.status(201).body(Optional.ofNullable(repository.save(produtos)));
		}
	}
	
	public ResponseEntity<Optional<Produtos>> putProdutos(Long id, Produtos nome){
		Optional<Produtos> updateProdutos = repository.findById(id);
		if(updateProdutos.isPresent()) {
			updateProdutos.get().setNome(nome.getNome());
			updateProdutos.get().setPreco(nome.getPreco());
			updateProdutos.get().setCategoria(nome.getCategoria());
			return ResponseEntity.status(201).body(Optional.ofNullable(repository.save(updateProdutos.get())));
		}else {
			return ResponseEntity.status(400).build();
		}
	}
	
	public ResponseEntity<?> deleteProdutos(long id) {
		repository.deleteById(id);
		return ResponseEntity.status(202).build();
	}
}
