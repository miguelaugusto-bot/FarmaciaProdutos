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

import com.app.farmacia.farmacia.model.Categoria;
import com.app.farmacia.farmacia.services.CategoriaServices;


@RestController
@RequestMapping("/categorias")
@CrossOrigin("*")
public class CategoriaController {
	
	private @Autowired CategoriaServices services;
	
	
	@GetMapping("/todas")
	public ResponseEntity<List<Categoria>> getAll(){
		return services.findAllCategorias();
	}
	
	@GetMapping("/pesquisar/id")
	public ResponseEntity<Categoria> getCategoriaId(@RequestParam(defaultValue = "") long id){
		return services.findByCategoriaId(id);
	}
	
	@GetMapping("/pesquisar/nome")
	public ResponseEntity<List<Categoria>> getCategoriaNome(@RequestParam(defaultValue = "") String tipo){
		return services.findByNomeCategoria(tipo);
	}
	
	@PostMapping("/adicionar")
	public ResponseEntity<Optional<Categoria>> postCategoriaNome(@RequestBody Categoria tipo){
		return services.postCategoria(tipo);
	}
	
	@PutMapping("/atualizar")
	public ResponseEntity<Optional<Categoria>> putCategoriaNome(@RequestBody Categoria tipo,@RequestParam(defaultValue = "") long id){
		return services.putCategoria(id, tipo);
	}
	
	@DeleteMapping("/deletar")
	public ResponseEntity<?> deleteCategoriaId(@RequestParam(defaultValue = "") long id){
		return services.deleteCategoria(id);
	}
}
