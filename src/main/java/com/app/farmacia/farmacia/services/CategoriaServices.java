package com.app.farmacia.farmacia.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.app.farmacia.farmacia.model.Categoria;
import com.app.farmacia.farmacia.repository.CategoriaRepository;

import lombok.Data;

@Data
@Service
public class CategoriaServices {

	private @Autowired CategoriaRepository repository;
	
	public ResponseEntity<List<Categoria>> findAllCategorias(){
		List<Categoria> listaDeCategoria = repository.findAll();
		if(!listaDeCategoria.isEmpty()) {
			return ResponseEntity.status(200).body(listaDeCategoria);
		}
		else {
			return ResponseEntity.status(201).build();
		}
	}
	
	public ResponseEntity<Categoria> findByCategoriaId(long id){
		return repository.findById(id)
				.map(encontrado -> ResponseEntity.status(200).body(encontrado))
				.orElse(ResponseEntity.status(204).build());
	}
	
	public ResponseEntity<List<Categoria>> findByNomeCategoria(String tipo){
		List<Categoria> findCategoria = repository.findAllByTipoContaining(tipo);
		if(!findCategoria.isEmpty()) {
			return ResponseEntity.status(302).body(findCategoria);
		}
		else {
			return ResponseEntity.status(204).build();
		}
	}
	
	public ResponseEntity<Optional<Categoria>> postCategoria(Categoria tipo){
		Optional<Categoria> adicionarCategoria = repository.findByTipo(tipo.getTipo());
		if(adicionarCategoria.isPresent()) {
			return ResponseEntity.status(400).build();
		}else {
			return ResponseEntity.status(201).body(Optional.ofNullable(repository.save(tipo)));
		}
	}
	
	public ResponseEntity<Optional<Categoria>> putCategoria(Long id, Categoria tipo){
		Optional<Categoria> updateCategoria = repository.findById(id);
		if(updateCategoria.isPresent()) {
			updateCategoria.get().setTipo(tipo.getTipo());
			return ResponseEntity.status(201).body(Optional.ofNullable(repository.save(updateCategoria.get())));
		}else {
			return ResponseEntity.status(400).build();
		}
	}
	
	public ResponseEntity<?> deleteCategoria(long id) {
		repository.deleteById(id);
		return ResponseEntity.status(202).build();
	}
	
}
