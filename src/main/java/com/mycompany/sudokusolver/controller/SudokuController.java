/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sudokusolver.controller;

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
    private ExampleBoards exampleBoards = new ExampleBoards();
    
    public SudokuController(SudokuGui view, SudokuLogic logic) {
        this.gui = view;
        this.logic = logic;
        initListeners();
        this.gui.updateFromBoard(this.exampleBoards.getExampleBoard());
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
            if (JOptionPane.showConfirmDialog(this.gui, "Spiel beenden?", "", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == 0) System.exit(0);
        } else if (e.getSource() == gui.getMenuItemSolve() || e.getSource() == gui.getButtonSolve()) {            
            this.logic.setBoard(this.gui.readToBoard());            
            String message = this.logic.solve();            
            this.gui.updateFromBoard(this.logic.getBoard());
            this.gui.setOutput(message);            
        } else if (e.getSource() == gui.getButtonReset()) {
            int[][] newBoard = new int[9][9];
            this.logic.setBoard(newBoard);
            this.gui.updateFromBoard(newBoard);
            this.gui.setOutput("");
        } else if (e.getSource() == gui.getButtonExample()) {
            this.gui.updateFromBoard(this.exampleBoards.getExampleBoard());
        } else {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }        
    }
    
}
