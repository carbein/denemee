package com.project.humanresource.controller;

import com.project.humanresource.entity.Zimmet;
import com.project.humanresource.service.ZimmetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/zimmet")
public class ZimmetController {
    private final ZimmetService zimmetService;

    @Autowired
    public ZimmetController(ZimmetService zimmetService) {
        this.zimmetService = zimmetService;
    }

    @PostMapping
    public ResponseEntity<Zimmet> createZimmet(@RequestBody Zimmet zimmet) {
        return ResponseEntity.ok(zimmetService.save(zimmet));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Zimmet> getZimmetById(@PathVariable Long id) {
        Optional<Zimmet> zimmet = zimmetService.findById(id);
        return zimmet.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
} 