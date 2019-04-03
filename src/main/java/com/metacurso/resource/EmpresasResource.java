package com.metacurso.resource;

import com.metacurso.event.RecursoCriadoEvent;
import com.metacurso.model.Empresas;
import com.metacurso.repository.EmpresaRepository;
import com.metacurso.service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/empresas")
public class EmpresasResource {

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private EmpresaService empresaService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @GetMapping
    public List<Empresas> getAll() {
        return empresaRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Empresas> save(@Valid @RequestBody Empresas empresas,
                                          HttpServletResponse response) {
        Empresas empresaSalvo = empresaRepository.save(empresas);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, empresaSalvo.getCodigo()));
        return ResponseEntity.status(HttpStatus.CREATED).body(empresaSalvo);
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<Empresas> update(@PathVariable("codigo") Integer codigo,
                                             @Valid @RequestBody Empresas empresas) {
        return ResponseEntity.ok(empresaService.update(codigo, empresas));
    }

    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("codigo") Integer codigo) {
        empresaRepository.deleteById(codigo);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Empresas> findByCodigo(@PathVariable("codigo") Integer codigo) {
        return empresaRepository.findById(codigo).isPresent() ?
                ResponseEntity.ok().body(empresaRepository.findById(codigo).get()) :
                ResponseEntity.notFound().build();
    }
}
