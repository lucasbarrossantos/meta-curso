package com.metacurso.repository;

import com.metacurso.model.Materiais;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaterialRepository extends JpaRepository<Materiais, Integer> {

    Page<Materiais> findAllByNomeContainingIgnoreCase(String nome, Pageable pageable);
}
