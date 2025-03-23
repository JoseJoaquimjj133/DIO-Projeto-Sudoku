package sudoku;

import javax.swing.SwingUtilities;



public class MainApp {
 public static void main(String[] args) {
     SwingUtilities.invokeLater(() -> {
         SudokuModel model = new SudokuModel();
         SudokuView view = new SudokuView();
         new SudokuController(model, view);
         view.setVisible(true);
     });
 }
}