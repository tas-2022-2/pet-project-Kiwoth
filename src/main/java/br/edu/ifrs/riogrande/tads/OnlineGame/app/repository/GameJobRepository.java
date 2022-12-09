package br.edu.ifrs.riogrande.tads.OnlineGame.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.Repository;

import br.edu.ifrs.riogrande.tads.OnlineGame.app.model.GameJob;

public interface GameJobRepository extends Repository<GameJob, Integer> {

    GameJob save(GameJob job);

    List<GameJob> findAll();

    Optional<GameJob> findByName(String name);

    void delete(GameJob job);

}
