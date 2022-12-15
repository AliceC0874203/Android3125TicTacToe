package com.example.android_3125_tictactoe;

import java.util.ArrayList;

public class TictactoeObj {
    private int position;
    private int player;

    public TictactoeObj(int position, int player) {
        this.position = position;
        this.player = player;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setPlayer(int player) {
        this.player = player;
    }

    public int getPosition() {
        return position;
    }

    public int getPlayer() {
        return player;
    }

    static ArrayList<TictactoeObj> list = new ArrayList<>();

}
