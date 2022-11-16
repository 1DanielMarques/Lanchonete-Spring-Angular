package com.lanchonete.lanchoneteSpring.resources;

import com.lanchonete.lanchoneteSpring.entities.Lanche;
import com.lanchonete.lanchoneteSpring.services.LancheService;
import com.lanchonete.lanchoneteSpring.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/lanches")
public class LancheResource {

    @Autowired
    LancheService service;

    @Autowired
    PedidoService pedidoService;

    @GetMapping
    public ResponseEntity<List<Lanche>> findAll() {
        List<Lanche> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Lanche> findById(@PathVariable Long id) {
        Lanche obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<Lanche> insert(@RequestBody Lanche obj) {
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @PostMapping(value = "/order/{id}")
    public ResponseEntity<Lanche> insert(@RequestBody Lanche obj, @PathVariable Long id) {
        obj.setPedidoLanche(pedidoService.findById(id));
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
    public ResponseEntity<Lanche> update(@PathVariable long id, @RequestBody Lanche obj) {
        obj = service.update(id, obj);
        return ResponseEntity.ok().body(obj);
    }


}
