package br.edu.ifrs.riogrande.tads.OnlineGame.app.services;

import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import br.edu.ifrs.riogrande.tads.OnlineGame.app.exceptions.IllegalGameJobChangeException;
import br.edu.ifrs.riogrande.tads.OnlineGame.app.exceptions.UserAlreadyExistsException;
import br.edu.ifrs.riogrande.tads.OnlineGame.app.exceptions.NotFoundException.UserNotFoundException;
import br.edu.ifrs.riogrande.tads.OnlineGame.app.model.GameJob;
import br.edu.ifrs.riogrande.tads.OnlineGame.app.model.Inventory;
import br.edu.ifrs.riogrande.tads.OnlineGame.app.model.Stat;
import br.edu.ifrs.riogrande.tads.OnlineGame.app.model.User;
import br.edu.ifrs.riogrande.tads.OnlineGame.app.repository.UserRepository;
import br.edu.ifrs.riogrande.tads.OnlineGame.app.services.dto.UserRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final GameJobService gameJobService;
    private final InventoryService inventoryService;

    public void save(UserRequest request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent())
            throw new UserAlreadyExistsException("user %s already exists", request.getUsername());
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        userRepository.save(user);
    }

    public List<User> list() {
        return userRepository.findAll();
    }

    public User find(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("user %s not found.", username));
    }

    public void delete(String username) {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("user %s not found.", username));
        userRepository.delete(user);
    }

    public void update(String username, @Valid UserRequest request) {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("user %s not found.", username));
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        userRepository.save(user);
    }

    public Map<String, Object> work(String username, String jobName) {
        Map<String, Object> outBody = new HashMap<>();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("user %s not found.", username));
        GameJob job = gameJobService.find(jobName);
        if (user.getJob() != null) {
            if (user.getJob().getName().equals(jobName))
                throw new IllegalGameJobChangeException("user is already working with " + jobName);
            else {
                outBody.put("lastWorkResult", calculateDrop(user));
            }
        }
        user.setJob(job);
        user.setJobStartTime(new Date());
        userRepository.save(user);
        outBody.put("newJob", jobName);
        return outBody;
    }

    public Map<String, Object> stopWork(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("user %s not found.", username));
        if (user.getJob() == null)
            throw new IllegalGameJobChangeException("user already stopped working");
        var resultado = calculateDrop(user);
        user.setJob(null);
        user.setJobStartTime(new Date());
        userRepository.save(user);
        return resultado;

    }

    private Map<String, Object> calculateDrop(User user) {
        Map<String, Object> outBody = new HashMap<>();
        GameJob job = user.getJob();
        long jobSeconds = gameJobService.getJobTickToSeconds(job);
        double chance = job.getChanceToDrop();
        long workInSeconds = ChronoUnit.SECONDS.between(user.getJobStartTime().toInstant(), new Date().toInstant());
        long diff = workInSeconds;
        long workDone = diff / jobSeconds;
        int dropCount = 0;
        Stat statModifier = user.getStats().stream().filter(s -> s.getName() == job.getStat()).findAny().get();
        for (int i = 0; i < workDone; i++) {
            if (Math.random() <= chance)
                dropCount += statModifier.getActualValue();
        }
        long failedJob = workDone - dropCount;
        Map<String, Object> outAdd = inventoryService.addReward(user.getInventory(), job.getDrop(), dropCount);
        outBody.put("workDone", workDone);
        outBody.put("dropCount", dropCount);
        outBody.put("workInSeconds", workInSeconds);
        outBody.put("failedJob", failedJob);
        outBody.putAll(outAdd);
        outBody.replace("message",
                new StringBuilder()
                        .append("you worked with ")
                        .append(job.getName())
                        .append(" for ")
                        .append(workInSeconds)
                        .append(" seconds, completed ")
                        .append(workDone)
                        .append(" tasks and dropped ")
                        .append(dropCount)
                        .append(" ")
                        .append(job.getDrop().getName())
                        .append(outBody.get("message"))
                        .toString());
        return outBody;
    }

    public Map<String, Object> sellItem(User user, int id) {
        Map<String, Object> out = inventoryService.sellItem(inventoryService.getSlot(user.getInventory(), id));
        user.setGold(user.getGold() + ((Integer) out.get("total")));
        userRepository.save(user);
        return out;
    }

    public Inventory getUserInventory(User user) {
        return user.getInventory();
    }

}
