package com.metacurso.resource;

import com.metacurso.event.RecursoCriadoEvent;
import com.metacurso.exceptionhandler.MetaCursoExceptionHandler;
import com.metacurso.model.Cursos;
import com.metacurso.repository.CursoRepository;
import com.metacurso.service.CursoService;
import com.metacurso.service.exception.CategoriaInexistenteOuInativaException;
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
    public ResponseEntity<Cursos> findByCodigo(@PathVariable("codigo") Integer codigo) {
        return cursoRepository.findById(codigo).isPresent() ?
                ResponseEntity.ok().body(cursoRepository.findById(codigo).get()) :
                ResponseEntity.notFound().build();
    }

    // ExceptionHandlers

    @ExceptionHandler({ CategoriaInexistenteOuInativaException.class })
    public ResponseEntity<Object> handleCategoriaInexistenteOuInativaException(
            CategoriaInexistenteOuInativaException ex) {

        String mensagemUsuario = messageSource.getMessage("categoria.inexistente",
                null, LocaleContextHolder.getLocale());
        String mensagemDesenvolvedor = ex.toString();
        List<MetaCursoExceptionHandler.Erro> erros = Arrays.asList(new MetaCursoExceptionHandler.Erro(mensagemUsuario, mensagemDesenvolvedor));
        return ResponseEntity.badRequest().body(erros);
    }
}
