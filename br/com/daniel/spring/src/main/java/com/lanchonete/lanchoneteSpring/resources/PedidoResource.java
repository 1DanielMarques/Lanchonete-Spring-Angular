package com.lanchonete.lanchoneteSpring.resources;

import com.lanchonete.lanchoneteSpring.entities.Pedido;
import com.lanchonete.lanchoneteSpring.services.PedidoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api/pedidos")
public class PedidoResource {


    private PedidoService service;

    public PedidoResource(PedidoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Pedido>> findAll() {
        List<Pedido> list = service.findAll();
        service.updateQtd();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Pedido> findById(@PathVariable Long id) {
        Pedido obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping(value = "/{item}/{id}")
    public ResponseEntity<Boolean> findItem(@PathVariable("id") Long id, @PathVariable("item") String item) {
        boolean hasItem = service.findItem(id, item);
        return ResponseEntity.ok().body(hasItem);
    }

    @PostMapping
    public ResponseEntity<Pedido> insert(@RequestBody String json) {
        Pedido obj = service.insert(json);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{item}/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable("id") Long id, @PathVariable("item") String item) {
        service.deleteItem(id, item);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Pedido> update(@PathVariable long id, @RequestBody String json) {
        Pedido obj = service.update(id, json);
        return ResponseEntity.ok().body(obj);
    }

}