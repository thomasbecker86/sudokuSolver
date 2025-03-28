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
    }
}
