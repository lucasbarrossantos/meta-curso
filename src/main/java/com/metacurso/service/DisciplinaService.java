package com.metacurso.service;

import com.metacurso.model.Disciplinas;
import com.metacurso.repository.DisciplinaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DisciplinaService {

    @Autowired
    private DisciplinaRepository disciplinaRepository;


    public Disciplinas update(Integer codigo, Disciplinas disciplinas) {
        Optional<Disciplinas> categoriaSalva = findByCodigo(codigo);

        BeanUtils.copyProperties(disciplinas, categoriaSalva.get(), "codigo");
        return disciplinaRepository.save(categoriaSalva.get());
    }

    private Optional<Disciplinas> findByCodigo(Integer codigo) {
        Optional<Disciplinas> categoriaSalva = disciplinaRepository.findById(codigo);

        if (!categoriaSalva.isPresent()) {
            throw new EmptyResultDataAccessException(1);
        }

        return categoriaSalva;
    }
}
