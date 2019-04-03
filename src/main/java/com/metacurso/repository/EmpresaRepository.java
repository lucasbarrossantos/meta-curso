package com.metacurso.repository;

import com.metacurso.model.Empresas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresaRepository extends JpaRepository<Empresas, Integer> {

}
