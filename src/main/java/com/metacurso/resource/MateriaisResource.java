package com.metacurso.resource;

import com.metacurso.event.RecursoCriadoEvent;
import com.metacurso.model.Materiais;
import com.metacurso.repository.MaterialRepository;
import com.metacurso.service.MaterialService;
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
@RequestMapping("/materiais")
public class MateriaisResource {

    @Autowired
    private MaterialRepository materialRepository;

    @Autowired
    private MaterialService materialService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @GetMapping
    public Page<Materiais> getAll(Pageable pageable) {
        return materialRepository.findAll(pageable);
    }

    @PostMapping
    public ResponseEntity<Materiais> save(@Valid @RequestBody Materiais materiais,
                                          HttpServletResponse response) {
        Materiais materialSalvo = materialRepository.save(materiais);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, materialSalvo.getCodigo()));
        return ResponseEntity.status(HttpStatus.CREATED).body(materialSalvo);
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<Materiais> update(@PathVariable("codigo") Integer codigo,
                                             @Valid @RequestBody Materiais materiais) {
        return ResponseEntity.ok(materialService.update(codigo, materiais));
    }

    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("codigo") Integer codigo) {
        materialRepository.deleteById(codigo);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Materiais> findByCodigo(@PathVariable("codigo") Integer codigo) {
        return materialRepository.findById(codigo).isPresent() ?
                ResponseEntity.ok().body(materialRepository.findById(codigo).get()) :
                ResponseEntity.notFound().build();
    }

    @GetMapping(params = "nome") // Se tiver o parâmetro nome, então cai aqui!
    public Page<Materiais> findAllByNome(@RequestParam(required = false, defaultValue = "%") String nome,
                                         Pageable pageable) {
        return materialRepository.findAllByNomeContainingIgnoreCase(nome, pageable);
    }
}
