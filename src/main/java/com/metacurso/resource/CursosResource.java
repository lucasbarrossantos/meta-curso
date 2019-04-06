package com.metacurso.resource;

import com.metacurso.event.RecursoCriadoEvent;
import com.metacurso.exceptionhandler.MetaCursoExceptionHandler;
import com.metacurso.model.Cursos;
import com.metacurso.model.vo.CursoDTO;
import com.metacurso.model.vo.DisciplinaComboDTO;
import com.metacurso.model.vo.MaterialComboDTO;
import com.metacurso.repository.CursoMaterialRepository;
import com.metacurso.repository.CursoRepository;
import com.metacurso.repository.CursosDisciplinaRepository;
import com.metacurso.service.CursoService;
import com.metacurso.service.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/cursos")
public class CursosResource {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private CursoService cursoService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private CursosDisciplinaRepository cursosDisciplinaRepository;

    @Autowired
    private CursoMaterialRepository cursoMaterialRepository;

    @GetMapping
    public List<Cursos> getAll() {
        return cursoRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Cursos> save(@Valid @RequestBody Cursos cursos,
                                       HttpServletResponse response) {
        Cursos cursoSalvo = cursoService.save(cursos);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, cursoSalvo.getCodigo()));
        return ResponseEntity.status(HttpStatus.CREATED).body(cursoSalvo);
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<Cursos> update(@PathVariable("codigo") Integer codigo,
                                         @Valid @RequestBody Cursos cursos) {
        return ResponseEntity.ok(cursoService.update(codigo, cursos));
    }

    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("codigo") Integer codigo) {
        cursoRepository.deleteById(codigo);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<CursoDTO> findByCodigo(@PathVariable("codigo") Integer codigo) {
        return cursoRepository.cursoEditDTO(codigo) != null ?
                ResponseEntity.ok().body(cursoRepository.cursoEditDTO(codigo)) :
                ResponseEntity.notFound().build();
    }

    @PostMapping("/{cursoId}/adicionar-disciplina/{disciplinaId}")
    public ResponseEntity<List<DisciplinaComboDTO>>
        adicionarDisciplina(@PathVariable("disciplinaId") Integer disciplinaId,
                            @PathVariable("cursoId") Integer cursoId) {

        cursoService.adicionarDisciplina(cursoId, disciplinaId);
        return cursosDisciplinaRepository.disciplinasComboDTO(cursoId).isEmpty() ?
                ResponseEntity.notFound().build() :
                ResponseEntity.ok().body(cursosDisciplinaRepository.disciplinasComboDTO(cursoId));

    }

    @PostMapping("/{cursoId}/adicionar-material/{materialId}")
    public ResponseEntity<List<MaterialComboDTO>>
    adicionarMaterial(@PathVariable("materialId") Integer materialId,
                        @PathVariable("cursoId") Integer cursoId) {

        cursoService.adicionarMaterial(cursoId, materialId);
        return cursoMaterialRepository.materiaisComboDTO(cursoId).isEmpty() ?
                ResponseEntity.notFound().build() :
                ResponseEntity.ok().body(cursoMaterialRepository.materiaisComboDTO(cursoId));

    }

    @GetMapping("/disciplinas-do-curso/{codigo}")
    public ResponseEntity<List<DisciplinaComboDTO>> findAllDisciplinasByCursoCodigo(@PathVariable("codigo") Integer codigo) {
        return cursosDisciplinaRepository.disciplinasComboDTO(codigo) != null ?
                ResponseEntity.ok().body(cursosDisciplinaRepository.disciplinasComboDTO(codigo)) :
                ResponseEntity.notFound().build();
    }

    @GetMapping("/materiais-do-curso/{codigo}")
    public ResponseEntity<List<MaterialComboDTO>> findAllMateriaisByCursoCodigo(@PathVariable("codigo") Integer codigo) {
        return cursoMaterialRepository.materiaisComboDTO(codigo) != null ?
                ResponseEntity.ok().body(cursoMaterialRepository.materiaisComboDTO(codigo)) :
                ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{cursoId}/remover-disciplina/{disciplinaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDisciplina(@PathVariable("cursoId") Integer cursoId,
                                 @PathVariable("disciplinaId") Integer disciplinaId) {
        cursoService.deleteDisciplina(cursoId, disciplinaId);
    }

    /**
     * Exceptions Handler
     */

    @ExceptionHandler({CategoriaInexistenteException.class})
    public ResponseEntity<Object> handleCategoriaInexistente(
            CategoriaInexistenteException ex) {

        String mensagemUsuario = messageSource.getMessage("categoria.inexistente",
                null, LocaleContextHolder.getLocale());
        String mensagemDesenvolvedor = ex.toString();
        List<MetaCursoExceptionHandler.Erro> erros = Arrays.asList(new MetaCursoExceptionHandler.Erro(mensagemUsuario, mensagemDesenvolvedor));
        return ResponseEntity.badRequest().body(erros);
    }

    @ExceptionHandler({DisciplinaInexistenteException.class})
    public ResponseEntity<Object> handleCategoriaInexistente(
            DisciplinaInexistenteException ex) {

        String mensagemUsuario = messageSource.getMessage("curso.disciplina",
                null, LocaleContextHolder.getLocale());
        String mensagemDesenvolvedor = ex.toString();
        List<MetaCursoExceptionHandler.Erro> erros = Arrays.asList(new MetaCursoExceptionHandler.Erro(mensagemUsuario, mensagemDesenvolvedor));
        return ResponseEntity.badRequest().body(erros);
    }

    @ExceptionHandler({DisciplinaJaAdicionadaException.class})
    public ResponseEntity<Object> handleCategoriaInexistente(
            DisciplinaJaAdicionadaException ex) {

        String mensagemUsuario = messageSource.getMessage("curso.disciplina-ja-adicionada",
                null, LocaleContextHolder.getLocale());
        String mensagemDesenvolvedor = ex.toString();
        List<MetaCursoExceptionHandler.Erro> erros = Arrays.asList(new MetaCursoExceptionHandler.Erro(mensagemUsuario, mensagemDesenvolvedor));
        return ResponseEntity.badRequest().body(erros);
    }

    @ExceptionHandler({MaterialJaAdicionadaException.class})
    public ResponseEntity<Object> handleMaterialJaAdicionadaException (
            MaterialJaAdicionadaException ex) {

        String mensagemUsuario = messageSource.getMessage("curso.material-ja-adicionado",
                null, LocaleContextHolder.getLocale());
        String mensagemDesenvolvedor = ex.toString();
        List<MetaCursoExceptionHandler.Erro> erros = Arrays.asList(new MetaCursoExceptionHandler.Erro(mensagemUsuario, mensagemDesenvolvedor));
        return ResponseEntity.badRequest().body(erros);
    }

    @ExceptionHandler({MaterialInexistenteException.class})
    public ResponseEntity<Object> handleMaterialInexistente(
            MaterialInexistenteException ex) {

        String mensagemUsuario = messageSource.getMessage("material.inexistente",
                null, LocaleContextHolder.getLocale());
        String mensagemDesenvolvedor = ex.toString();
        List<MetaCursoExceptionHandler.Erro> erros = Arrays.asList(new MetaCursoExceptionHandler.Erro(mensagemUsuario, mensagemDesenvolvedor));
        return ResponseEntity.badRequest().body(erros);
    }
}
