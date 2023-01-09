package com.lanchonete.lanchoneteSpring.resources;

import com.lanchonete.lanchoneteSpring.entities.Lanche;
import com.lanchonete.lanchoneteSpring.services.LancheService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.net.URI;
import java.util.List;

@Validated
@RestController
@RequestMapping(value = "/api/lanches")
public class LancheResource {


    private LancheService service;

    public LancheResource(LancheService service) {
        this.service = service;

    }

    @GetMapping
    public ResponseEntity<List<Lanche>> findAll() {
        List<Lanche> lancheList = service.findAll();
        return ResponseEntity.ok().body(lancheList);
    }

    @GetMapping(value = "/pedido/{id}")
    public ResponseEntity<List<Lanche>> findAll(@PathVariable("id") Long id) {
        List<Lanche> lancheList = service.findLanchesPedido(id);
        return ResponseEntity.ok().body(lancheList);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Lanche> findById(@PathVariable @NotNull @Positive Long id) {
        Lanche lanche = service.findById(id);
        return ResponseEntity.ok().body(lanche);
    }

    @PostMapping
    public ResponseEntity<Lanche> insert(@RequestBody @Valid Lanche lanche) {
        lanche = service.insert(lanche);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(lanche.getId()).toUri();
        return ResponseEntity.created(uri).body(lanche);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable @NotNull @Positive Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Lanche> update(@PathVariable @NotNull @Positive Long id, @RequestBody @Valid Lanche lanche) {
        lanche = service.update(id, lanche);
        return ResponseEntity.ok().body(lanche);
    }

}