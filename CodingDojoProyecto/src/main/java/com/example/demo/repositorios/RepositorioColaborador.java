package com.example.demo.repositorios;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.modelos.Colaborador;

@Repository
public interface RepositorioColaborador extends CrudRepository<Colaborador, Long>{
	List<Colaborador> findAll();
}