package com.codeoftheweb.salvo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
public class Salvo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private Date turnNumber;
    @ElementCollection
    @Column(name="slocations")
    private List<String> salvoLocations = new ArrayList<>();
    @ElementCollection
    @Column(name="hlocations")
    private List<String> hitLocations = new ArrayList<>();

    public List<String> getHitLocations() {
        return hitLocations;
    }

    public void setHitLocations(List<String> hitLocations){
        this.hitLocations = getHitsInThisTurn();
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="gamePlayer_id")
    private GamePlayer gamePlayer;

    public Salvo() {
    }

    public Salvo(Date turnNumber, List<String> salvoLocations) {
        this.salvoLocations = salvoLocations;
        this.turnNumber = turnNumber;
    }

    public Salvo(List<String> hitLocations) {
        this.hitLocations = hitLocations;
    }

    public long getId() {
        return id;
    }

    public void setGamePlayer(GamePlayer gamePlayer) {
        this.gamePlayer = gamePlayer;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<String> getSalvoLocations() {
        return salvoLocations;
    }

    public void setSalvoLocations(List<String> salvoLocations) {
        this.salvoLocations = salvoLocations;
    }

    public Date getTurnNumber() {
        return turnNumber;
    }

    public void setTurnNumber(Date turnNumber) {
        this.turnNumber = turnNumber;
    }

    public GamePlayer getGamePlayer() {
        return gamePlayer;
    }

    public List<String> getMyShipsLocationsAsString(){
        List<String> myShipsLocationsAsString=new ArrayList<>();
        Set<Ship> myShips=this.getGamePlayer().getShips();
        if(myShips.size()>0){
            for (Ship ship: myShips){
                myShipsLocationsAsString.addAll(ship.getShipLocation());
            }
            return myShipsLocationsAsString;
        }else{return null;}
    }

    public List<String> opponentSalvoThisTurnAsString(){
        GamePlayer myGamePlayer = this.getGamePlayer();
        GamePlayer myOpponent=myGamePlayer.getOpponent();
        List<Salvo> mySalvoAllTurns=myGamePlayer.getSalvoes();
        List<Salvo> opponentSalvoAllTurns = myOpponent.getSalvoes();
        if( mySalvoAllTurns.size()==opponentSalvoAllTurns.size()){
            List<String> opponentSalvoThisTurn= new ArrayList<>();
            for(Salvo salvo: opponentSalvoAllTurns){
                if(this.getTurnNumber()==salvo.getTurnNumber()){
                    opponentSalvoThisTurn.addAll(salvo.getSalvoLocations());
                }
            }return opponentSalvoThisTurn;
        }else{return null;}
        }

        List<String> getHitsInThisTurn(){
        List<String> myShips=this.getMyShipsLocationsAsString();
        List<String> opponentSalvoesThisTurn=this.opponentSalvoThisTurnAsString();
        if(myShips!=null && opponentSalvoesThisTurn!=null){
            opponentSalvoesThisTurn.retainAll(myShips);
            hitLocations=opponentSalvoesThisTurn;
            return hitLocations;
        }else{return null;}
    }

}
