package com.codeoftheweb.salvo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.*;

@Entity
public class GamePlayer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private Date date;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="player_id")
    private Player player;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="game_id")
    private Game game;
    @OneToMany(mappedBy="gamePlayer", fetch=FetchType.EAGER)
    Set<Ship> ships = new HashSet<>();
    @OneToMany(mappedBy="gamePlayer", fetch=FetchType.EAGER)
    List<Salvo> salvoes = new ArrayList<>();

    public Set<Ship> getShips() {
        return ships;
    }

    public List<Salvo> getSalvoes() {
        return salvoes;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }


    public GamePlayer() {
    }

    public GamePlayer(Player player, Game game, Date date) {
        this.player = player;
        this.game =game;
        this.date=date;
    }

    public void setShips(Set<Ship> ships) {
        this.ships = ships;
    }

    public void addShip(Ship ship) {
        ship.setGamePlayer(this);
        ships.add(ship);
    }

    public void addSalvo(Salvo salvo) {
        salvo.setGamePlayer(this);
        salvoes.add(salvo);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public List<ShipDto> createShipsDto(){
        List<ShipDto> shipDtos = new ArrayList<>();
        for(Ship ship: ships){
            ShipDto myShipDto = new ShipDto(ship.getShipType(), ship.getShipLocation());
            shipDtos.add(myShipDto);
        }
       return shipDtos;
    }

    public GamePlayer getOpponent(){
       Set<GamePlayer> gamePlayers = new HashSet<>();
       gamePlayers.addAll(this.getGame().getGamePlayers());
      GamePlayer opponent = new GamePlayer();
       for (GamePlayer gamePlayer: gamePlayers){
           if(this.getId()!= gamePlayer.getId()){
               gamePlayer=opponent;
           } else{return null;}
       }
        return opponent;
    }
}
