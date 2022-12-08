package br.edu.ifrs.riogrande.tads.OnlineGame.app.config;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import br.edu.ifrs.riogrande.tads.OnlineGame.app.services.GameJobService;
import br.edu.ifrs.riogrande.tads.OnlineGame.app.services.UserService;
import br.edu.ifrs.riogrande.tads.OnlineGame.app.services.dto.GameJobRequest;
import br.edu.ifrs.riogrande.tads.OnlineGame.app.services.dto.ItemRequest;
import br.edu.ifrs.riogrande.tads.OnlineGame.app.services.dto.UserRequest;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TestData implements ApplicationRunner {

    private final UserService userService;
    private final GameJobService gameJobService;

    public void run(ApplicationArguments args) {
        UserRequest ur = new UserRequest();
        ur.setUsername("Marcio");
        ur.setPassword("Marcio123");
        userService.save(ur);

        UserRequest ur2 = new UserRequest();
        ur2.setUsername("Cleito");
        ur2.setPassword("Cleito111");
        userService.save(ur2);

        GameJobRequest gjr = new GameJobRequest();
        gjr.setName("iron_mining");
        gjr.setStat("STR");
        gjr.setTickToDrop(1);
        gjr.setChanceToDrop(1.);

        ItemRequest ir = new ItemRequest();
        ir.setName("iron_ore");
        ir.setDescription("iron ore");
        ir.setValue(30);
        ir.setResource(true);

        gjr.setDrop(ir);

        gameJobService.save(gjr);

        GameJobRequest gjr2 = new GameJobRequest();
        gjr2.setName("copper_mining");
        gjr2.setStat("STR");
        gjr2.setTickToDrop(1);
        gjr2.setChanceToDrop(1.);

        ItemRequest ir2 = new ItemRequest();
        ir2.setName("copper_ore");
        ir2.setDescription("copper ore");
        ir2.setValue(30);
        ir2.setResource(true);

        gjr2.setDrop(ir2);

        gameJobService.save(gjr2);

        userService.work("Marcio", "iron_mining");
    }
}

// ### New valid User
// POST http://localhost:8080/api/v1/users HTTP/1.1
// Content-Type: application/json

// {
// "username": "Marcio",
// "password": "Marcio123"
// }
// ### List One User
// GET http://localhost:8080/api/v1/users/Marcio HTTP/1.1

// ### New Valid job
// POST http://localhost:8080/api/v1/jobs HTTP/1.1
// Content-Type: application/json

// {
// "name": "iron_mining",
// "stat": "STR",
// "tickToDrop": 1,
// "chanceToDrop": 0.5,
// "drop": {
// "name":"iron_ore",
// "description":"iron ore",
// "value": 30,
// "resource": true
// }
// }