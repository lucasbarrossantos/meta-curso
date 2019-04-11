package com.metacurso.repository;

import com.metacurso.model.Disciplinas;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DisciplinaRepository extends JpaRepository<Disciplinas, Integer> {

    Page<Disciplinas> findAllByNomeContainingIgnoreCase(String nome, Pageable pageable);
}
