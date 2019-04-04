package com.metacurso.service;

import com.metacurso.model.Estoque;
import com.metacurso.repository.EstoqueRepository;
import com.metacurso.repository.MaterialRepository;
import com.metacurso.service.exception.MaterialInexistenteException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EstoqueService {

    @Autowired
    private EstoqueRepository estoqueRepository;

    @Autowired
    private MaterialRepository materialRepository;

    public Estoque save(Estoque estoque) {
        validarMaterial(estoque);
        return estoqueRepository.save(estoque);
    }

    public Estoque update(Integer codigo, Estoque materiais) {
        Optional<Estoque> estoqueSalva = findByCodigo(codigo);

        BeanUtils.copyProperties(materiais, estoqueSalva.get(), "codigo");
        return estoqueRepository.save(estoqueSalva.get());
    }

    private Optional<Estoque> findByCodigo(Integer codigo) {
        Optional<Estoque> estoqueSalva = estoqueRepository.findById(codigo);

        if (!estoqueSalva.isPresent()) {
            throw new EmptyResultDataAccessException(1);
        }

        return estoqueSalva;
    }

    private void validarMaterial(Estoque estoque) {
        materialRepository.findById(estoque.getMaterial().getCodigo())
                .orElseThrow(MaterialInexistenteException::new);
    }
    
}
