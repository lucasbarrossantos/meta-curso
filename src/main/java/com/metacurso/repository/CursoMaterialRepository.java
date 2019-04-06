package com.metacurso.repository;

import com.metacurso.model.CursosMateriais;
import com.metacurso.model.vo.MaterialComboDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CursoMaterialRepository extends JpaRepository<CursosMateriais, Integer> {

    @Query("select cm.material.codigo as codigo, cm.material.nome as nome from CursosMateriais as cm where cm.curso.codigo = ?1")
    List<MaterialComboDTO> materiaisComboDTO(Integer cursoID);

    List<CursosMateriais> findByCursoCodigoAndMaterialCodigo(Integer cursoId, Integer materialId);
}
