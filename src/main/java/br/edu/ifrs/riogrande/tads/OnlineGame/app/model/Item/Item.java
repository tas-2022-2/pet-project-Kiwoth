package br.edu.ifrs.riogrande.tads.OnlineGame.app.model.Item;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "item")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Item {
    @Id
    @GeneratedValue
    Integer id;

    @Column(name = "username", length = 32, nullable = false, unique = true)
    String name;
    String description;
    Integer value;
    Integer maxStack = 1;
}
