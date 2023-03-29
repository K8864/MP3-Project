package main;

import player.*;

import javax.swing.*;
import java.util.*;

public class Main {

    private static JFrame window;
    public static JFrame getWindow() {
        return window;
    }

    public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 3, 2));
        System.out.println(arr.add(1));
        arr.add(arr.size(), 9);
        System.out.println(arr.set(arr.size()-1, 8));
        System.out.println(arr.remove(arr.size()-1));
        System.out.println(arr);
        for (int i = 0; i < arr.size(); i++) {
            int pos = i;
            for (int j = i; j < arr.size(); j++) {
                if (arr.get(j) < arr.get(pos))
                    pos = j;
            }
            int min = arr.get(pos);
            arr.set(pos, arr.get(i));
            arr.set(i, min);
        }
        System.out.println(arr);
        arr = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 3, 2, 1));
        System.out.println(arr);
        for (int i = 1; i < arr.size(); i++) {
            int tmp = arr.get(i);
            int j = i;
            while ((j > 0) && (arr.get(j - 1) > tmp)) {
                arr.set(j, arr.get(j - 1));
                j--;
            }
            arr.set(j, tmp);
        }
        System.out.println(arr);
        int[][] arr2D = new int[][] {{1, 2}, {3, 4}};
        for(int[] r : arr2D) {
            for (int i : r) {
                System.out.print(i);
            }
            System.out.println();
        }
        for(int c=0; c<arr2D[0].length; c++) {
            for (int r = 0; r < arr2D.length; r++) {
                System.out.print(arr2D[r][c]);
            }
            System.out.println();
        }



        //Window
        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Game");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.setUpGame();
        gamePanel.startGameThread();
    }
}