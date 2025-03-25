/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sudokusolver.gui;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Thomas
 */
public class SudokuGui extends JFrame {
    
    private JMenuBar menuBar;
    private JMenu menuFile;
    private JMenuItem menuItemSolve, menuItemClose;
    private JTextField[][] cells = new JTextField[9][9];
    
    
    public SudokuGui() {        
        JPanel board = new JPanel();
        board.setLayout(new GridLayout(3, 3));
        board.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        for (int boxRow = 0; boxRow < 3; boxRow++) {
            for (int boxCol = 0; boxCol < 3; boxCol++) {
                JPanel box = new JPanel();
                box.setLayout(new GridLayout(3, 3));
                box.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                for (int cell = 0; cell < 9; cell++) {
                    JTextField field = new JTextField(2);
                    int row = boxRow * 3 + cell / 3;
                    int col = boxCol * 3 + cell % 3;                    
                    field.setHorizontalAlignment(JTextField.CENTER);
                    cells[row][col] = field;
                    box.add(field);
                }
                board.add(box);
            }
        }

        this.add(board);
        this.setTitle("Sudoku Solver");
        this.createMenu();        
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(600, 600);
        this.setLocationRelativeTo(null);
        this.pack();
        this.setVisible(true);
    }
    
    private void createMenu() {
        this.menuBar = new JMenuBar();
        this.menuFile = new JMenu("Datei");
        this.menuItemSolve = new JMenuItem("Sudoku lösen");
        this.menuItemClose = new JMenuItem("Beenden");
        
        this.menuFile.add(menuItemSolve);
        this.menuFile.add(menuItemClose);
        
        this.menuBar.add(menuFile);
        
        this.setJMenuBar(menuBar);
    }

    public JMenuItem getMenuItemSolve() {
        return menuItemSolve;
    }

    public JMenuItem getMenuItemClose() {
        return menuItemClose;
    }

    public JTextField[][] getCells() {
        return cells;
    }
    
    //updates the GUI with values from the passed array
    public void updateFromBoard(int[][] board) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board[row][col] != 0) {
                    this.cells[row][col].setText(String.valueOf(board[row][col]));
                } else {
                    this.cells[row][col].setText("");
                }
            }
        }
    }
    
    //reads values from the GUI and returns them as an array
    public int[][] readToBoard() {
        int[][] board = new int[9][9];
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                try {
                    String text = this.cells[row][col].getText().trim();
                    board[row][col] = text.isEmpty() ? 0 : Integer.parseInt(text);
                } catch (NumberFormatException e) {
                    board[row][col] = 0;
                }
            }
        }
        return board;
    }
}
