package com.metacurso.resource;

import com.metacurso.event.RecursoCriadoEvent;
import com.metacurso.model.Disciplinas;
import com.metacurso.repository.DisciplinaRepository;
import com.metacurso.service.DisciplinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/disciplinas")
public class DisciplinasResource {

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    @Autowired
    private DisciplinaService disciplinaService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @GetMapping
    public List<Disciplinas> getAll() {
        return disciplinaRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Disciplinas> save(@Valid @RequestBody Disciplinas disciplinas,
                                            HttpServletResponse response) {
        Disciplinas disciplinaSalva = disciplinaRepository.save(disciplinas);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, disciplinaSalva.getCodigo()));
        return ResponseEntity.status(HttpStatus.CREATED).body(disciplinaSalva);
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<Disciplinas> update(@PathVariable("codigo") Integer codigo,
                                             @Valid @RequestBody Disciplinas disciplinas) {
        return ResponseEntity.ok(disciplinaService.update(codigo, disciplinas));
    }

    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("codigo") Integer codigo) {
        disciplinaRepository.deleteById(codigo);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Disciplinas> findByCodigo(@PathVariable("codigo") Integer codigo) {
        return disciplinaRepository.findById(codigo).isPresent() ?
                ResponseEntity.ok().body(disciplinaRepository.findById(codigo).get()) :
                ResponseEntity.notFound().build();
    }
    
}
