package com.codeoftheweb.salvo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.*;

@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    Date gameStart = new Date();
    @OneToMany(mappedBy="game", fetch=FetchType.EAGER)
    Set<GamePlayer> gamePlayers = new HashSet<>();

    public Set<GamePlayer> getGamePlayers() {
        return gamePlayers;
    }

    public void addGamePlayer(GamePlayer gamePlayer) {
        gamePlayer.setGame(this);
        gamePlayers.add(gamePlayer);
    }

    public Game() {
    }

    public Game(Date gameStart) {
        this.gameStart=gameStart;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getGameStart() {
        return gameStart;
    }

    public void setGameStart(Date gameStart) {
        this.gameStart = gameStart;
    }

   public List<GamePlayerDto> makeGamePlayerDto(){
        List<GamePlayerDto> gamePlayerDtos = new ArrayList<>();
        for(GamePlayer gamePlayer: gamePlayers){
            Player player = gamePlayer.getPlayer();
            PlayerDto playerDto = new PlayerDto(player.getId(), player.getUserName());
            GamePlayerDto gamePlayerDto = new GamePlayerDto(gamePlayer.getId(), playerDto);
            gamePlayerDtos.add(gamePlayerDto);
        }
        return gamePlayerDtos;
   }

}
