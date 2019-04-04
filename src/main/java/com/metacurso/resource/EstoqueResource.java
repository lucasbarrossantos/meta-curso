package com.metacurso.resource;

import com.metacurso.event.RecursoCriadoEvent;
import com.metacurso.exceptionhandler.MetaCursoExceptionHandler;
import com.metacurso.model.Estoque;
import com.metacurso.repository.EstoqueRepository;
import com.metacurso.service.EstoqueService;
import com.metacurso.service.exception.MaterialInexistenteException;
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
@RequestMapping("/estoques")
public class EstoqueResource {

    @Autowired
    private EstoqueRepository estoqueRepository;

    @Autowired
    private EstoqueService estoqueService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private MessageSource messageSource;

    @GetMapping
    public List<Estoque> getAll() {
        return estoqueRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Estoque> save(@Valid @RequestBody Estoque estoque,
                                          HttpServletResponse response) {
        Estoque estoqueSalvo = estoqueService.save(estoque);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, estoqueSalvo.getCodigo()));
        return ResponseEntity.status(HttpStatus.CREATED).body(estoqueSalvo);
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<Estoque> update(@PathVariable("codigo") Integer codigo,
                                             @Valid @RequestBody Estoque estoque) {
        return ResponseEntity.ok(estoqueService.update(codigo, estoque));
    }

    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("codigo") Integer codigo) {
        estoqueRepository.deleteById(codigo);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Estoque> findByCodigo(@PathVariable("codigo") Integer codigo) {
        return estoqueRepository.findById(codigo).isPresent() ?
                ResponseEntity.ok().body(estoqueRepository.findById(codigo).get()) :
                ResponseEntity.notFound().build();
    }

    // ExceptionHandlers

    @ExceptionHandler({ MaterialInexistenteException.class })
    public ResponseEntity<Object> handleMaterialInexistente (
            MaterialInexistenteException ex) {

        String mensagemUsuario = messageSource.getMessage("material.inexistente",
                null, LocaleContextHolder.getLocale());
        String mensagemDesenvolvedor = ex.toString();
        List<MetaCursoExceptionHandler.Erro> erros = Arrays.asList(new MetaCursoExceptionHandler.Erro(mensagemUsuario, mensagemDesenvolvedor));
        return ResponseEntity.badRequest().body(erros);
    }
}
