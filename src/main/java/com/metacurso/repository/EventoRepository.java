package com.metacurso.repository;

import com.metacurso.model.Eventos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventoRepository extends JpaRepository<Eventos, Integer> {

}
