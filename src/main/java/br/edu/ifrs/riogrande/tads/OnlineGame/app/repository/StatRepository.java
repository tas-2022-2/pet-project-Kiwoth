package br.edu.ifrs.riogrande.tads.OnlineGame.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.Repository;

import br.edu.ifrs.riogrande.tads.OnlineGame.app.model.Stat;
import br.edu.ifrs.riogrande.tads.OnlineGame.app.model.User;

public interface StatRepository extends Repository<Stat, Integer> {

    Stat save(Stat job);

    List<Stat> findAll();

    List<Stat> findByName(String name);

    Optional<Stat> findByUser(User user);

    void delete(Stat job);

}
