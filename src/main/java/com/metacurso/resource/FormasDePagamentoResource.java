package com.metacurso.resource;

import com.metacurso.event.RecursoCriadoEvent;
import com.metacurso.model.FormasPagamento;
import com.metacurso.repository.FormasPagamentoRepository;
import com.metacurso.service.FormasPagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/formas-de-pagamento")
public class FormasDePagamentoResource {

    @Autowired
    private FormasPagamentoRepository pessoaRepository;

    @Autowired
    private FormasPagamentoService pessoaService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @GetMapping
    public List<FormasPagamento> getAll() {
        return pessoaRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<FormasPagamento> save(@Valid @RequestBody FormasPagamento pessoas,
                                          HttpServletResponse response) {
        FormasPagamento pessoaSalvo = pessoaRepository.save(pessoas);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, pessoaSalvo.getCodigo()));
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaSalvo);
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<FormasPagamento> update(@PathVariable("codigo") Integer codigo,
                                             @Valid @RequestBody FormasPagamento pessoas) {
        return ResponseEntity.ok(pessoaService.update(codigo, pessoas));
    }

    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("codigo") Integer codigo) {
        pessoaRepository.deleteById(codigo);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<FormasPagamento> findByCodigo(@PathVariable("codigo") Integer codigo) {
        return pessoaRepository.findById(codigo).isPresent() ?
                ResponseEntity.ok().body(pessoaRepository.findById(codigo).get()) :
                ResponseEntity.notFound().build();
    }
}
