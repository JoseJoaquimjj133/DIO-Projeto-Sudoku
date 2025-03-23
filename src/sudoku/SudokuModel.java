package sudoku;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SudokuModel implements Serializable {
 private static final long serialVersionUID = 1L;
 private int[][] board;
 private boolean[][] editableCells;
 private int[][] solution;

 public SudokuModel() {
     board = new int[9][9];
     editableCells = new boolean[9][9];
     solution = new int[9][9];
     generateNewBoard(Difficulty.EASY);
 }

 public void generateNewBoard(Difficulty difficulty) {
     generateFullBoard();
     removeNumbersBasedOnDifficulty(difficulty);
 }

 private boolean generateFullBoard() {
     List<Integer> numbers = new ArrayList<>();
     for (int i = 1; i <= 9; i++) numbers.add(i);
     
     for (int i = 0; i < 9; i++) {
         for (int j = 0; j < 9; j++) {
             if (board[i][j] == 0) {
                 Collections.shuffle(numbers);
                 for (int num : numbers) {
                     if (isValidMove(i, j, num)) {
                         board[i][j] = num;
                         if (generateFullBoard()) {
                             return true;
                         } else {
                             board[i][j] = 0;
                         }
                     }
                 }
                 return false;
             }
         }
     }
     return true;
 }

 private void removeNumbersBasedOnDifficulty(Difficulty difficulty) {
     int cellsToRemove = difficulty.getCellsToRemove();
     for (int i = 0; i < 9; i++) {
         System.arraycopy(board[i], 0, solution[i], 0, 9);
     }

     while (cellsToRemove > 0) {
         int row = (int) (Math.random() * 9);
         int col = (int) (Math.random() * 9);
         if (board[row][col] != 0) {
             board[row][col] = 0;
             editableCells[row][col] = true;
             cellsToRemove--;
         }
     }
 }

 // Resto dos métodos (isValidMove, setCell, isBoardFull, etc.)
 public boolean isValidMove(int row, int col, int value) {
     if (value < 1 || value > 9) return false;
     for (int i = 0; i < 9; i++) {
         if (board[row][i] == value && i != col) return false;
         if (board[i][col] == value && i != row) return false;
     }
     int startRow = 3 * (row / 3);
     int startCol = 3 * (col / 3);
     for (int i = startRow; i < startRow + 3; i++) {
         for (int j = startCol; j < startCol + 3; j++) {
             if (board[i][j] == value && !(i == row && j == col)) return false;
         }
     }
     return true;
 }

 public int[][] getSolution() {
     return solution;
 }

 public int getCell(int row, int col) {
     return board[row][col];
 }

 public boolean isCellEditable(int row, int col) {
     return editableCells[row][col];
 }

 public void setCell(int row, int col, int value) {
     board[row][col] = value;
 }

 public boolean isBoardFull() {
     for (int i = 0; i < 9; i++) {
         for (int j = 0; j < 9; j++) {
             if (board[i][j] == 0) return false;
         }
     }
     return true;
 }
}