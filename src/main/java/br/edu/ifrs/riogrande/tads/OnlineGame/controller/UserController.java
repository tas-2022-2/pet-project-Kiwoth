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
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.edu.ifrs.riogrande.tads.OnlineGame.app.model.User;
import br.edu.ifrs.riogrande.tads.OnlineGame.app.services.UserService;
import br.edu.ifrs.riogrande.tads.OnlineGame.app.services.dto.UserRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("/api/v1")
public class UserController {
    private final UserService userService;

    @PostMapping(path = "/users", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> create(
            @RequestBody @Valid UserRequest body) {
        userService.save(body);

        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/{username}")
                .buildAndExpand(body.getUsername()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping(path = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> readAll() {
        return ResponseEntity.ok().body(userService.list());
    }

    @GetMapping(path = "/users/{username}")
    public ResponseEntity<Object> read(@PathVariable String username) {
        User user = userService.find(username);
        return ResponseEntity.ok(user);
    }

    @PutMapping(path = "/users/{username}")
    public ResponseEntity<Object> update(
            @RequestBody @Valid UserRequest body,
            @PathVariable String username) {
        userService.update(username, body);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(path = "/users/{username}")
    @ResponseStatus(code = HttpStatus.OK)
    public void delete(@PathVariable String username) {
        userService.delete(username);
    }

    @PatchMapping(path = "/users/{username}/work/{jobName}")
    public ResponseEntity<Object> work(
            @PathVariable String username,
            @PathVariable String jobName) {
        return ResponseEntity.ok(userService.work(username, jobName));
    }

    @PatchMapping(path = "/users/{username}/stop_work")
    public ResponseEntity<Object> stopWork(
            @PathVariable String username) {
        return ResponseEntity.ok(userService.stopWork(username));
    }

    @GetMapping(path = "/users/{username}/inventory/")
    public ResponseEntity<Object> getInventory(
            @PathVariable String username) {
        User user = userService.find(username);
        return ResponseEntity.ok(userService.getUserInventory(user));
    }

    @PatchMapping(path = "/users/{username}/inventory/sell/{slot_id}")
    public ResponseEntity<Object> sellItem(
            @PathVariable int slot_id,
            @PathVariable String username) {
        User user = userService.find(username);
        return ResponseEntity.ok(userService.sellItem(user, slot_id));
    }

}
