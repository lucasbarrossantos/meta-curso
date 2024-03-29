package com.metacurso.repository;

import com.metacurso.model.Cursos;
import com.metacurso.model.vo.CursoComboDTO;
import com.metacurso.model.vo.CursoDTO;
import com.metacurso.repository.curso.CursoRepositoryQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CursoRepository extends JpaRepository<Cursos, Integer>, CursoRepositoryQuery {

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

    @Query("select new com.metacurso.model.vo.CursoDTO(c.codigo, " +
            "c.nome," +
            "c.descricao," +
            "c.observacoes, " +
            "c.taxa_matricula, " +
            "c.total_material, " +
            "c.valorCurso, " +
            "c.status) " +
            "from Cursos as c order by c.nome desc ")
    Page<CursoDTO> cursoDTO(Pageable pageable);

    @Query("select c.codigo as codigo, c.nome as nome from Cursos as c")
    List<CursoComboDTO> CursosComboDTO();

}
