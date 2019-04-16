package com.metacurso.resource;

import com.metacurso.event.RecursoCriadoEvent;
import com.metacurso.exceptionhandler.MetaCursoExceptionHandler;
import com.metacurso.model.Horarios;
import com.metacurso.model.Turmas;
import com.metacurso.model.vo.HorarioDTO;
import com.metacurso.model.vo.TurmaEditDTO;
import com.metacurso.model.vo.TurmaGridDTO;
import com.metacurso.repository.HorariosRepository;
import com.metacurso.repository.TurmaRepository;
import com.metacurso.repository.filter.TurmaFilter;
import com.metacurso.service.TurmaService;
import com.metacurso.service.exception.ChoqueDeHorarioException;
import com.metacurso.service.exception.CursoInexistenteException;
import com.metacurso.service.exception.HorarioDataFimMaiorQueDataInicioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/turmas")
public class TurmasResource {

    @Autowired
    private TurmaRepository turmaRepository;

    @Autowired
    private TurmaService turmaService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private HorariosRepository horariosRepository;

    @GetMapping
    public List<Turmas> getAll() {
        return turmaRepository.findAll();
    }

    @GetMapping(params = "resumo")
    public Page<TurmaGridDTO> getAllTurmasResumidas(TurmaFilter filter, Pageable pageable) {
        return turmaRepository.resumo(filter, pageable);
    }

    @PostMapping
    public ResponseEntity<Turmas> save(@Valid @RequestBody Turmas turmas,
                                             HttpServletResponse response) {
        Turmas turmaSalva = turmaService.save(turmas);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, turmaSalva.getCodigo()));
        return ResponseEntity.status(HttpStatus.CREATED).body(turmaSalva);
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<Turmas> update(@PathVariable("codigo") Integer codigo,
                                                @Valid @RequestBody Turmas turmas) {
        return ResponseEntity.ok(turmaService.update(codigo, turmas));
    }

    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("codigo") Integer codigo) {
        turmaService.deleteByCodigo(codigo);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<TurmaEditDTO> findByCodigo(@PathVariable("codigo") Integer codigo) {
        return turmaRepository.findById(codigo).isPresent() ?
                ResponseEntity.ok().body(turmaRepository.turmaEditDTO(codigo)) :
                ResponseEntity.notFound().build();
    }

    @GetMapping("/horarios-da-turma/{codigo}")
    public ResponseEntity<List<HorarioDTO>> getHorariosDaTurma(@PathVariable("codigo") Integer codigo) {
        return !horariosRepository.horariosDTO(codigo).isEmpty() ?
                ResponseEntity.ok().body(horariosRepository.horariosDTO(codigo)) :
                ResponseEntity.ok().build();
    }

    @PostMapping("/adicionar-horario")
    public ResponseEntity<Void> adicionarHoratio(@Valid @RequestBody Horarios horario,
                                       HttpServletResponse response) {
        turmaService.adicionarHorario(horario);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, horario.getTurma().getCodigo()));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{turmaId}/remover-horario/{horarioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteHorario(@PathVariable("turmaId") Integer turmaId,
                               @PathVariable("horarioId") Integer horarioId) {
        turmaService.removerHorario(turmaId, horarioId);
    }

    // ExceptionHandlers

    @ExceptionHandler({CursoInexistenteException.class })
    public ResponseEntity<Object> handleCursoInexistenteException(
            CursoInexistenteException ex) {

        String mensagemUsuario = messageSource.getMessage("curso.inexistente",
                null, LocaleContextHolder.getLocale());
        String mensagemDesenvolvedor = ex.toString();
        List<MetaCursoExceptionHandler.Erro> erros = Arrays.asList(new MetaCursoExceptionHandler.Erro(mensagemUsuario, mensagemDesenvolvedor));
        return ResponseEntity.badRequest().body(erros);
    }

    @ExceptionHandler({ChoqueDeHorarioException.class})
    public ResponseEntity<Object> handleChoqueDeHorarioException(
            ChoqueDeHorarioException ex) {

        String mensagemUsuario = messageSource.getMessage("turma.choque-de-horario",
                null, LocaleContextHolder.getLocale());
        String mensagemDesenvolvedor = ex.toString();
        List<MetaCursoExceptionHandler.Erro> erros = Arrays.asList(new MetaCursoExceptionHandler.Erro(mensagemUsuario, mensagemDesenvolvedor));
        return ResponseEntity.badRequest().body(erros);
    }

    @ExceptionHandler({HorarioDataFimMaiorQueDataInicioException.class})
    public ResponseEntity<Object> handleHorarioDataFimMaiorQueDataInicioException(
            HorarioDataFimMaiorQueDataInicioException ex) {

        String mensagemUsuario = messageSource.getMessage("turma.horario.fim-maior-que-inicio",
                null, LocaleContextHolder.getLocale());
        String mensagemDesenvolvedor = ex.toString();
        List<MetaCursoExceptionHandler.Erro> erros = Arrays.asList(new MetaCursoExceptionHandler.Erro(mensagemUsuario, mensagemDesenvolvedor));
        return ResponseEntity.badRequest().body(erros);
    }

    @ExceptionHandler({ DateTimeParseException.class })
    public ResponseEntity<Object> handleDateTimeParseException(DateTimeParseException tx,
                                                               WebRequest request) {

        String mensagemUsuario = messageSource.getMessage("turma.horario.horario-invalido",null, LocaleContextHolder.getLocale());
        String mensagemDesenvolvedor = tx.toString();
        List<MetaCursoExceptionHandler.Erro> erros = Arrays.asList(new MetaCursoExceptionHandler.Erro(mensagemUsuario, mensagemDesenvolvedor));

        return ResponseEntity.badRequest().body(erros);
    }

}
