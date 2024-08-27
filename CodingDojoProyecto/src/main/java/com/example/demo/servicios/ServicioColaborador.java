package com.example.demo.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.modelos.Colaborador;
import com.example.demo.modelos.Usuario;
import com.example.demo.repositorios.RepositorioColaborador;


@Service
public class ServicioColaborador {
	@Autowired
	private final RepositorioColaborador repositorioColaborador;

	public ServicioColaborador(RepositorioColaborador repositorioColaborador) {
		this.repositorioColaborador = repositorioColaborador;
	}
	
	public Colaborador insertarColaborador(Colaborador nuevoColaborador){
		return this.repositorioColaborador.save(nuevoColaborador);
	}
	
	public Colaborador obtenerPorId(Long id) {
		return this.repositorioColaborador.findById(id).orElse(null);
	}
}
