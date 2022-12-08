package br.edu.ifrs.riogrande.tads.OnlineGame.app.model;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@ToString
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "inventory")
public class Inventory {
    @Id
    @GeneratedValue
    Integer id;

    Integer maxSpace = 5;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "inventory_id")
    @OrderColumn
    @JsonManagedReference
    List<Slot> slots = Stream.generate(() -> new Slot()).limit(maxSpace).collect(Collectors.toList());
}
