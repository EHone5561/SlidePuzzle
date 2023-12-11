package slidepuzzle;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DownButton extends JButton implements ActionListener {
    private SlidePuzzleBoard board;
    private PuzzleFrame frame;
    DownButton(SlidePuzzleBoard b, PuzzleFrame f) {
        super("Down");
        board = b;
        frame = f;
        addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        int row = board.GetEmptyRow();
        int col = board.GetEmptyCol();
        PuzzleButton[][] button_board = frame.getButton_board();
        for(int i = row; i >= 0; i--) {
            button_board[i][col].actionPerformed(e);
        }
    }
}
