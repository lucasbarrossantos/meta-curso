package com.metacurso.service;

import com.metacurso.model.Cursos;
import com.metacurso.model.CursosInteressados;
import com.metacurso.model.Interessados;
import com.metacurso.model.InteressadosEventos;
import com.metacurso.repository.*;
import com.metacurso.service.exception.CursoInexistenteException;
import com.metacurso.service.exception.EventoInexistenteException;
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

    @Autowired
    private InteressadosEventosRepository interessadosEventosRepository;

    @Autowired
    private EventoRepository eventoRepository;

    public Interessados save(Interessados interessados) {
        validarCurso(interessados);
        validarEvento(interessados);

        interessados.setDatacadastro(new Date());
        interessados = interessadoRepository.save(interessados);
        cursosInteressados(interessados);
        interessadosEventos(interessados);
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

    private void validarEvento(Interessados interessados) {
        eventoRepository.findById(interessados.getEvento().getCodigo())
                .orElseThrow(EventoInexistenteException::new);
    }

    private void cursosInteressados(Interessados interessados) {
        CursosInteressados cursosInteressados = new CursosInteressados();
        cursosInteressados.setInteressado(interessados);
        cursosInteressados.setCurso(interessados.getCurso());
        cursosInteressadosRepository.save(cursosInteressados);
    }

    private void interessadosEventos(Interessados interessados) {
        InteressadosEventos interessadosEventos = new InteressadosEventos();
        interessadosEventos.setInteressados(interessados);
        interessadosEventos.setEventos(interessados.getEvento());
        interessadosEventosRepository.save(interessadosEventos);
    }

}
