package com.metacurso.repository;

import com.metacurso.model.Turmas;
import com.metacurso.model.vo.TurmaEditDTO;
import com.metacurso.model.vo.TurmaGridDTO;
import com.metacurso.repository.turma.TurmaRepositoryQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TurmaRepository extends JpaRepository<Turmas, Integer>, TurmaRepositoryQuery {

    @Query("select new com.metacurso.model.vo.TurmaEditDTO(t.codigo, t.nome, c.codigo, t.datainicio, t.datatermino, t.status, t.nvagas, t.vagasdisponiveis, t.turno) from Turmas as t inner join t.curso as c where t.codigo = ?1")
    TurmaEditDTO turmaEditDTO(Integer turmaId);

    @Query("select new com.metacurso.model.vo.TurmaGridDTO(t.codigo, t.nome, t.datainicio, t.datatermino, t.status, t.curso.codigo, t.curso.nome, t.nvagas, t.vagasdisponiveis, t.turno) from Turmas as t")
    Page<TurmaGridDTO> TurmasGridDTO(Pageable pageable);

}
