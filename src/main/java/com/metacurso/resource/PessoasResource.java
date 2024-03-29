package com.metacurso.resource;

import com.metacurso.event.RecursoCriadoEvent;
import com.metacurso.model.Pessoas;
import com.metacurso.repository.PessoaRepository;
import com.metacurso.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Date;
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
    public Page<Pessoas> getAll(Pageable pageable) {
        return pessoaRepository.findAll(pageable);
    }

    @PostMapping
    public ResponseEntity<Pessoas> save(@Valid @RequestBody Pessoas pessoas,
                                          HttpServletResponse response) {
        Pessoas pessoaSalvo = pessoaService.save(pessoas);
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

    @GetMapping(params = "nome") // Se tiver o parâmetro nome, então cai aqui!
    public Page<Pessoas> findAllByNome(@RequestParam(required = false, defaultValue = "%") String nome,
                                       Pageable pageable) {
        return pessoaRepository.findAllByNomeContainingIgnoreCase(nome, pageable);
    }
}
