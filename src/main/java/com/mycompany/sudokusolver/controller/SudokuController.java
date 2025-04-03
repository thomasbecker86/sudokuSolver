/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sudokusolver.controller;

import com.mycompany.sudokusolver.gui.InputFilter;
import com.mycompany.sudokusolver.gui.SudokuGui;
import com.mycompany.sudokusolver.model.ExampleBoards;
import com.mycompany.sudokusolver.model.SudokuLogic;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Thomas
 */
public class SudokuController implements ActionListener {

    private SudokuGui gui;
    private SudokuLogic logic;
    private InputFilter inputFilter;
    private ExampleBoards exampleBoards = new ExampleBoards();
    
    public SudokuController(SudokuGui gui, SudokuLogic logic) {
        this.gui = gui;
        this.logic = logic;
        this.inputFilter = new InputFilter(logic);
        this.gui.setInputFilter(inputFilter);
        initListeners();
        
        int[][] newExampleBoard = this.exampleBoards.getExampleBoard();
        this.logic.setBoard(newExampleBoard);
        
        inputFilter.setInternalUpdate(true);
        this.gui.updateFromBoard(this.logic.getBoard());
        inputFilter.setInternalUpdate(false);
    }

    private void initListeners() {
        gui.getMenuItemSolve().addActionListener(this);
        gui.getMenuItemClose().addActionListener(this);
        gui.getButtonSolve().addActionListener(this);
        gui.getButtonReset().addActionListener(this);
        gui.getButtonExample().addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == gui.getMenuItemClose()) {
            if (JOptionPane.showConfirmDialog(this.gui, "Close the game?", "", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == 0) System.exit(0);
        } else if (e.getSource() == gui.getMenuItemSolve() || e.getSource() == gui.getButtonSolve()) {            
            this.logic.setBoard(this.gui.readToBoard());            
            String message = this.logic.solve();
            inputFilter.setInternalUpdate(true);
            this.gui.updateFromBoard(this.logic.getBoard());
            inputFilter.setInternalUpdate(false);
            this.gui.setOutput(message);            
        } else if (e.getSource() == gui.getButtonReset()) {
            int[][] newBoard = new int[9][9];
            this.logic.setBoard(newBoard);
            inputFilter.setInternalUpdate(true);
            this.gui.updateFromBoard(newBoard);
            inputFilter.setInternalUpdate(false);
            this.gui.setOutput("");
        } else if (e.getSource() == gui.getButtonExample()) {
            int[][] newExampleBoard = this.exampleBoards.getExampleBoard();            
            this.logic.setBoard(newExampleBoard);
            logic.printSodoku();
            inputFilter.setInternalUpdate(true);
            this.gui.updateFromBoard(this.logic.getBoard());
            inputFilter.setInternalUpdate(false);
        } else {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }        
    }
}
