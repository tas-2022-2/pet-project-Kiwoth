package br.edu.ifrs.riogrande.tads.OnlineGame.app.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderColumn;

import com.fasterxml.jackson.annotation.JsonManagedReference;

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
public class User {

        @Id
        @GeneratedValue
        Integer id;

        @Column(name = "username", length = 32, nullable = false, unique = true)
        String username;

        @Column(name = "password", length = 64, nullable = false)
        String password;

        @ManyToOne
        GameJob job = null;

        @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
        Inventory inventory = new Inventory();

        Integer gold = 100;

        @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
        @JoinColumn(name = "user_id")
        @OrderColumn
        @JsonManagedReference
        List<Stat> stats = List.of(Stat.builder().name("hp").value(10).actualValue(10).build(),
                        Stat.builder().name("str").value(1).actualValue(1).build(),
                        Stat.builder().name("dex").value(1).actualValue(1).build(),

                        Stat.builder().name("int").value(1).actualValue(1).build());

        Date jobStartTime = new Date();

}
