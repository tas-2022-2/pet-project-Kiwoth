package br.edu.ifrs.riogrande.tads.OnlineGame.app.model.Effect;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.edu.ifrs.riogrande.tads.OnlineGame.app.model.Stat;
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
@Table(name = "effect")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Effect {
    @Id
    @GeneratedValue
    Integer id;

    @Column(length = 32, nullable = false, unique = true)
    String name;
    String description;

    @ManyToOne
    Stat stat;
}
