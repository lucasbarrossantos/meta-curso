package com.metacurso.resource;

import com.metacurso.event.RecursoCriadoEvent;
import com.metacurso.model.Categorias;
import com.metacurso.repository.CategoriaRepository;
import com.metacurso.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @GetMapping
    public List<Categorias> getAll() {
        return categoriaRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Categorias> save(@Valid @RequestBody Categorias categorias,
                                             HttpServletResponse response) {
        Categorias categoriaSalva = categoriaRepository.save(categorias);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, categoriaSalva.getCodigo()));
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaSalva);
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<Categorias> update(@PathVariable("codigo") Integer codigo,
                                                @Valid @RequestBody Categorias categorias) {
        return ResponseEntity.ok(categoriaService.update(codigo, categorias));
    }

    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable("codigo") Integer codigo) {
        categoriaRepository.deleteById(codigo);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Categorias> findByCodigo(@PathVariable("codigo") Integer codigo) {
        return categoriaRepository.findById(codigo).isPresent() ?
                ResponseEntity.ok().body(categoriaRepository.findById(codigo).get()) :
                ResponseEntity.notFound().build();
    }

    @GetMapping(params = "nome") // Se tiver o parâmetro nome, então cai aqui!
    public Page<Categorias> findAllByNome(@RequestParam(required = false, defaultValue = "%") String nome,
                                      Pageable pageable) {
        return categoriaRepository.findAllByNomeContainingIgnoreCase(nome, pageable);
    }

}
