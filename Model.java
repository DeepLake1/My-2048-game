package com.javarush.task.task35.task3513;

import java.util.ArrayList;
import java.util.List;

public class Model {
    private final static int FIELD_WIDTH = 4;
    private Tile[][] gameTiles /*= {{new Tile(1),new Tile(2),new Tile(3),new Tile(4)},{new Tile(1),new Tile(2),new Tile(3),new Tile(4)},{new Tile(1),new Tile(2),new Tile(3),new Tile(4)},{new Tile(1),new Tile(2),new Tile(3),new Tile(4)}}*/;
    protected int score;
    protected int maxTile;

    public Model() {
        resetGameTiles();
        this.score = 0;
        this.maxTile = 0;
    }

    public Tile[][] getGameTiles() {
        return gameTiles;
    }


    public void resetGameTiles() {
        gameTiles = new Tile[Model.FIELD_WIDTH][Model.FIELD_WIDTH];
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                gameTiles[i][j] = new Tile();
            }
        }
        addTile();
        addTile();
    }

    public void addTile() {
        List<Tile> emptyTilesList = getEmptyTiles();
        if (!emptyTilesList.isEmpty()) {
            emptyTilesList.get((int) (emptyTilesList.size() * Math.random())).value = Math.random() < 0.9 ? 2 : 4;
        }
    }

    private List<Tile> getEmptyTiles() {
        List<Tile> tileList = new ArrayList<>();
        for (Tile[] s : gameTiles) {
            for (Tile emptyTile : s) {
                if (emptyTile.value == 0) tileList.add(emptyTile);
            }
        }
        return tileList;
    }

    public boolean compressTiles(Tile[] tiles) {
        int count = 0;
        boolean somethingChanged = false;
        int[] values = new int[tiles.length];
        for (int g = 0; g < tiles.length; g++) values[g]=tiles[g].value;
        for (int i = 0; i < tiles.length; i++)
        {  if (tiles[i].value != 0)
                tiles[count++].value = tiles[i].value;}

        while (count < tiles.length)
        {tiles[count++].value = 0;
       }
        for (int f = 0; f < tiles.length; f++) if(!(values[f]==tiles[f].value)) somethingChanged=true;
        return somethingChanged;
    }


    private boolean mergeTiles(Tile[] tiles) {
        int oldScore = score;
        for (int i = 0; i < tiles.length - 1; i++) {
            if (tiles[i].value == tiles[i + 1].value&&tiles[i].value!=0) {
                tiles[i].value = tiles[i].value + tiles[i + 1].value;
                score+=tiles[i].value;
                if(tiles[i].value>this.maxTile) maxTile = tiles[i].value;
                tiles[i + 1].value = 0;
            }
        }
        int count = 0;
        for (int i = 0; i < tiles.length; i++)
            if (tiles[i].value != 0)
                tiles[count++].value = tiles[i].value;

        while (count < tiles.length)
            tiles[count++].value = 0;

        return oldScore!=score;
    }

    // поворот матрицы на 90 градусов против часовой стрелки

    public void rotate(){
        Tile[][] newArray = new Tile[FIELD_WIDTH][FIELD_WIDTH];

        for (int i = 0; i < FIELD_WIDTH; ++i) {
            for (int j = 0; j < FIELD_WIDTH; ++j) {
                newArray[i][ j] = gameTiles[4 - j - 1][ i];
            }
        }
        gameTiles = newArray;
    }
    public void left(){
        boolean someTilesHasChanged = false;
        for(Tile[]tiles:gameTiles){
            if(compressTiles(tiles)|mergeTiles(tiles))someTilesHasChanged=true;
        }
        if(someTilesHasChanged) addTile();
    }
    public void right(){
        rotate();
        rotate();
       left();
        rotate();
        rotate();
    }
    public void down(){
      rotate();
       left();
        rotate();
        rotate();
        rotate();
    }
    public void up(){
        rotate();
        rotate();
        rotate();
       left();
        rotate();
    }
    public boolean canMove (){
        if(!getEmptyTiles().isEmpty()) return true;
        for (int i = 0; i < gameTiles.length; i++) {
            for (int j = 1; j < gameTiles.length; j++) {
                if (gameTiles[i][j].value == gameTiles[i][j - 1].value)
                    return true;
            }
        }
        for (int j = 0; j < gameTiles.length; j++) {
            for (int i = 1; i < gameTiles.length; i++) {
                if (gameTiles[i][j].value == gameTiles[i - 1][j].value)
                    return true;
            }
        }
        return false;
    }
}
