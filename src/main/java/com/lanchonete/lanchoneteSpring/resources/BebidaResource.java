package com.lanchonete.lanchoneteSpring.resources;

import com.lanchonete.lanchoneteSpring.entities.Bebida;
import com.lanchonete.lanchoneteSpring.services.BebidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/bebidas")
public class BebidaResource {

    @Autowired
    BebidaService service;

    @GetMapping
    public ResponseEntity<List<Bebida>> findAll() {
        List<Bebida> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Bebida> findById(@PathVariable Long id) {
        Bebida obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<Bebida> insert(@RequestBody Bebida obj) {
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Bebida> update(@PathVariable long id, @RequestBody Bebida obj) {
        obj = service.update(id, obj);
        return ResponseEntity.ok().body(obj);
    }


}
