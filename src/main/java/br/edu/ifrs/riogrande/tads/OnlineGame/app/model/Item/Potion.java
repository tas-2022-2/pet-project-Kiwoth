package br.edu.ifrs.riogrande.tads.OnlineGame.app.model.Item;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.edu.ifrs.riogrande.tads.OnlineGame.app.model.Effect.Effect;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "potion")
public class Potion extends Item {
    Integer maxStack = 10;
    @ManyToOne
    Effect effect;

}
