package com.metacurso.service;

import com.metacurso.model.Cursos;
import com.metacurso.model.CursosDisciplinas;
import com.metacurso.model.CursosMateriais;
import com.metacurso.model.Materiais;
import com.metacurso.repository.*;
import com.metacurso.service.exception.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    @Autowired
    private CursosDisciplinaRepository cursosDisciplinaRepository;

    @Autowired
    private CursoMaterialRepository cursoMaterialRepository;

    @Autowired
    private MaterialRepository materialRepository;

    public Cursos save(Cursos cursos) {
        validarCategoria(cursos);
        return cursoRepository.save(cursos);
    }

    public Cursos update(Integer codigo, Cursos cursos) {
        Optional<Cursos> cursoSalva = findByCodigo(codigo);

        BeanUtils.copyProperties(cursos, cursoSalva.get(), "codigo");
        return cursoRepository.save(cursoSalva.get());
    }

    public void deleteDisciplina(Integer cursoId, Integer disciplinaId) {
        validarDisciplina(disciplinaId);
        cursosDisciplinaRepository.delete(cursosDisciplinaRepository
                .findByCursoCodigoAndDisciplinaCodigo(cursoId, disciplinaId));
    }

    public void deleteMaterial(Integer cursoId, Integer materialId) {
        validarMaterial(materialId);
        Materiais materiais = materialRepository.findById(materialId).get();
        atualizarTotalMaterial(cursoId, materiais.getValor(), true);
        cursoMaterialRepository.delete(cursoMaterialRepository
                .findByCursoCodigoAndMaterialCodigo(cursoId, materialId));
    }

    public void adicionarDisciplina(Integer cursoId, Integer disciplinaId) {
        validarDisciplina(disciplinaId);
        verificarDuplicidadeDeDisciplina(cursoId, disciplinaId);
        CursosDisciplinas cursosDisciplinas = new CursosDisciplinas();
        cursosDisciplinas.setCurso(cursoRepository.findById(cursoId).get());
        cursosDisciplinas.setDisciplina(disciplinaRepository.findById(disciplinaId).get());
        cursosDisciplinaRepository.save(cursosDisciplinas);
    }

    public void adicionarMaterial(Integer cursoId, Integer materialId) {
        validarMaterial(materialId);
        verificarDuplicidadeDeMaterial(cursoId, materialId);
        CursosMateriais cursosMateriais = new CursosMateriais();
        cursosMateriais.setCurso(cursoRepository.findById(cursoId).get());
        cursosMateriais.setMaterial(materialRepository.findById(materialId).get());
        cursosMateriais = cursoMaterialRepository.save(cursosMateriais);
        atualizarTotalMaterial(cursoId, cursosMateriais.getMaterial().getValor(), false);
    }

    private void atualizarTotalMaterial(Integer cursoId, Double valor, Boolean removendo) {
        Optional<Cursos> curso = cursoRepository.findById(cursoId);
        if (curso.isPresent()) {
            if (curso.get().getTotal_material() == null) {
                curso.get().setTotal_material(0.0);
            }

            if (!removendo) {
                curso.get().setTotal_material(curso.get().getTotal_material() + valor);
                cursoRepository.save(curso.get());
            } else {
                curso.get().setTotal_material(curso.get().getTotal_material() - valor);
                cursoRepository.save(curso.get());
            }
        }
    }

    private void verificarDuplicidadeDeDisciplina(Integer cursoId, Integer disciplinaId) {
        List<CursosDisciplinas> disciplinas = cursosDisciplinaRepository
                .findAllByCursoCodigoAndDisciplinaCodigo(cursoId, disciplinaId);

        if (!disciplinas.isEmpty()) {
            throw new DisciplinaJaAdicionadaException();
        }
    }

    private void verificarDuplicidadeDeMaterial(Integer cursoId, Integer materialId) {
        List<CursosMateriais> materiais = cursoMaterialRepository
                .findAllByCursoCodigoAndMaterialCodigo(cursoId, materialId);

        if (!materiais.isEmpty()) {
            throw new MaterialJaAdicionadaException();
        }
    }

    private Optional<Cursos> findByCodigo(Integer codigo) {
        Optional<Cursos> cursoSalva = cursoRepository.findById(codigo);

        if (!cursoSalva.isPresent()) {
            throw new EmptyResultDataAccessException(1);
        }

        return cursoSalva;
    }

    private void validarCategoria(Cursos cursos) {
        if (cursos.getCategoria() != null) {
            categoriaRepository.findById(cursos.getCategoria().getCodigo())
                    .orElseThrow(CategoriaInexistenteException::new);
        }
    }

    private void validarDisciplina(Integer disciplinaId) {
        if (disciplinaId != null) {
            disciplinaRepository.findById(disciplinaId)
                    .orElseThrow(DisciplinaInexistenteException::new);
        }
    }

    private void validarMaterial(Integer materialId) {
        if (materialId != null) {
            materialRepository.findById(materialId)
                    .orElseThrow(MaterialInexistenteException::new);
        }
    }

}
