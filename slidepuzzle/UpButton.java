package slidepuzzle;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpButton extends JButton implements ActionListener {
    private SlidePuzzleBoard board;
    private PuzzleFrame frame;
    UpButton(SlidePuzzleBoard b, PuzzleFrame f) {
        super("Up");
        board = b;
        frame = f;
        addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        int row = board.GetEmptyRow();
        int col = board.GetEmptyCol();
        PuzzleButton[][] button_board = frame.getButton_board();
        for(int i = row+1; i <= board.getBoardSize()-1; i++) {
            button_board[i][col].actionPerformed(e);
        }

    }
}
