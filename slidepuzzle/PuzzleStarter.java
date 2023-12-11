package slidepuzzle;

import javax.swing.*;

public class PuzzleStarter {
	public static void main(String[] args) {
		int boardSize = getValidBoardSize();
		new PuzzleFrame(new SlidePuzzleBoard(boardSize));
	}

	private static int getValidBoardSize() {
		while (true) {
			try {
				String input = JOptionPane.showInputDialog("슬라이드 퍼즐 보드의 크기를 입력하세요. " +
						"\nex) 4를 입력할 경우 4x4 크기의 퍼즐 보드가 만들어집니다.");
				// 사용자가 취소 버튼을 누르면 null이 반환되므로 체크
				if (input == null) {
					// 프로그램 종료 또는 다른 처리 가능
					System.exit(0);
				}
				int boardSize = Integer.parseInt(input);
				// 입력값이 2부터 9까지의 숫자인지 확인
				if (boardSize >= 2 && boardSize <= 9) {
					return boardSize;
				} else {
					JOptionPane.showMessageDialog(null, "2부터 9사이의 정수를 입력하세요.");
				}
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "숫자를 입력하세요.");
			}
		}
	}
}