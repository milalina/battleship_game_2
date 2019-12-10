package com.codeoftheweb.salvo;

import java.util.List;

public class SalvoDto {
    private List<String> salvoes;
    private List<String> hits;
    private int turn;

    public List<String> getSalvoes() {
        return salvoes;
    }

    public void setSalvoes(List<String> salvoes) {
        this.salvoes = salvoes;
    }

    public List<String> getHits() {
        return hits;
    }

    public void setHits(List<String> hits) {
        this.hits = hits;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public SalvoDto(List<String> salvoes, List<String> hits, int turn) {
        this.salvoes = salvoes;
        this.hits = hits;
        this.turn = turn;
    }
}
