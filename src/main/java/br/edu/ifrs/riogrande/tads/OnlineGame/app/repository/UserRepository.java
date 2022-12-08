package br.edu.ifrs.riogrande.tads.OnlineGame.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.Repository;

import br.edu.ifrs.riogrande.tads.OnlineGame.app.model.GameJob;
import br.edu.ifrs.riogrande.tads.OnlineGame.app.model.User;

public interface UserRepository extends Repository<User, Integer> {

    User save(User user);

    List<User> findAll();

    Optional<User> findByUsername(String username);

    List<User> findByJob(GameJob username);

    Optional<User> findById(int id);

    void delete(User user);

}
