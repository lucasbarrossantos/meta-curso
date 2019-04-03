package com.metacurso.service;

import com.metacurso.model.Categorias;
import com.metacurso.repository.CategoriaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categorias update(Integer codigo, Categorias categorias) {
        Optional<Categorias> categoriaSalva = getCategoriaPorCodigo(codigo);

        BeanUtils.copyProperties(categorias, categoriaSalva.get(), "codigo");
        return categoriaRepository.save(categoriaSalva.get());
    }

    private Optional<Categorias> getCategoriaPorCodigo(Integer codigo) {
        Optional<Categorias> categoriaSalva = categoriaRepository.findById(codigo);

        if (!categoriaSalva.isPresent()) {
            throw new EmptyResultDataAccessException(1);
        }

        return categoriaSalva;
    }

}
