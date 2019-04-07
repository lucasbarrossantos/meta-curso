package com.metacurso.repository;

import com.metacurso.model.Pessoas;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoas, Integer> {

    Page<Pessoas> findAllByNomeContainingIgnoreCase(String nome, Pageable pageable);
}
