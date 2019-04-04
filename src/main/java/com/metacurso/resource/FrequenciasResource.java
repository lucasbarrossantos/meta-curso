package com.metacurso.resource;

import com.metacurso.event.RecursoCriadoEvent;
import com.metacurso.model.Frequencias;
import com.metacurso.repository.FrequenciaRepository;
import com.metacurso.service.FrequenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/frequencias")
public class FrequenciasResource {

    @Autowired
    private FrequenciaRepository frequenciaRepository;

    @Autowired
    private FrequenciaService frequenciaService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @GetMapping
    public List<Frequencias> getAll() {
        return frequenciaRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Frequencias> save(@Valid @RequestBody Frequencias frequencias,
                                          HttpServletResponse response) {
        Frequencias frequenciaSalvo = frequenciaService.save(frequencias);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, frequenciaSalvo.getCodigo()));
        return ResponseEntity.status(HttpStatus.CREATED).body(frequenciaSalvo);
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<Frequencias> update(@PathVariable("codigo") Integer codigo,
                                             @Valid @RequestBody Frequencias frequencias) {
        return ResponseEntity.ok(frequenciaService.update(codigo, frequencias));
    }

    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("codigo") Integer codigo) {
        frequenciaRepository.deleteById(codigo);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Frequencias> findByCodigo(@PathVariable("codigo") Integer codigo) {
        return frequenciaRepository.findById(codigo).isPresent() ?
                ResponseEntity.ok().body(frequenciaRepository.findById(codigo).get()) :
                ResponseEntity.notFound().build();
    }
}
