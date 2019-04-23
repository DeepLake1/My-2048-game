package com.javarush.task.task35.task3513;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Model model = new Model();
        Controller controller = new Controller(model);
         /*Tile[][] gameTiles = {{new Tile(1),new Tile(2),new Tile(3),new Tile(4)},{new Tile(1),new Tile(2),new Tile(3),new Tile(4)},{new Tile(1),new Tile(2),new Tile(3),new Tile(4)},{new Tile(1),new Tile(2),new Tile(3),new Tile(4)}};
        model.saveState(gameTiles);*/
        JFrame game = new JFrame();

        game.setTitle("2048");
        game.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        game.setSize(450, 500);
        game.setResizable(false);

        game.add(controller.getView());


        game.setLocationRelativeTo(null);
        game.setVisible(true);

    }
}

