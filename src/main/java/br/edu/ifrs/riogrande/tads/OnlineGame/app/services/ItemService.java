package br.edu.ifrs.riogrande.tads.OnlineGame.app.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import br.edu.ifrs.riogrande.tads.OnlineGame.app.exceptions.GameException;
import br.edu.ifrs.riogrande.tads.OnlineGame.app.exceptions.ItemAlreadyExistsException;
import br.edu.ifrs.riogrande.tads.OnlineGame.app.exceptions.NotFoundException.GameJobNotFoundException;
import br.edu.ifrs.riogrande.tads.OnlineGame.app.model.Item.Item;
import br.edu.ifrs.riogrande.tads.OnlineGame.app.model.Item.Resource;
import br.edu.ifrs.riogrande.tads.OnlineGame.app.repository.ItemRepository;
import br.edu.ifrs.riogrande.tads.OnlineGame.app.services.dto.ItemRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Validated // não sei se é ruim fazer isso 🤔
public class ItemService {

    private final ItemRepository itemRepository;

    public void save(@Valid ItemRequest request) {
        saveAndReturn(request);
    }

    public Item saveAndReturn(@Valid ItemRequest request) {
        if (itemRepository.findByName(request.getName()).isPresent())
            throw new ItemAlreadyExistsException("item %s already exists", request.getName());
        Item item;
        if (request.getResource()) {
            item = new Resource();
        } else {
            throw new GameException("Not implemented");
        }

        item.setName(request.getName());
        item.setDescription(request.getDescription());
        item.setValue(request.getValue());
        return itemRepository.save(item);
    }

    public Item GetOrSaveIfAbsent(@Valid ItemRequest request) {
        return itemRepository.findByName(request.getName()).orElseGet(() -> saveAndReturn(request));
    }

    public Optional<Item> find(String name) {
        return itemRepository.findByName(name);
    }

    public List<Item> list() {
        return itemRepository.findAll();
    }

    public void delete(String name) {

        Item item = itemRepository.findByName(name)
                .orElseThrow(() -> new GameJobNotFoundException("item %s not found.", name));
        itemRepository.delete(item);
    }

    public void update(String name, @Valid ItemRequest request) {

        Item item = itemRepository.findByName(name)
                .orElseThrow(() -> new GameJobNotFoundException("item %s not found.", name));
        item.setName(request.getName());
        item.setDescription(request.getDescription());
        item.setValue(request.getValue());
        itemRepository.save(item);
    }

}
