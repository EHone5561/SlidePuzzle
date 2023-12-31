package slidepuzzle;

import java.util.Arrays;
import java.util.Random;

/** SlidePuzzleBoard models a slide puzzle. */ 
public class SlidePuzzleBoard { 
	private PuzzlePiece[][] board;
	
	// 빈칸의 좌표 
	private int empty_row;
	private int empty_col;
	// representation invariant: board[empty_row][empty_col] == null
	
	private boolean game_on = false;
	private int boardSize;

 public SlidePuzzleBoard(int s_boardSize) {
	 boardSize = s_boardSize;
	 board = new PuzzlePiece[boardSize][boardSize];
	 // 퍼즐 조각 1~(boardSize * boardSize - 1)를 보드에 순서대로 끼우기
	 int number = 1;
	 for (int row = 0; row < boardSize; row++) {
		 for (int col = 0; col < boardSize; col++) {
			 if (col != boardSize - 1 || row != boardSize - 1) {
				 board[row][col] = new PuzzlePiece(number);
				 number += 1;
			 } else {
				 board[boardSize - 1][boardSize - 1] = null;
				 empty_row = boardSize - 1;
				 empty_col = boardSize - 1;
			 }
		 }
	 }
 }

	/** getPuzzlePiece - 퍼즐 조각을 리턴 
	 * @param row - 가로줄 인덱스 
	 * @param col - 세로줄 인덱스 
     * @return 퍼즐 조각  */
	public PuzzlePiece getPuzzlePiece(int row, int col) { 
		return board[row][col];
	}

	/** gameOn - 게임이 진행중인지 점검하는 함수 
     * @return 게임이 진행중이면 true, 끝났으면 false  */
	public boolean gameOn() {
		return game_on;
	}
	
	/** 이동이 가능하면, 퍼즐 조각을 빈칸으로 이동 
	 * @param w - 이동하기 원하는 퍼즐 조각 
	 * @return 이동 성공하면 true를 리턴하고, 이동이 불가능하면 false를 리턴 */
	public boolean move(int w) { 
		int row, col; // w의 위치 
		// 빈칸에 주변에서 w의 위치를 찾음 
		if (found(w, empty_row - 1, empty_col)) { // 좌
			row = empty_row - 1;
			col = empty_col;
		}
		else if (found(w, empty_row + 1, empty_col)) { // 우
			row = empty_row + 1;
			col = empty_col;
		}
		else if (found(w, empty_row, empty_col - 1)) { // 하
			row = empty_row;
			col = empty_col - 1;
		}
		else if (found(w, empty_row, empty_col + 1)) { // 상
			row = empty_row;
			col = empty_col + 1;
		}
		else
			return false;
		// w를 빈칸에 복사
		board[empty_row][empty_col] = board[row][col];
		// 빈칸 위치를 새로 설정하고, w를 제거
		empty_row = row;
		empty_col = col;
		board[empty_row][empty_col] = null;
		return true;
	}
	
	/** found - board[row][col]에 퍼즐 조각 v가 있는지 확인  
	 * @param v - 확인할 수 
	 * @param row - 보드의 가로줄 인덱스 
	 * @param col - 보드의 세로줄 인덱스 
	 * @return 있으면 true, 없으면 false */
	private boolean found(int v, int row, int col) {
		if (row >= 0 && row <= boardSize - 1 && col >= 0 && col <= boardSize - 1)
			return board[row][col].faceValue() == v;
		else
			return false;
	}

	public void moveUp(int w) {
		// 상 버튼을 눌렀을 때의 동작 구현
		int row, col; // w의 위치
		// 빈칸에 주변에서 w의 위치를 찾음
		while (!(empty_col == boardSize - 1)) {
			if (found(w, empty_row, empty_col - 1)) { // 상 (빈칸이 맨 아래로 이동)
				row = empty_row;
				col = empty_col - 1;
			}
		}
	}

	public void moveDown(int w) {
		// 상 버튼을 눌렀을 때의 동작 구현
		int row, col; // w의 위치
		// 빈칸에 주변에서 w의 위치를 찾음
		while (!(empty_col == 0)){
			if (found(w, empty_row, empty_col + 1)) { // 하 (빈칸이 맨 위로 이동)
				row = empty_row;
				col = empty_col + 1;
			}
		}
	}

	public void moveLeft(int w) {
		// 좌 버튼을 눌렀을 때의 동작 구현
		int row, col; // w의 위치
		// 빈칸에 주변에서 w의 위치를 찾음
		while (!(empty_row == boardSize - 1)){
			if (found(w, empty_row + 1, empty_col)) { // 좌 (빈칸이 맨 오른쪽으로 이동)
				row = empty_row + 1;
				col = empty_col;
			}
		}
	}

	public void moveRight(int w) {
		// 우 버튼을 눌렀을 때의 동작 구현
		int row, col; // w의 위치
		// 빈칸에 주변에서 w의 위치를 찾음
		while (!(empty_row == 0)){
			if (found(w, empty_row - 1, empty_col)) { // 우 (빈칸이 맨 왼쪽으로 이동)
				row = empty_row - 1;
				col = empty_col;
			}
		}
	}
	
	/** createPuzzleBoard - 퍼즐 게임 초기 보드 생성 */
	public void createPuzzleBoard() {
		int[] numbers = generateRandomPermutation(boardSize * boardSize - 1);
		int i = 0;
		for (int row = 0; row < boardSize; row++) {
			for (int col = 0; col < boardSize; col++) {
				if (col != boardSize - 1 || row != boardSize - 1) {
					board[row][col] = new PuzzlePiece(numbers[i] + 1);
					i += 1;
				} else {
					board[boardSize - 1][boardSize - 1] = null;
					empty_row = boardSize - 1;
					empty_col = boardSize - 1;
				}
			}
		}
		game_on = true;
	}
	public void gametest() {
		game_on = true;
	}
	
	/** generateRandomPermutation - 0~n-1 범위의 정수 수열을 무작위로 섞은 배열을 리턴 한다.
	 * @param n - 수열의 길이 
	 * @return 0~n-1 범위의 정수를 무작위로 섞어 만든 배열 
	 */
	private int[] generateRandomPermutation(int n) {
		Random random = new Random();
	    int[] permutation = new int[n];
	    for (int i = 0; i < n; i++) {
	        int d = random.nextInt(i+1);
	        permutation[i] = permutation[d];
	        permutation[d] = i;
	    }
	    return permutation;
	}
	
	/** gameOver - 퍼즐 게임이 끝났는지를 확인  
	 * @return 목표를 달성했으면 true, 아직 더 진행해야 하면 false 
	 */
	public boolean gameOver() {
		if (empty_row != boardSize - 1 || empty_col != boardSize - 1)
			return false;
		else {
			int number = 1;
			for (int row = 0; row < boardSize; row++) {
				for (int col = 0; col < boardSize; col++) {
					if (col != boardSize - 1 || row != boardSize - 1) {
						if (board[row][col].faceValue() != number)
							return false;
						else
							number += 1;
					}
				}
			}
			game_on = false;
			return true;
		}
	}
	public int getBoardSize() {
		return boardSize;
	}

	// PossibleCheck에서 보드의 완성 가능성을 확인하기 위해 퍼즐보드의 값을 리턴
	public int[][] boardCheck() {
		int[][] board_arr = new int[boardSize][boardSize];
		for (int row = 0; row < boardSize; row++) {
			for (int col = 0; col < boardSize; col++) {
				if (board[row][col] == null) {
					board_arr[row][col] = 0;
				} else {
					board_arr[row][col] = board[row][col].faceValue();
				}
			}
		}
		return board_arr;
	}

	public int GetEmptyRow() {
		return empty_row;
	}
	public int GetEmptyCol() {
		return empty_col;
	}
}
	
