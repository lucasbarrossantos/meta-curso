package com.metacurso.repository;

import com.metacurso.model.CursosDisciplinas;
import com.metacurso.model.vo.DisciplinaComboDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CursosDisciplinaRepository extends JpaRepository<CursosDisciplinas, Integer> {

    @Query("select cd.disciplina.codigo as codigo, cd.disciplina.nome as nome from CursosDisciplinas as cd where cd.curso.codigo = ?1")
    List<DisciplinaComboDTO> disciplinasComboDTO(Integer cursoID);

    CursosDisciplinas findByCursoCodigoAndDisciplinaCodigo(Integer cursoId, Integer disciplinaId);

    List<CursosDisciplinas> findAllByCursoCodigoAndDisciplinaCodigo(Integer cursoId, Integer disciplinaId);

}
