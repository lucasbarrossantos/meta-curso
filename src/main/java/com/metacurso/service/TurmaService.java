package com.metacurso.service;

import com.metacurso.model.CursosTurmas;
import com.metacurso.model.Turmas;
import com.metacurso.repository.CursoRepository;
import com.metacurso.repository.CursosTurmasRepository;
import com.metacurso.repository.TurmaRepository;
import com.metacurso.service.exception.CursoInexistenteException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TurmaService {

    @Autowired
    private TurmaRepository turmaRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private CursosTurmasRepository cursosTurmasRepository;

    public Turmas save(Turmas turmas) {
        validarCurso(turmas);

        turmas.setVagasdisponiveis(turmas.getNvagas());
        turmas = turmaRepository.save(turmas);
        cursoTurma(turmas);
        return turmas;
    }

    public Turmas update(Integer codigo, Turmas turmas) {
        Optional<Turmas> turmaSalva = findByCodigo(codigo);

        BeanUtils.copyProperties(turmas, turmaSalva.get(), "codigo");
        return turmaRepository.save(turmaSalva.get());
    }

    private Optional<Turmas> findByCodigo(Integer codigo) {
        Optional<Turmas> turmaSalva = turmaRepository.findById(codigo);

        if (!turmaSalva.isPresent()) {
            throw new EmptyResultDataAccessException(1);
        }

        return turmaSalva;
    }

    private void validarCurso(Turmas turmas) {
        cursoRepository.findById(turmas.getCurso().getCodigo())
                .orElseThrow(CursoInexistenteException::new);
    }

    private void cursoTurma(Turmas turmas) {
        CursosTurmas cursosTurmas = new CursosTurmas();
        cursosTurmas.setTurma(turmas);
        cursosTurmas.setCurso(turmas.getCurso());
        cursosTurmasRepository.save(cursosTurmas);
    }
}
