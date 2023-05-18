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
import org.springframework.security.crypto.password.PasswordEncoder;
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

import br.com.fiap.station.model.Usuario;
import br.com.fiap.station.repository.UsuarioRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/station/usuario")
public class UsuarioController {
    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    UsuarioRepository repo;

    @Autowired
    PagedResourcesAssembler<Usuario> assembler;

    @Autowired
    PasswordEncoder encoder;

    @GetMapping
    public PagedModel<EntityModel<Usuario>> index(@PageableDefault(size = 5) Pageable pageable, @RequestParam(required = false) String query) {
        log.info("Buscando todos os usuários!");

        Page<Usuario> page = query == null ? repo.findAll(pageable) : repo.findByEmail(pageable, query);

        return assembler.toModel(page);
    }

    @GetMapping("{id}")
    public EntityModel<Usuario> show(@PathVariable Long id) {
        log.info("Buscando o usuário de id: " + id);

        Usuario usuario = repo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado!"));

        return usuario.toModel();
    }

    @PostMapping
    public ResponseEntity<EntityModel<Usuario>> create(@RequestBody @Valid Usuario usuario, BindingResult result) {
        log.info("Cadastrando o usuário: " + usuario);

        usuario.setSenha(encoder.encode(usuario.getSenha()));

        repo.save(usuario);

        return ResponseEntity.created(usuario.toModel().getRequiredLink("self").toUri()).body(usuario.toModel());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Usuario> destroy(@PathVariable Long id) {
        log.info("Deletando o usuário de id: " + id);

        Usuario usuario = repo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado!"));

        repo.delete(usuario);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Usuario> update(@PathVariable Long id, @RequestBody @Valid Usuario usuario, BindingResult result) {
        log.info("Editando usuário com id: " + id);

        usuario.setSenha(encoder.encode(usuario.getSenha()));
        
        var u = repo.findById(id);

        if (u.isEmpty()) return ResponseEntity.notFound().build();

        BeanUtils.copyProperties(usuario, u.get(), "id");

        repo.save(u.get());

        return ResponseEntity.ok(u.get());
    }
}
