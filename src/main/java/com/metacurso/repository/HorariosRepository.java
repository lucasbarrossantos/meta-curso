package com.metacurso.repository;

import com.metacurso.model.Horarios;
import com.metacurso.model.vo.HorarioDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface HorariosRepository extends JpaRepository<Horarios, Integer> {

    @Query("select new com.metacurso.model.vo.HorarioDTO(h.codigo, h.inicio, h.fim, h.dia, h.dataAula, p.nome, p.codigo, d.nome, d.codigo) from Horarios as h inner join h.professor as p inner join h.disciplina as d where h.turma.codigo = ?1")
    List<HorarioDTO> horariosDTO(Integer turmaId);

    @Query("select h from Horarios as h where h.dataAula = ?1 and h.turma.codigo = ?2 and h.dia = ?3 and (?4 < h.inicio and ?5 between ?4 and ?5)")
    List<Horarios> turmaComChoqueDeHorario(Date dataAula, Integer codigoTurma, Integer dia, String inicio, String fim);
}
