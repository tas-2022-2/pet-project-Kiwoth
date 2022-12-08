package br.edu.ifrs.riogrande.tads.OnlineGame.app.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import br.edu.ifrs.riogrande.tads.OnlineGame.app.model.Stat;
import br.edu.ifrs.riogrande.tads.OnlineGame.app.repository.StatRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Validated // não sei se é ruim fazer isso 🤔
public class StatService {

    private final StatRepository statRepository;

    public List<Stat> find(String name) {
        return statRepository.findByName(name);
    }

}
