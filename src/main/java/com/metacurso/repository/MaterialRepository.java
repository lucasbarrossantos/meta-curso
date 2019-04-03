package com.metacurso.repository;

import com.metacurso.model.Materiais;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaterialRepository extends JpaRepository<Materiais, Integer> {

}
