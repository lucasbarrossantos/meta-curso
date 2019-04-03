package com.metacurso.resource;

import com.metacurso.event.RecursoCriadoEvent;
import com.metacurso.model.Pessoas;
import com.metacurso.repository.PessoaRepository;
import com.metacurso.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/pessoas")
public class PessoasResource {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @GetMapping
    public List<Pessoas> getAll() {
        return pessoaRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Pessoas> save(@Valid @RequestBody Pessoas pessoas,
                                          HttpServletResponse response) {
        Pessoas pessoaSalvo = pessoaRepository.save(pessoas);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, pessoaSalvo.getCodigo()));
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaSalvo);
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<Pessoas> update(@PathVariable("codigo") Integer codigo,
                                             @Valid @RequestBody Pessoas pessoas) {
        return ResponseEntity.ok(pessoaService.update(codigo, pessoas));
    }

    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("codigo") Integer codigo) {
        pessoaRepository.deleteById(codigo);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Pessoas> findByCodigo(@PathVariable("codigo") Integer codigo) {
        return pessoaRepository.findById(codigo).isPresent() ?
                ResponseEntity.ok().body(pessoaRepository.findById(codigo).get()) :
                ResponseEntity.notFound().build();
    }
}
