package com.metacurso.repository;

import com.metacurso.model.Categorias;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categorias, Integer> {

    Page<Categorias> findAllByNomeContainingIgnoreCase(String nome, Pageable pageable);

}
