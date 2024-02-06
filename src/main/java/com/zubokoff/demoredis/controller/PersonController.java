package com.zubokoff.demoredis.controller;

import com.zubokoff.demoredis.entity.redis.Person;
import com.zubokoff.demoredis.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("persons")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @PostMapping("/{qtde}")
    public void save(@PathVariable int qtde, @RequestBody Person person) {
        if (qtde == 0) {
            qtde = 1;
        }
        this.personService.save(qtde, person);
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok().body(this.personService.getAll());
    }

    @GetMapping("/keys/{patternKey}")
    public ResponseEntity<?> getKeys(@PathVariable String patternKey) {
        return ResponseEntity.ok().body(this.personService.getKeys(patternKey));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable String id) {
        Person person = this.personService.getById(id);
        if (person != null) {
            return ResponseEntity.ok().body(person);
        }
        return ResponseEntity.ok().body("Valor não localizado");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable String id) {
        this.personService.deleteById(id);
        return ResponseEntity.ok("Pessoa deletada com sucesso");
    }
}
