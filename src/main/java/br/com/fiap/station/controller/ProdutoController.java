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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.station.model.Produto;
import br.com.fiap.station.repository.ProdutoRepository;

@RestController
@RequestMapping("/station/produto")
public class ProdutoController {
    Logger log = LoggerFactory.getLogger(getClass());
    
    @Autowired
    ProdutoRepository repo;

    @Autowired
    PagedResourcesAssembler<Produto> assembler;

    @GetMapping
    public PagedModel<EntityModel<Produto>> index(@PageableDefault(size = 5) Pageable pageable, @RequestParam(required = false) String query) {
        log.info("Bucando todos os produtos!");

        Page<Produto> page = query == null ? repo.findAll(pageable) : repo.findByNomeContaining(pageable, query);

        return assembler.toModel(page);
    }

    @GetMapping("{id}")
    public EntityModel<Produto> show(@PathVariable Long id) {
        log.info("Buscando o produto com id: " + id);

        Produto prodt = repo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado!"));

        return prodt.toModel();
    }

    @PostMapping
    public ResponseEntity<EntityModel<Produto>> create(@RequestBody Produto prodt, BindingResult result) {
        log.info("Cadastrando o produto: " + prodt);

        repo.save(prodt);

        return ResponseEntity.created(prodt.toModel().getRequiredLink("self").toUri()).body(prodt.toModel());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Produto> destroy(@PathVariable Long id) {
        log.info("Deletando o produto de id: " + id);

        Produto prodt = repo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado!"));

        repo.delete(prodt);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Produto> update(@PathVariable Long id, @RequestBody Produto prodt, BindingResult result) {
        log.info("Editndo o produto com id: " + id);

        var p = repo.findById(id);
        
        if (p.isEmpty()) return ResponseEntity.notFound().build();

        BeanUtils.copyProperties(prodt, p.get(), "id");

        repo.save(p.get());

        return ResponseEntity.ok(p.get());
    }
}
