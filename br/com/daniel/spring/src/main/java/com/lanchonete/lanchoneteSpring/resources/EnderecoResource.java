package com.lanchonete.lanchoneteSpring.resources;

import com.lanchonete.lanchoneteSpring.entities.Endereco;
import com.lanchonete.lanchoneteSpring.services.EnderecoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api/enderecos")
public class EnderecoResource {
    private EnderecoService service;

    public EnderecoResource(EnderecoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Endereco>> findAll() {
        List<Endereco> enderecoList = service.findAll();
        return ResponseEntity.ok().body(enderecoList);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Endereco> findById(@PathVariable Long id) {
        Endereco endereco = service.findById(id);
        return ResponseEntity.ok().body(endereco);
    }

    @PostMapping
    public ResponseEntity<Endereco> insert(@RequestBody Endereco endereco) {
        endereco = service.insert(endereco);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(endereco.getId()).toUri();
        return ResponseEntity.created(uri).body(endereco);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Endereco> update(@PathVariable long id, @RequestBody Endereco endereco) {
        endereco = service.update(id, endereco);
        return ResponseEntity.ok().body(endereco);
    }

}