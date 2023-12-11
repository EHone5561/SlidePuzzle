package slidepuzzle;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartButton extends JButton implements ActionListener {
	
	private SlidePuzzleBoard board;
	private PuzzleFrame frame;
	private PossibleCheck check;
	private int boardSize;
	
	public StartButton(SlidePuzzleBoard b, PuzzleFrame f, PossibleCheck c) {
		super("Start");
		board = b;
		frame = f;
		check = c;
		boardSize = board.getBoardSize();
		addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		board.createPuzzleBoard();
		while (!check.isSolvable(board.boardCheck())) {
			board.createPuzzleBoard();
		}
		// board.gametest();
		frame.startTime();
		frame.update();
		frame.timerSet();

	}

}