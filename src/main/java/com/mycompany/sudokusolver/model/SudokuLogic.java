/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sudokusolver.model;

import java.util.Arrays;

/**
 *
 * @author Thomas
 */
public class SudokuLogic {
    
    private int[][] board = new int[9][9];
    
    public int[][] getBoard() {
        return board;
    }
    
    public void setBoard(int[][] newBoard) {
        this.board = new int[newBoard.length][];
        for (int i = 0; i < newBoard.length; i++) {
            this.board[i] = Arrays.copyOf(newBoard[i], newBoard[i].length);
        }
    }
    
    public void printSodoku() {
        for (int[] row : this.board) {
            for (int number : row) {
                System.out.print(number);
            }
            System.out.println();
        }
        System.out.println("\n");
    }
    
    //Checks if a given candidate can be inserted into the current row
    public boolean checkRow(int rowIndex, int candidate) {
        for (int columnIndex = 0; columnIndex < this.board[rowIndex].length ; columnIndex++) {
            if (candidate == Math.abs(this.board[rowIndex][columnIndex])) {
                return false;
            }
        }
        return true;
    }
    
    //Checks if a given candidate can be inserted into the current column
    public boolean checkColumn(int columnIndex, int candidate) {
        for (int rowIndex = 0; rowIndex < this.board.length; rowIndex++) {
            if (candidate == Math.abs(this.board[rowIndex][columnIndex])) {
                return false;
            }
        }
        return true;
    }
    
    //Checks if a given candidate can be inserted into the current subgrid
    public boolean checkSubgrid(int rowIndex, int columnIndex, int candidate) {
        int startRowIndex = (rowIndex / 3) * 3;
        int startColumnIndex = (columnIndex / 3) * 3;
        for (int i = startRowIndex; i < startRowIndex + 3; i++) {
            for (int j = startColumnIndex; j < startColumnIndex + 3; j++) {
                if (candidate == Math.abs(this.board[i][j])) {
                    return false;
                }
            }            
        }
        return true;
    }
    
    public void insertValue(int rowIndex, int columnIndex, int value) {
        this.board[rowIndex][columnIndex] = value;
    }
    
    public void removeValue(int rowIndex, int columnIndex) {
        this.board[rowIndex][columnIndex] = 0;
    }
    
    //Converts all negative numbers (i.e. preallocated numbers) into positive numbers
    private void makePositive() {
        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board.length; j++) {
                this.board[i][j] = Math.abs(this.board[i][j]);
            }
        }
    }
    //Converts all numbers into negative numbers, needs to be called by the solve-method to work
    private void makeNegative() {
        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board.length; j++) {
                this.board[i][j] = this.board[i][j] *= -1;
            }
        }
    }
    
    public String solve() {
        
        this.makeNegative();
        int position = 0;
        int rowIndex;
        int columnIndex;
        int counter = 0;
        
        while (position < 81) {
            counter++;
            if (position < 0) {
            //    System.out.println("No solution found!");
                return "No solution found!";
            }
            
            rowIndex = position / 9;
        //    System.out.println("RowIndex: " + rowIndex);
            columnIndex = position % 9;
        //    System.out.println("ColumnIndex: " + columnIndex);
            
            if (this.board[rowIndex][columnIndex] >= 0) {
                int candidate = this.board[rowIndex][columnIndex] + 1;
                while (!checkRow(rowIndex, candidate) || !checkColumn(columnIndex, candidate) || !checkSubgrid(rowIndex, columnIndex, candidate)) {
                    candidate++;
                }
                if (candidate <= 9) {
                    this.board[rowIndex][columnIndex] = candidate;
                    position++;
                } else {
                    this.board[rowIndex][columnIndex] = 0;
                    try {
                        position--;
                        while (board[position / 9][position % 9] < 0) {
                            position--;
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        this.makePositive();
                        return "An error occured, check the input!";
                    }
                 }
            } else {
                position++;
            }
        }
    //    System.out.println("Number of loop cycles: " + counter + "\n");
        
        this.makePositive();
        
        return "Sudoku succesfully solved!\nNumber of loop cycles: " + counter + "\n";
    }
}
