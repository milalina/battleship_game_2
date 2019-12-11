package com.codeoftheweb.salvo;

import java.util.List;

public class GamePlayerDto {
    private long id;
    private PlayerDto player;
    private List<SalvoDto> salvoes;


    public GamePlayerDto(long id, PlayerDto player, List<SalvoDto> salvoes) {
        this.id = id;
        this.player = player;
        this.salvoes=salvoes;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<SalvoDto> getSalvoes() {
        return salvoes;
    }

    public void setSalvoes(List<SalvoDto> salvoes) {
        this.salvoes = salvoes;
    }

    public PlayerDto getPlayer() {
        return player;
    }

    public void setPlayer(PlayerDto player) {
        this.player = player;
    }


}
