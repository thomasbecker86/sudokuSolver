/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sudokusolver.model;

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
        this.board = newBoard;
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
    private boolean checkRow(int rowIndex, int candidate) {
        for (int columnIndex = 0; columnIndex < this.board[rowIndex].length ; columnIndex++) {
            if (candidate == Math.abs(this.board[rowIndex][columnIndex])) {
                return false;
            }
        }
        return true;
    }
    
    //Checks if a given candidate can be inserted into the current column
    private boolean checkColumn(int columnIndex, int candidate) {
        for (int rowIndex = 0; rowIndex < this.board.length; rowIndex++) {
            if (candidate == Math.abs(this.board[rowIndex][columnIndex])) {
                return false;
            }
        }
        return true;
    }
    
    //Checks if a given candidate can be inserted into the current subgrid
    private boolean checkSubgrid(int rowIndex, int columnIndex, int candidate) {
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
    
    //Converts all negative numbers (i.e. preallocated numbers) into positive numbers
    private int[][] makePositive() {
        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board.length; j++) {
                this.board[i][j] = Math.abs(this.board[i][j]);
            }
        }
        return this.board;
    }
    //Converts all numbers into negative numbers, needs to be called by the solve-method to work
    private void makeNegative() {
        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board.length; j++) {
                this.board[i][j] = this.board[i][j] *= -1;
            }
        }
    }
    
    public int[][] solve() {
        
        this.makeNegative();
        int position = 0;
        int rowIndex;
        int columnIndex;
        int counter = 0;
        
        while (position < 81) {
            counter++;
            if (position < 0) {
                System.out.println("No solution found!");
                break;
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
                    do {
                        position--;
                    } while (this.board[position / 9][position % 9] < 0);                    
                }
            } else {
                position++;
            }
        }
        System.out.println("Number of loop cycles: " + counter + "\n");
        
        this.makePositive();
        
        return this.board;
    }
}
