package com.app.farmacia.farmacia.model.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.farmacia.farmacia.model.Produtos;
import com.app.farmacia.farmacia.services.ProdutoServices;

@RestController
@RequestMapping("/produtos")
@CrossOrigin("*")
public class ProdutoController {
	
	private @Autowired ProdutoServices services;
	
	@GetMapping("/todos")
	public ResponseEntity<List<Produtos>> getAll(){
		return services.findAllProdutos();
	}
	
	@GetMapping("/pesquisar/id")
	public ResponseEntity<Produtos> getProdutosId(@RequestParam(defaultValue = "") long id){
		return services.findByProdutosId(id);
	}
	
	@GetMapping("/pesquisar/nome")
	public ResponseEntity<List<Produtos>> getProdutosNome(@RequestParam(defaultValue = "") String nome){
		return services.findByNomeProdutos(nome);
	}
	
	@PostMapping("/adicionar")
	public ResponseEntity<Optional<Produtos>> postProdutosNome(@RequestBody Produtos produtos){
		return services.postProdutos(produtos);
	}
	
	@PutMapping("/atualizar")
	public ResponseEntity<Optional<Produtos>> putProdutosNome(@RequestBody Produtos nome,@RequestParam(defaultValue = "") long id){
		return services.putProdutos(id, nome);
	}
	
	@DeleteMapping("/deletar")
	public ResponseEntity<?> deleteCategoriaId(@RequestParam(defaultValue = "") long id){
		return services.deleteProdutos(id);
	}
}
