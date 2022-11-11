package com.lanchonete.lanchoneteSpring.resources;

import com.lanchonete.lanchoneteSpring.entities.Lanche;
import com.lanchonete.lanchoneteSpring.services.LancheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/lanches")
public class LancheResource {

    @Autowired
    LancheService service;

    @GetMapping
    public ResponseEntity<List<Lanche>> findAll() {
        List<Lanche> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

}
