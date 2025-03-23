package sudoku;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.Border;

public class SudokuView extends JFrame {
private static final long serialVersionUID = 1L;

private JTextField[][] cells;
 private JButton newGameButton;
 private JComboBox<Difficulty> difficultyComboBox;
 private JPanel controlPanel;
 private JLabel timerLabel;
 private Timer timer;
 private int elapsedTime = 0;

 public SudokuView() {
     initializeUI();
 }

 private void initializeUI() {
     setTitle("Sudoku");
     setLayout(new BorderLayout());

   
     JPanel boardPanel = new JPanel(new GridLayout(9, 9));
     cells = new JTextField[9][9];
     Border border = BorderFactory.createLineBorder(Color.GRAY, 1);

     for (int i = 0; i < 9; i++) {
         for (int j = 0; j < 9; j++) {
             cells[i][j] = new JTextField();
             cells[i][j].setHorizontalAlignment(JTextField.CENTER);
             cells[i][j].setFont(new Font("Arial", Font.BOLD, 20));
             cells[i][j].setBorder(border);
             boardPanel.add(cells[i][j]);
         }
     }

     controlPanel = new JPanel();
     difficultyComboBox = new JComboBox<>(Difficulty.values());
     newGameButton = new JButton("Novo Jogo");
     timerLabel = new JLabel("Tempo: 00:00");

     controlPanel.add(new JLabel("Dificuldade:"));
     controlPanel.add(difficultyComboBox);
     controlPanel.add(newGameButton);
     controlPanel.add(timerLabel);

     add(boardPanel, BorderLayout.CENTER);
     add(controlPanel, BorderLayout.SOUTH);

     setSize(600, 600);
     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     startTimer();
 }

 public JPanel getControlPanel() {
     return controlPanel;
 }

 public Difficulty getSelectedDifficulty() {
     return (Difficulty) difficultyComboBox.getSelectedItem();
 }

 private void startTimer() {
     timer = new Timer(1000, e -> {
         elapsedTime++;
         int minutes = elapsedTime / 60;
         int seconds = elapsedTime % 60;
         timerLabel.setText(String.format("Tempo: %02d:%02d", minutes, seconds));
     });
     timer.start();
 }

 public void resetTimer() {
     elapsedTime = 0;
     timerLabel.setText("Tempo: 00:00");
 }

 public void updateCell(int row, int col, int value, boolean editable) {
     cells[row][col].setText(value == 0 ? "" : String.valueOf(value));
     cells[row][col].setEditable(editable);
     cells[row][col].setBackground(editable ? Color.WHITE : Color.LIGHT_GRAY);
 }

 public JButton getNewGameButton() {
     return newGameButton;
 }

 public JTextField getCell(int row, int col) {
     return cells[row][col];
 }
}