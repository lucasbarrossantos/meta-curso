package com.metacurso.service;

import com.metacurso.model.Cursos;
import com.metacurso.model.CursosInteressados;
import com.metacurso.model.Interessados;
import com.metacurso.repository.CursoRepository;
import com.metacurso.repository.CursosInteressadosRepository;
import com.metacurso.repository.InteressadoRepository;
import com.metacurso.service.exception.CursoInexistenteException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class InteressadoService {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private InteressadoRepository interessadoRepository;

    @Autowired
    private CursosInteressadosRepository cursosInteressadosRepository;

    public Interessados save(Interessados interessados) {
        validarCurso(interessados);
        //TODO: validar Interessado também

        interessados.setDatacadastro(new Date());
        interessados = interessadoRepository.save(interessados);
        cursosInteressados(interessados);
        return interessados;
    }

    public Interessados update(Integer codigo, Interessados interessados) {
        Optional<Interessados> interessadoSalva = findByCodigo(codigo);

        BeanUtils.copyProperties(interessados, interessadoSalva.get(), "codigo");
        return interessadoRepository.save(interessadoSalva.get());
    }

    private Optional<Interessados> findByCodigo(Integer codigo) {
        Optional<Interessados> interessadoSalvo = interessadoRepository.findById(codigo);

        if (!interessadoSalvo.isPresent()) {
            throw new EmptyResultDataAccessException(1);
        }

        return interessadoSalvo;
    }

    private void validarCurso(Interessados interessados) {
        cursoRepository.findById(interessados.getCurso().getCodigo())
                .orElseThrow(CursoInexistenteException::new);
    }

    private void cursosInteressados(Interessados interessados) {
        CursosInteressados cursosInteressados = new CursosInteressados();
        cursosInteressados.setInteressado(interessados);
        cursosInteressados.setCurso(interessados.getCurso());
        cursosInteressadosRepository.save(cursosInteressados);
        // TODO: Chamar também o InteressadosEventos
    }

}
