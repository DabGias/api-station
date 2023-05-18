package br.com.fiap.station.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.station.model.Categoria;
import br.com.fiap.station.repository.CategoriaRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/station/categoria")
public class CategoriaController {
    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    CategoriaRepository repo;

    @Autowired
    PagedResourcesAssembler<Categoria> assembler;

    @GetMapping
    public PagedModel<EntityModel<Categoria>> index(@PageableDefault(size = 5) Pageable pageable) {
        log.info("Buscando todas as categorias!");

        Page<Categoria> page = repo.findAll(pageable);

        return assembler.toModel(page);
    }

    @GetMapping("{id}")
    public EntityModel<Categoria> show(@PathVariable Long id) {
        log.info("Buscando a categoria com id: " + id);

        Categoria categoria = repo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria não encontrada!"));

        return categoria.toModel();
    }

    @PostMapping
    public ResponseEntity<EntityModel<Categoria>> create(@RequestBody @Valid Categoria categoria, BindingResult result) {
        log.info("Cadastrando a categoria: " + categoria);

        repo.save(categoria);

        return ResponseEntity.created(categoria.toModel().getRequiredLink("self").toUri()).body(categoria.toModel());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Categoria> destroy(@PathVariable Long id) {
        log.info("Deletando a categoria de id: " + id);

        Categoria categoria = repo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria não encontrada!"));

        repo.delete(categoria);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Categoria> update(@PathVariable Long id, @RequestBody @Valid Categoria categoria, BindingResult result) {
        log.info("Editando categoria com id: " + id);

        var c = repo.findById(id);

        if (c.isEmpty()) return ResponseEntity.notFound().build();

        BeanUtils.copyProperties(categoria, c.get(), "id");

        repo.save(c.get());

        return ResponseEntity.ok(c.get());
    }
}
