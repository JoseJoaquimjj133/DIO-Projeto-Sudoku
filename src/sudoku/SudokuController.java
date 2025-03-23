package sudoku;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.io.*;

public class SudokuController {
	private SudokuModel model;
	private SudokuView view;
	private JButton hintButton;
	private int hintsLeft = 3;

	public SudokuController(SudokuModel model, SudokuView view) {
		this.model = model;
		this.view = view;
		setupListeners();
		initializeBoard();
	}

	private void setupListeners() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				JTextField cell = view.getCell(i, j);
				cell.getDocument().addDocumentListener(new CellDocumentListener(i, j));
			}
		}

		view.getNewGameButton().addActionListener(e -> {
			model.generateNewBoard(view.getSelectedDifficulty());
			initializeBoard();
			view.resetTimer();
		});

		hintButton = new JButton("Dica (" + hintsLeft + ")");
		hintButton.addActionListener(e -> giveHint());
		view.getControlPanel().add(hintButton);

		JButton saveButton = new JButton("Salvar");
		JButton loadButton = new JButton("Carregar");
		saveButton.addActionListener(e -> saveGame());
		loadButton.addActionListener(e -> loadGame());
		view.getControlPanel().add(saveButton);
		view.getControlPanel().add(loadButton);
	}

	private void initializeBoard() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				int value = model.getCell(i, j);
				boolean editable = model.isCellEditable(i, j);
				view.updateCell(i, j, value, editable);
			}
		}
	}

	private void giveHint() {
		if (hintsLeft > 0) {
			hintsLeft--;
			hintButton.setText("Dica (" + hintsLeft + ")");
		}
	}

	private void saveGame() {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("sudoku_save.dat"))) {
			oos.writeObject(model);
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(view, "Erro ao salvar!");
		}
	}

	private void loadGame() {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("sudoku_save.dat"))) {
			model = (SudokuModel) ois.readObject();
			initializeBoard();
		} catch (IOException | ClassNotFoundException ex) {
			JOptionPane.showMessageDialog(view, "Erro ao carregar!");
		}
	}

	private class CellDocumentListener implements DocumentListener {
		private int row;
		private int col;

		public CellDocumentListener(int row, int col) {
			this.row = row;
			this.col = col;
		}

		@Override
		public void insertUpdate(DocumentEvent e) {
			handleUpdate();
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			handleUpdate();
		}

		@Override
		public void changedUpdate(DocumentEvent e) {
		}

		private void handleUpdate() {
			String text = view.getCell(row, col).getText();
			if (text.isEmpty()) {
				model.setCell(row, col, 0);
				return;
			}
			try {
				int value = Integer.parseInt(text);
				if (model.isValidMove(row, col, value)) {
					model.setCell(row, col, value);
				} else {
					JOptionPane.showMessageDialog(view, "Movimento inválido!");
				}
			} catch (NumberFormatException ex) {
				view.getCell(row, col).setText("");
			}
		}
	}
}