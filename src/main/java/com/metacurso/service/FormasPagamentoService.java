package com.metacurso.service;

import com.metacurso.model.FormasPagamento;
import com.metacurso.repository.FormasPagamentoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FormasPagamentoService {

    @Autowired
    private FormasPagamentoRepository formasPagamentoRepository;

    public FormasPagamento update(Integer codigo, FormasPagamento materiais) {
        Optional<FormasPagamento> formasPagamentoSalva = findByCodigo(codigo);

        BeanUtils.copyProperties(materiais, formasPagamentoSalva.get(), "codigo");
        return formasPagamentoRepository.save(formasPagamentoSalva.get());
    }

    private Optional<FormasPagamento> findByCodigo(Integer codigo) {
        Optional<FormasPagamento> formasPagamentoSalva = formasPagamentoRepository.findById(codigo);

        if (!formasPagamentoSalva.isPresent()) {
            throw new EmptyResultDataAccessException(1);
        }

        return formasPagamentoSalva;
    }
    
}
