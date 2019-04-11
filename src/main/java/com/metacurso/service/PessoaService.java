package com.metacurso.service;

import com.metacurso.model.Pessoas;
import com.metacurso.repository.PessoaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public Pessoas update(Integer codigo, Pessoas materiais) {
        Optional<Pessoas> pessoaSalva = findByCodigo(codigo);

        BeanUtils.copyProperties(materiais, pessoaSalva.get(), "codigo");
        return pessoaRepository.save(pessoaSalva.get());
    }

    private Optional<Pessoas> findByCodigo(Integer codigo) {
        Optional<Pessoas> pessoaSalva = pessoaRepository.findById(codigo);

        if (!pessoaSalva.isPresent()) {
            throw new EmptyResultDataAccessException(1);
        }

        return pessoaSalva;
    }

    public Pessoas save(Pessoas pessoas) {
        if (pessoas.getData_cadastro() == null) {
            pessoas.setData_cadastro(new Date());
        }

        return pessoaRepository.save(pessoas);
    }
}
