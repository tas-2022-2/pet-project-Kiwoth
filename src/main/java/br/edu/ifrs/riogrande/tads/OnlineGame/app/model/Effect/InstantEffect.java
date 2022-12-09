package br.edu.ifrs.riogrande.tads.OnlineGame.app.model.Effect;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

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
@Table(name = "instant_effect")
public class InstantEffect extends Effect {
    Double value;

    @Enumerated(EnumType.STRING)
    EffectType type;
}
