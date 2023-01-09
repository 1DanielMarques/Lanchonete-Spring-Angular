package com.lanchonete.lanchoneteSpring.resources;

import com.lanchonete.lanchoneteSpring.entities.Pedido;
import com.lanchonete.lanchoneteSpring.services.PedidoService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.net.URI;
import java.util.List;

@Validated
@RestController
@RequestMapping(value = "/api/pedidos")
public class PedidoResource {

    private PedidoService service;

    public PedidoResource(PedidoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Pedido>> findAll() {
        List<Pedido> pedidoList = service.findAll();
        service.updateQtd();
        return ResponseEntity.ok().body(pedidoList);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Pedido> findById(@PathVariable @NotNull @Positive Long id) {
        Pedido pedido = service.findById(id);
        return ResponseEntity.ok().body(pedido);
    }

    @GetMapping(value = "/{item}/{id}")
    public ResponseEntity<Boolean> findItem(@PathVariable("id") @NotNull @Positive Long id, @PathVariable("item") @NotBlank @NotNull String item) {
        boolean hasItem = service.findItem(id, item);
        return ResponseEntity.ok().body(hasItem);
    }

    @PostMapping
    public ResponseEntity<Pedido> insert(@RequestBody @NotBlank @NotNull String json) {
        Pedido pedido = service.insert(json);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pedido.getId()).toUri();
        return ResponseEntity.created(uri).body(pedido);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable @NotNull @Positive Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{item}/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable("id") @NotNull @Positive Long id, @PathVariable("item") @NotBlank @NotNull String item) {
        service.deleteItem(id, item);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Pedido> update(@PathVariable @NotNull @Positive Long id, @RequestBody @NotBlank @NotNull String json) {
        Pedido pedido = service.update(id, json);
        return ResponseEntity.ok().body(pedido);
    }

}