package br.com.fiap.station.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.station.model.Usuario;
import br.com.fiap.station.repository.UsuarioRepository;

@RestController
@RequestMapping("/station/usuario")
public class UsuarioController {
    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    UsuarioRepository repo;

    @Autowired
    PagedResourcesAssembler<Usuario> assembler;

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
}
