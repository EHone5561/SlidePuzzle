package slidepuzzle;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RightButton extends JButton implements ActionListener {
    private SlidePuzzleBoard board;
    private PuzzleFrame frame;
    RightButton(SlidePuzzleBoard b, PuzzleFrame f) {
        super("Right");
        board = b;
        frame = f;
        addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        int row = board.GetEmptyRow();
        int col = board.GetEmptyCol();
        PuzzleButton[][] button_board = frame.getButton_board();
        for(int i = col; i >= 0; i--) {
            button_board[row][i].actionPerformed(e);
        }
    }
}
