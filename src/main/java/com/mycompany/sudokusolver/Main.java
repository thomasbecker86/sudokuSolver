/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sudokusolver;

import com.mycompany.sudokusolver.controller.SudokuController;
import com.mycompany.sudokusolver.gui.SudokuGui;
import com.mycompany.sudokusolver.model.SudokuLogic;

/**
 *
 * @author Thomas
 */
public class Main {
    
    public static void main(String[] args) {
        SudokuGui gui = new SudokuGui();
        SudokuLogic logic = new SudokuLogic();
        SudokuController controller = new SudokuController(gui, logic);
        
    //    gui.updateFromBoard(logic.getBoard());
        
        
        int[][] exampleBoard = {
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
        gui.updateFromBoard(exampleBoard);

    }
}
