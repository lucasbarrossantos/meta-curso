package com.metacurso.repository.turma;

import com.metacurso.model.vo.TurmaGridDTO;
import com.metacurso.repository.filter.TurmaFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TurmaRepositoryQuery {

    Page<TurmaGridDTO> resumo(TurmaFilter filter, Pageable pageable);

}
