package br.edu.ifrs.riogrande.tads.OnlineGame.app.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

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
@Table(name = "slot")
public class Slot {
    @Id
    @GeneratedValue
    Integer id;

    @ManyToOne
    Item item = null;

    Integer quantity = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    Inventory inventory;
}
