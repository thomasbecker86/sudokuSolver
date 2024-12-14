/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.sudokusolver;

/**
 *
 * @author Thomas
 */
public class SudokuSolver {

    public static void printSodoku(int[][] sudoku) {
        for (int[] row : sudoku) {
            for (int number : row) {
                System.out.print(number);
            }
            System.out.println();
        }
        System.out.println("\n");
    }
    
    //Checks if a given candidate can be inserted into the current row
    private static boolean checkRow(int[][] sudoku, int rowIndex, int candidate) {
        for (int columnIndex = 0; columnIndex < sudoku[rowIndex].length ; columnIndex++) {
            if (candidate == Math.abs(sudoku[rowIndex][columnIndex])) {
                return false;
            }
        }
        return true;
    }
    
    //Checks if a given candidate can be inserted into the current column
    private static boolean checkColumn(int[][] sudoku, int columnIndex, int candidate) {
        for (int rowIndex = 0; rowIndex < sudoku.length; rowIndex++) {
            if (candidate == Math.abs(sudoku[rowIndex][columnIndex])) {
                return false;
            }
        }
        return true;
    }
    
    //Checks if a given candidate can be inserted into the current subgrid
    private static boolean checkSubgrid(int[][] sudoku, int rowIndex, int columnIndex, int candidate) {
        int startRowIndex = (rowIndex / 3) * 3;
        int startColumnIndex = (columnIndex / 3) * 3;
        for (int i = startRowIndex; i < startRowIndex + 3; i++) {
            for (int j = startColumnIndex; j < startColumnIndex + 3; j++) {
                if (candidate == Math.abs(sudoku[i][j])) {
                    return false;
                }
            }            
        }
        return true;
    }
    
    //Converts all negative numbers (i.e. preallocated numbers) into positive numbers
    private static int[][] makePositive(int[][] sudoku) {
        for (int i = 0; i < sudoku.length; i++) {
            for (int j = 0; j < sudoku.length; j++) {
                sudoku[i][j] = Math.abs(sudoku[i][j]);
            }
        }
        return sudoku;
    }
    
    public static int[][] solve(int[][] sudoku) {
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
            
            if (sudoku[rowIndex][columnIndex] >= 0) {
                int candidate = sudoku[rowIndex][columnIndex] + 1;
                while (!checkRow(sudoku, rowIndex, candidate) || !checkColumn(sudoku, columnIndex, candidate) || !checkSubgrid(sudoku, rowIndex, columnIndex, candidate)) {
                    candidate++;
                }
                if (candidate <= 9) {
                    sudoku[rowIndex][columnIndex] = candidate;
                    position++;
                } else {
                    sudoku[rowIndex][columnIndex] = 0;
                    do {
                        position--;
                    } while (sudoku[position / 9][position % 9] < 0);                    
                }
            } else {
                position++;
            }
        }
        System.out.println("Number of loop cycles: " + counter + "\n");
        return sudoku;
    }
    
    public static void main(String[] args) {
        int[][] sudoku = new int[9][9];
        sudoku[0][1] = -3;
        
        sudoku[1][3] = -1;
        sudoku[1][4] = -9;
        sudoku[1][5] = -5;
        
        sudoku[2][2] = -8;
        sudoku[2][7] = -6;
        
        sudoku[3][0] = -8;
        sudoku[3][4] = -6;
        
        sudoku[4][0] = -4;
        sudoku[4][3] = -8;
        sudoku[4][8] = -1;
        
        sudoku[5][4] = -2;
        
        sudoku[6][1] = -6;
        sudoku[6][6] = -2;
        sudoku[6][7] = -8;
        
        sudoku[7][3] = -4;
        sudoku[7][4] = -1;
        sudoku[7][5] = -9;
        sudoku[7][8] = -5;
        
        sudoku[8][7] = -7;

    //    printSodoku(sudoku);
        solve(sudoku);
        makePositive(sudoku);
        printSodoku(sudoku);
        
        int[][] sudokuTwo = new int[9][9];
        sudokuTwo[0][7] = -1;
        
        sudokuTwo[1][0] = -4;
        
        sudokuTwo[2][1] = -2;
        
        sudokuTwo[3][4] = -5;
        sudokuTwo[3][6] = -4;
        sudokuTwo[3][8] = -7;
        
        sudokuTwo[4][2] = -8;
        sudokuTwo[4][6] = -3;
        
        sudokuTwo[5][2] = -1;
        sudokuTwo[5][4] = -9;
        
        sudokuTwo[6][0] = -3;
        sudokuTwo[6][3] = -4;
        sudokuTwo[6][6] = -2;
        
        sudokuTwo[7][1] = -5;
        sudokuTwo[7][3] = -1;
        
        sudokuTwo[8][3] = -8;
        sudokuTwo[8][5] = -6;

    //    printSodoku(sudokuTwo);
        solve(sudokuTwo);
        makePositive(sudokuTwo);
        printSodoku(sudokuTwo);
    }
}
