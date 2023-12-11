package slidepuzzle;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PuzzleButton extends JButton implements ActionListener {

	private SlidePuzzleBoard board;
	private PuzzleFrame frame;
	
	public PuzzleButton(SlidePuzzleBoard b, PuzzleFrame f) {
		board = b;
		frame = f;
		addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (board.gameOn()) {
			String s = getText();
			if (! s.equals("") && board.move(Integer.parseInt(s))) {
				frame.update();
				if (board.gameOver())
					frame.finish();
			}
		}
	}
}