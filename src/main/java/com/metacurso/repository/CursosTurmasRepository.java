package com.metacurso.repository;

import com.metacurso.model.CursosTurmas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursosTurmasRepository extends JpaRepository<CursosTurmas, Integer> {

    CursosTurmas findOneByTurmaCodigo(Integer turmaId);

}
