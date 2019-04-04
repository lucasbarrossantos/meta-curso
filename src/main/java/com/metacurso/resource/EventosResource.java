package com.metacurso.resource;

import com.metacurso.event.RecursoCriadoEvent;
import com.metacurso.model.Eventos;
import com.metacurso.repository.EventoRepository;
import com.metacurso.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/eventos")
public class EventosResource {

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private EventoService eventoService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @GetMapping
    public List<Eventos> getAll() {
        return eventoRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Eventos> save(@Valid @RequestBody Eventos eventos,
                                          HttpServletResponse response) {
        Eventos eventoSalvo = eventoRepository.save(eventos);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, eventoSalvo.getCodigo()));
        return ResponseEntity.status(HttpStatus.CREATED).body(eventoSalvo);
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<Eventos> update(@PathVariable("codigo") Integer codigo,
                                             @Valid @RequestBody Eventos eventos) {
        return ResponseEntity.ok(eventoService.update(codigo, eventos));
    }

    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("codigo") Integer codigo) {
        eventoRepository.deleteById(codigo);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Eventos> findByCodigo(@PathVariable("codigo") Integer codigo) {
        return eventoRepository.findById(codigo).isPresent() ?
                ResponseEntity.ok().body(eventoRepository.findById(codigo).get()) :
                ResponseEntity.notFound().build();
    }
}
