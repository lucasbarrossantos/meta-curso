package com.metacurso.resource;

import com.metacurso.event.RecursoCriadoEvent;
import com.metacurso.exceptionhandler.MetaCursoExceptionHandler;
import com.metacurso.model.Interessados;
import com.metacurso.repository.InteressadoRepository;
import com.metacurso.service.InteressadoService;
import com.metacurso.service.exception.CursoInexistenteException;
import com.metacurso.service.exception.EventoInexistenteException;
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
@RequestMapping("/interessados")
public class InteressadosResource {

    @Autowired
    private InteressadoRepository interessadoRepository;

    @Autowired
    private InteressadoService interessadoService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private MessageSource messageSource;

    @GetMapping
    public List<Interessados> getAll() {
        return interessadoRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Interessados> save(@Valid @RequestBody Interessados interessados,
                                          HttpServletResponse response) {
        Interessados interessadoSalvo = interessadoService.save(interessados);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, interessadoSalvo.getCodigo()));
        return ResponseEntity.status(HttpStatus.CREATED).body(interessadoSalvo);
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<Interessados> update(@PathVariable("codigo") Integer codigo,
                                             @Valid @RequestBody Interessados interessados) {
        return ResponseEntity.ok(interessadoService.update(codigo, interessados));
    }

    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("codigo") Integer codigo) {
        interessadoRepository.deleteById(codigo);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Interessados> findByCodigo(@PathVariable("codigo") Integer codigo) {
        return interessadoRepository.findById(codigo).isPresent() ?
                ResponseEntity.ok().body(interessadoRepository.findById(codigo).get()) :
                ResponseEntity.notFound().build();
    }

    // ExceptionHandlers

    @ExceptionHandler({CursoInexistenteException.class })
    public ResponseEntity<Object> handleCursoInexistenteOuInativaException(
            CursoInexistenteException ex) {

        String mensagemUsuario = messageSource.getMessage("curso.inexistente",
                null, LocaleContextHolder.getLocale());
        String mensagemDesenvolvedor = ex.toString();
        List<MetaCursoExceptionHandler.Erro> erros =
                Arrays.asList(new MetaCursoExceptionHandler.Erro(mensagemUsuario, mensagemDesenvolvedor));
        return ResponseEntity.badRequest().body(erros);
    }

    @ExceptionHandler({ EventoInexistenteException.class })
    public ResponseEntity<Object> handleEventoInexistenteOuInativaException(
            EventoInexistenteException ex) {

        String mensagemUsuario = messageSource.getMessage("evento.inexistente",
                null, LocaleContextHolder.getLocale());
        String mensagemDesenvolvedor = ex.toString();
        List<MetaCursoExceptionHandler.Erro> erros =
                Arrays.asList(new MetaCursoExceptionHandler.Erro(mensagemUsuario, mensagemDesenvolvedor));
        return ResponseEntity.badRequest().body(erros);
    }
}
