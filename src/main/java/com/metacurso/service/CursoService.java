package com.metacurso.service;

import com.metacurso.model.Cursos;
import com.metacurso.repository.CategoriaRepository;
import com.metacurso.repository.CursoRepository;
import com.metacurso.service.exception.CategoriaInexistenteException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Cursos save(Cursos cursos) {
        validarCategoria(cursos);
        return cursoRepository.save(cursos);
    }

    public Cursos update(Integer codigo, Cursos cursos) {
        Optional<Cursos> cursoSalva = findByCodigo(codigo);

        BeanUtils.copyProperties(cursos, cursoSalva.get(), "codigo");
        return cursoRepository.save(cursoSalva.get());
    }

    private Optional<Cursos> findByCodigo(Integer codigo) {
        Optional<Cursos> cursoSalva = cursoRepository.findById(codigo);

        if (!cursoSalva.isPresent()) {
            throw new EmptyResultDataAccessException(1);
        }

        return cursoSalva;
    }

    private void validarCategoria(Cursos cursos) {
        categoriaRepository.findById(cursos.getCategoria().getCodigo())
                .orElseThrow(CategoriaInexistenteException::new);
    }
    
}
