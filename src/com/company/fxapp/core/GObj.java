package com.company.fxapp.core;

public class GObj implements PlaceHaving {
    private GameCell place;
    private Player player;

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public GameCell getPlace() {
        return place;
    }

    public void setPlace(GameCell place) {
        this.place = place;
    }
}
