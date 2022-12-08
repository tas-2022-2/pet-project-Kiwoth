package br.edu.ifrs.riogrande.tads.OnlineGame.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.Repository;

import br.edu.ifrs.riogrande.tads.OnlineGame.app.model.Item.Item;

public interface ItemRepository extends Repository<Item, Integer> {

    Item save(Item item);

    List<Item> findAll();

    Optional<Item> findByName(String name);

    void delete(Item item);

}
