package main;

import javax.swing.*;

public class mainClass {
    public static void main(String[] args) {

        JFrame window = new JFrame(null, null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Hunting game");

        controlPanel gamePanel = new controlPanel();
        window.add(gamePanel);
        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);
         gamePanel.setupGame();


        gamePanel.startGameThread();
    }
}