package com.lanchonete.lanchoneteSpring.resources;

import com.lanchonete.lanchoneteSpring.entities.Bebida;
import com.lanchonete.lanchoneteSpring.services.BebidaService;
import com.lanchonete.lanchoneteSpring.services.PedidoService;
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
@RequestMapping(value = "/api/bebidas")
public class BebidaResource {

    private BebidaService service;
    private PedidoService pedidoService;

    public BebidaResource(BebidaService service, PedidoService pedidoService) {
        this.service = service;
        this.pedidoService = pedidoService;
    }

    @GetMapping
    public ResponseEntity<List<Bebida>> findAll() {
        List<Bebida> bebidaList = service.findAll();
        return ResponseEntity.ok().body(bebidaList);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Bebida> findById(@PathVariable @NotNull @Positive Long id) {
        Bebida bebida = service.findById(id);
        return ResponseEntity.ok().body(bebida);
    }

    @GetMapping(value = "/pedido/{id}")
    public ResponseEntity<List<Bebida>> findAll(@PathVariable("id") Long id) {
        List<Bebida> bebidaList = service.findBebidasPedido(id);
        return ResponseEntity.ok().body(bebidaList);
    }

    @PostMapping
    public ResponseEntity<Bebida> insert(@RequestBody @Valid Bebida bebida) {
        bebida = service.insert(bebida);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(bebida.getId()).toUri();
        return ResponseEntity.created(uri).body(bebida);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable @NotNull @Positive Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Bebida> update(@PathVariable @NotNull @Positive Long id, @RequestBody @Valid Bebida bebida) {
        bebida = service.update(id, bebida);
        return ResponseEntity.ok().body(bebida);
    }

}