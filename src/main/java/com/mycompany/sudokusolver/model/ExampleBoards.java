/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sudokusolver.model;

import java.util.ArrayList;

/**
 *
 * @author Thomas
 */
public class ExampleBoards {
    
    private ArrayList<int[][]> exampleBoards = new ArrayList<>();
    
    public ExampleBoards() {
        int[][] firstBoard = {
            {0, 3, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 1, 9, 5, 0, 0, 0},
            {0, 0, 8, 0, 0, 0, 0, 6, 0},
            {8, 0, 0, 0, 6, 0, 0, 0, 0},
            {4, 0, 0, 8, 0, 0, 0, 0, 1},
            {0, 0, 0, 0, 2, 0, 0, 0, 0},
            {0, 6, 0, 0, 0, 0, 2, 8, 0},
            {0, 0, 0, 4, 1, 9, 0, 0, 5},
            {0, 0, 0, 0, 0, 0, 0, 7, 0}
        };
        int[][] secondBoard = {
            {0, 0, 0, 0, 0, 0, 0, 1, 0},
            {4, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 2, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 5, 0, 4, 0, 7},
            {0, 0, 8, 0, 0, 0, 3, 0, 0},
            {0, 0, 1, 0, 9, 0, 0, 0, 0},
            {3, 0, 0, 4, 0, 0, 2, 0, 0},
            {0, 5, 0, 1, 0, 0, 0, 0, 0},
            {0, 0, 0, 8, 0, 6, 0, 0, 0}
        };
        exampleBoards.add(firstBoard);
        exampleBoards.add(secondBoard);
    }
    
    public int[][] getExampleBoard() {
        int rand = (int) (Math.random() * exampleBoards.size());
        return exampleBoards.get(rand);
    }
    
    public void addExampleBoard(int[][] newBoard) {
        exampleBoards.add(newBoard);
    }
}
