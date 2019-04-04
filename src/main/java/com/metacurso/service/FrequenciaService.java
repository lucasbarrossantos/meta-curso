package com.metacurso.service;

import com.metacurso.model.Frequencias;
import com.metacurso.repository.DisciplinaRepository;
import com.metacurso.repository.FrequenciaRepository;
import com.metacurso.repository.TurmaRepository;
import com.metacurso.service.exception.TurmaInexistenteException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class FrequenciaService {

    @Autowired
    private FrequenciaRepository frequenciaRepository;

    @Autowired
    private TurmaRepository turmaRepository;

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    public Frequencias save(Frequencias frequencias) {
        validarTurma(frequencias);
        validarDisciplina(frequencias);

        frequencias.setDatacadastro(new Date());
        return frequenciaRepository.save(frequencias);
    }

    public Frequencias update(Integer codigo, Frequencias frequencias) {
        Optional<Frequencias> frequenciaSalva = findByCodigo(codigo);

        BeanUtils.copyProperties(frequencias, frequenciaSalva.get(), "codigo");
        return frequenciaRepository.save(frequenciaSalva.get());
    }

    private Optional<Frequencias> findByCodigo(Integer codigo) {
        Optional<Frequencias> frequenciaSalva = frequenciaRepository.findById(codigo);

        if (!frequenciaSalva.isPresent()) {
            throw new EmptyResultDataAccessException(1);
        }

        return frequenciaSalva;
    }

    private void validarTurma(Frequencias frequencias) {
        turmaRepository.findById(frequencias.getTurma().getCodigo())
                .orElseThrow(TurmaInexistenteException::new);
    }

    private void validarDisciplina(Frequencias frequencias) {
        disciplinaRepository.findById(frequencias.getDisciplina().getCodigo())
                .orElseThrow(TurmaInexistenteException::new);
    }
    
}
