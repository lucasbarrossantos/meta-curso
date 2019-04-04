package com.metacurso.service;

import com.metacurso.model.Eventos;
import com.metacurso.repository.EventoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    public Eventos update(Integer codigo, Eventos materiais) {
        Optional<Eventos> eventoSalva = findByCodigo(codigo);

        BeanUtils.copyProperties(materiais, eventoSalva.get(), "codigo");
        return eventoRepository.save(eventoSalva.get());
    }

    private Optional<Eventos> findByCodigo(Integer codigo) {
        Optional<Eventos> eventoSalva = eventoRepository.findById(codigo);

        if (!eventoSalva.isPresent()) {
            throw new EmptyResultDataAccessException(1);
        }

        return eventoSalva;
    }
    
}
