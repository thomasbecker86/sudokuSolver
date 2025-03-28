/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sudokusolver.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
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
    private JPanel controlArea, board;
    private JTextArea output;
    private JButton buttonSolve, buttonReset, buttonExample;
    
    
    public SudokuGui() {        
        this.createBoard();
        this.createMenu();
        this.createControlArea();
        
        this.output = new JTextArea(5, 20);
        output.setEditable(false);
        output.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Output Area"));
 
        JPanel firstRow = new JPanel();
        firstRow.setLayout(new BoxLayout(firstRow, BoxLayout.X_AXIS));
        firstRow.add(board);
        firstRow.add(Box.createRigidArea(new Dimension(10, 0)));
        firstRow.add(controlArea);
        firstRow.add(Box.createRigidArea(new Dimension(10, 0)));
        
        this.setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        this.add(firstRow);
        this.add(Box.createRigidArea(new Dimension(0, 10)));
        this.add(output);

        this.setTitle("Sudoku Solver");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(600, 600);
        this.setMinimumSize(new Dimension(300, 300));
        this.setLocationRelativeTo(null);
        this.pack();
        this.setVisible(true);
    }
    
    private void createBoard() {
        this.board = new JPanel();
        this.board.setLayout(new GridLayout(3, 3));
        this.board.setBorder(BorderFactory.createLineBorder(Color.BLACK));
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
                this.board.add(box);
            }
        }
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int size = Math.min(getWidth(), getHeight()); // Kleinere Dimension wählen
                board.setPreferredSize(new Dimension(size, size));
                board.revalidate();
                board.repaint();
            }
        });
    }
    
    private void createControlArea() {
        this.controlArea = new JPanel();
        this.controlArea.setLayout(new BoxLayout(controlArea, BoxLayout.Y_AXIS));

        this.buttonSolve = new JButton("Solve");
        this.buttonSolve.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.buttonReset = new JButton("Reset");
        this.buttonReset.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.buttonExample = new JButton("Example");
        this.buttonExample.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        this.controlArea.add(this.buttonSolve);        
        this.controlArea.add(Box.createRigidArea(new Dimension(0, 10)));
        this.controlArea.add(this.buttonReset);
        this.controlArea.add(Box.createRigidArea(new Dimension(0, 10)));
        this.controlArea.add(this.buttonExample);
    }
    
    private void createMenu() {
        this.menuBar = new JMenuBar();
        this.menuFile = new JMenu("File");
        this.menuItemSolve = new JMenuItem("Solve");
        this.menuItemClose = new JMenuItem("Exit");
        
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

    public JButton getButtonSolve() {
        return buttonSolve;
    }

    public JButton getButtonReset() {
        return buttonReset;
    }

    public JButton getButtonExample() {
        return buttonExample;
    }
    
    public JTextField[][] getCells() {
        return cells;
    }
    
    public void setOutput(String text) {
        this.output.setText(text);
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
