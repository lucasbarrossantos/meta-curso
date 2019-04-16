package com.metacurso.repository.curso;

import com.metacurso.model.vo.CursoDTO;
import com.metacurso.repository.filter.CursoFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CursoRepositoryQuery {

    Page<CursoDTO> resumo(CursoFilter filter, Pageable pageable);

}
