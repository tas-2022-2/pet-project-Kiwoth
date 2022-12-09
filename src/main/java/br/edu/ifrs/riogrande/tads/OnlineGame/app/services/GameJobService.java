package br.edu.ifrs.riogrande.tads.OnlineGame.app.services;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import br.edu.ifrs.riogrande.tads.OnlineGame.app.exceptions.GameJobAlreadyExistsException;
import br.edu.ifrs.riogrande.tads.OnlineGame.app.exceptions.NotFoundException.GameJobNotFoundException;
import br.edu.ifrs.riogrande.tads.OnlineGame.app.exceptions.NotFoundException.StatNotFoundException;
import br.edu.ifrs.riogrande.tads.OnlineGame.app.model.GameJob;
import br.edu.ifrs.riogrande.tads.OnlineGame.app.repository.GameJobRepository;
import br.edu.ifrs.riogrande.tads.OnlineGame.app.repository.UserRepository;
import br.edu.ifrs.riogrande.tads.OnlineGame.app.services.dto.GameJobRequest;
import lombok.RequiredArgsConstructor;

@Service
@Validated
@RequiredArgsConstructor
public class GameJobService {

    private final int SECONDS_PER_TICK = 1;

    private final GameJobRepository jobRepository;
    private final UserRepository userRepository;

    private final StatService statService;
    private final ItemService itemService;

    public void save(GameJobRequest request) {
        if (jobRepository.findByName(request.getName()).isPresent())
            throw new GameJobAlreadyExistsException("job %s already exists", request.getName());

        GameJob job = new GameJob();

        job.setName(request.getName());

        String stat = request.getStat().toLowerCase();
        if (statService.find(stat).isEmpty())
            throw new StatNotFoundException("stat '%s' not found.", stat);
        job.setStat(stat);
        job.setDrop(itemService.GetOrSaveIfAbsent(request.getDrop()));
        job.setTickToDrop(request.getTickToDrop());
        job.setChanceToDrop(request.getChanceToDrop());
        jobRepository.save(job);
    }

    public List<GameJob> list() {
        return jobRepository.findAll();
    }

    public GameJob find(String name) {
        return jobRepository.findByName(name)
                .orElseThrow(() -> new GameJobNotFoundException("job %s not found.", name));
    }

    public void delete(String name) {
        GameJob job = jobRepository.findByName(name)
                .orElseThrow(() -> new GameJobNotFoundException("job %s not found.", name));
        userRepository.findByJob(job).stream().forEach(u -> u.setJob(null));
        jobRepository.delete(job);
    }

    public void update(String name, @Valid GameJobRequest request) {
        GameJob job = jobRepository.findByName(name)
                .orElseThrow(() -> new GameJobNotFoundException("job %s not found.", name));
        String stat = request.getStat().toLowerCase();
        if (statService.find(stat).isEmpty())
            throw new StatNotFoundException("stat '%s' not found.", stat);
        job.setStat(stat);
        job.setTickToDrop(request.getTickToDrop());
        job.setChanceToDrop(request.getChanceToDrop());
        jobRepository.save(job);
    }

    public long getJobTickToSeconds(GameJob job) {
        return job.getTickToDrop() * SECONDS_PER_TICK;
    }

}
