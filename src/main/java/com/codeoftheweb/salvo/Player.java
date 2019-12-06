package com.codeoftheweb.salvo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity//tells Spring to create a player table for this class
public class Player {
    @Id //the id instance variable holds the database key for this class.
    // The @Id annotation says this field has a unique value for every instance of Player.
    //Placing @Id on the private instance variable tells JPA to persist all other instance
    // variables as columns in the database.
    //It doesn't matter that fields are marked private.
   // If there are fields that should not be saved, e.g., because they
    // hold temporary scratch data, annotate them with @Transient.
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private String userName;

    public Player(String userName) {
        this.userName = userName;
    }

    public Player() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    @OneToMany(mappedBy="player", fetch= FetchType.EAGER)
    Set<GamePlayer> gamePlayers = new HashSet<>();

    public Set<GamePlayer> getGamePlayers() {
        return gamePlayers;
    }

    public void addGamePlayer(GamePlayer gamePlayer) {
        gamePlayer.setPlayer(this);
        gamePlayers.add(gamePlayer);
    }
}
