package com.metacurso.repository;

import com.metacurso.model.Cursos;
import com.metacurso.model.vo.CursoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CursoRepository extends JpaRepository<Cursos, Integer> {

    @Query("select new com.metacurso.model.vo.CursoDTO(" +
            "c.codigo, " +
            "c.nome," +
            "c.descricao," +
            "c.observacoes, " +
            "c.taxa_matricula, " +
            "c.total_material, " +
            "c.valorCurso, " +
            "c.status)" +
            "from Cursos as c where c.codigo = ?1")
    CursoDTO cursoEditDTO(Integer cursoId);

}
