package it.svitali.dashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.svitali.dashboard.model.Categoria;

public interface CategoriaReposity extends JpaRepository<Categoria,Integer>{

}
