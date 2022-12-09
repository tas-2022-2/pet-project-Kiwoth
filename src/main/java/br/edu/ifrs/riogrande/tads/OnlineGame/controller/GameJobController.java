package br.edu.ifrs.riogrande.tads.OnlineGame.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.edu.ifrs.riogrande.tads.OnlineGame.app.model.GameJob;
import br.edu.ifrs.riogrande.tads.OnlineGame.app.services.GameJobService;
import br.edu.ifrs.riogrande.tads.OnlineGame.app.services.dto.GameJobRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("/api/v1")
public class GameJobController {
    private final GameJobService service;

    @PostMapping(path = "/jobs", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> create(
            @RequestBody @Valid GameJobRequest body) {
        service.save(body);
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/{jobname}")
                .buildAndExpand(body.getName()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(path = "/jobs/{name}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.OK)
    public void update(
            @PathVariable String name,
            @RequestBody @Valid GameJobRequest body) {
        service.update(name, body);

    }

    @DeleteMapping(path = "/jobs/{name}")
    @ResponseStatus(code = HttpStatus.OK)
    public void delete(@PathVariable String name) {
        service.delete(name);
    }

    @GetMapping(path = "/jobs", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<GameJob>> readAll() {
        return ResponseEntity.ok().body(service.list());
    }

    @GetMapping(path = "/jobs/{name}")
    public ResponseEntity<?> read(@PathVariable String name) {
        GameJob user = service.find(name);
        return ResponseEntity.ok(user);
    }

}
