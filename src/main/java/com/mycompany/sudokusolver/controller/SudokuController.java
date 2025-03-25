/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sudokusolver.controller;

import com.mycompany.sudokusolver.gui.SudokuGui;
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
    
    public SudokuController(SudokuGui view, SudokuLogic logic) {
        this.gui = view;
        this.logic = logic;
        initListeners();
    }

    private void initListeners() {
        gui.getMenuItemSolve().addActionListener(this);
        gui.getMenuItemClose().addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == gui.getMenuItemClose()) {
            if (JOptionPane.showConfirmDialog(this.gui, "Spiel beenden?", "", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == 0) System.exit(0);
        } else if (e.getSource() == gui.getMenuItemSolve()) {
            
            this.logic.setBoard(this.gui.readToBoard());            
            this.logic.solve();            
            this.gui.updateFromBoard(this.logic.getBoard());
            
        } else {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }        
    }
    
}
