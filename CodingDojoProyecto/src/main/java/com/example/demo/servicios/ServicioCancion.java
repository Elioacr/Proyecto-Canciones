package com.example.demo.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.modelos.Cancion;
import com.example.demo.repositorios.RepositorioCancion;

import jakarta.transaction.Transactional;

@Service
public class ServicioCancion {
	@Autowired
	private final RepositorioCancion repositorioCancion;

	public ServicioCancion(RepositorioCancion repositorioCancion) {
		this.repositorioCancion = repositorioCancion;
	}

	public Cancion insertarCancion(Cancion nuevaCancion){
		return this.repositorioCancion.save(nuevaCancion);
	}

	public List<Cancion> obtenerTodos(){
		return this.repositorioCancion.findAll();
	}

	public Cancion obtenerPorId(Long id) {
		return this.repositorioCancion.findById(id).orElse(null);
	}

	public void eliminarPorId(Long id) {
		this.repositorioCancion.deleteById(id);
	}

	@Transactional
	public Cancion actualizarCancion(Cancion cancion) {
		return this.repositorioCancion.save(cancion);
	}
	
	public boolean existePorTitulo(String titulo) {
        return repositorioCancion.existsByTitulo(titulo);
    }
}