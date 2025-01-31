package com.example.demo.repositorios;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.modelos.Cancion;

@Repository
public interface RepositorioCancion extends CrudRepository<Cancion, Long>{
	List<Cancion> findAll();
	boolean existsByTitulo(String titulo);
}