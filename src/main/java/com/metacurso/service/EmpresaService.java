package com.metacurso.service;

import com.metacurso.model.Empresas;
import com.metacurso.repository.EmpresaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmpresaService {
    
    @Autowired
    private EmpresaRepository empresaRepository;

    public Empresas update(Integer codigo, Empresas turmas) {
        Optional<Empresas> empresaSalva = findByCodigo(codigo);

        BeanUtils.copyProperties(turmas, empresaSalva.get(), "codigo");
        return empresaRepository.save(empresaSalva.get());
    }

    private Optional<Empresas> findByCodigo(Integer codigo) {
        Optional<Empresas> turmaSalva = empresaRepository.findById(codigo);

        if (!turmaSalva.isPresent()) {
            throw new EmptyResultDataAccessException(1);
        }

        return turmaSalva;
    }
}
