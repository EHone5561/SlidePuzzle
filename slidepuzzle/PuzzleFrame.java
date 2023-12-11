package slidepuzzle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

public class PuzzleFrame extends JFrame {

	private SlidePuzzleBoard board;
	private PuzzleButton[][] button_board;
	private JLabel timerLabel;  // 시간을 표시할 라벨
	private Timer timer = new Timer();
	private int time;
	private long startTime;
	private int boardSize;

	public PuzzleFrame(SlidePuzzleBoard b) {
		board = b;
		boardSize = board.getBoardSize();
		button_board = new PuzzleButton[boardSize][boardSize];
		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());

		JPanel p1 = new JPanel(new FlowLayout());
		p1.add(new StartButton(board, this, new PossibleCheck(board)));
		timerLabel = new JLabel("00:00:00");
		p1.add(timerLabel);

		JPanel p2 = new JPanel(new GridLayout(boardSize, boardSize));
		for (int row = 0; row < button_board.length; row++)
			for (int col = 0; col < button_board.length; col++) {
				button_board[row][col] = new PuzzleButton(board, this);
				p2.add(button_board[row][col]);
			}
		cp.add(p1, BorderLayout.NORTH);
		cp.add(p2, BorderLayout.CENTER);

		JPanel buttonPanel = new JPanel(new GridLayout(1, 4));

		JButton upButton = new UpButton(board, this);
		buttonPanel.add(upButton);

		JButton downButton = new DownButton(board, this);
		buttonPanel.add(downButton);

		JButton leftButton = new LeftButton(board, this);
		buttonPanel.add(leftButton);

		JButton rightButton = new RightButton(board, this);
		buttonPanel.add(rightButton);

		cp.add(buttonPanel, BorderLayout.SOUTH);

		update();
		setTitle("Slide Puzzle");
		setSize(250, 300);
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	/** update - 보드 프레임을 갱신함 */
	public void update() {
		PuzzlePiece pp;
		for (int row = 0; row < button_board.length; row++)
			for (int col = 0; col < button_board.length; col++) {
				pp = board.getPuzzlePiece(row, col);
				if (pp != null)
					button_board[row][col].setText(Integer.toString(pp.faceValue()));
				else
					button_board[row][col].setText("");
			}
	}
	public void startTime(){
		startTime = System.currentTimeMillis();
	}

	public void timerSet() {
		time = 0;
		timer.cancel();
		timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {

				timerLabel.setText(String.format("%02d:%02d:%02d", time/3600, (time % 3600) / 60, time % 60));
				time = time + 1;
			}
		}, 100, 1000);
	}

	/** finish - 퍼즐 게임 종료를 표시함 */
	public void finish() {
		button_board[boardSize - 1][boardSize - 1].setText("Done");

		long endTime = System.currentTimeMillis();
		long elapsedTime = (endTime - startTime) / 1000;
		long minutes = elapsedTime / 60;
		long seconds = elapsedTime % 60;

		timer.cancel();
		JOptionPane.showMessageDialog(this, String.format("소요시간 : %02d:%02d:%02d", time/3600, time/60, time%60));
	}
	public PuzzleButton[][] getButton_board() {
		return button_board;
	}
}