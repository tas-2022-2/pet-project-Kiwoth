package br.edu.ifrs.riogrande.tads.OnlineGame.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.edu.ifrs.riogrande.tads.OnlineGame.app.model.Item.Item;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "job")
public class GameJob {

    @Id
    @GeneratedValue
    Integer id;

    @Column(name = "name", length = 32, nullable = false, unique = true)
    String name;

    String stat;

    Integer tickToDrop; // Every ticks counts as SECONDS_PER_TICK in the gameJobService.java

    Double chanceToDrop;

    @ManyToOne
    Item drop;
}