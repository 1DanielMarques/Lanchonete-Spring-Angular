package com.lanchonete.lanchoneteSpring.resources;

import com.lanchonete.lanchoneteSpring.entities.Pedido;
import com.lanchonete.lanchoneteSpring.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api/pedidos")
public class PedidoResource {

    @Autowired
    PedidoService service;


    @GetMapping
    public ResponseEntity<List<Pedido>> findAll() {
        List<Pedido> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Pedido> findById(@PathVariable Long id) {
        Pedido obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<Pedido> insert(@RequestBody String json) {
        Pedido obj = service.parseInsert(json);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Pedido> update(@PathVariable long id, @RequestBody Pedido obj) {
        obj = service.update(id, obj);
        return ResponseEntity.ok().body(obj);
    }

}