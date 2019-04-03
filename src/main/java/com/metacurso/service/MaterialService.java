package com.metacurso.service;

import com.metacurso.model.Materiais;
import com.metacurso.repository.MaterialRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MaterialService {

    @Autowired
    private MaterialRepository materialRepository;

    public Materiais update(Integer codigo, Materiais materiais) {
        Optional<Materiais> materialSalva = findByCodigo(codigo);

        BeanUtils.copyProperties(materiais, materialSalva.get(), "codigo");
        return materialRepository.save(materialSalva.get());
    }

    private Optional<Materiais> findByCodigo(Integer codigo) {
        Optional<Materiais> materialSalva = materialRepository.findById(codigo);

        if (!materialSalva.isPresent()) {
            throw new EmptyResultDataAccessException(1);
        }

        return materialSalva;
    }

}
