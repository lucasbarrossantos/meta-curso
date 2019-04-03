package com.metacurso.repository;

import com.metacurso.model.Pessoas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoas, Integer> {

}
