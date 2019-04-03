package com.metacurso.repository;

import com.metacurso.model.Cursos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Cursos, Integer> {

}
