package slidepuzzle;

public class PossibleCheck {
    private static int N; // Grid Size
    private SlidePuzzleBoard board;
    public PossibleCheck(SlidePuzzleBoard b){
        board = b;
        N = board.getBoardSize();
    }

    // A utility function to count inversions in given
    // array 'arr[]'. Note that this function can be
    // optimized to work in O(n Log n) time. The idea
    // here is to keep code small and simple.
    private int getInvCount(int[] arr) {
        int invCount = 0;
        for (int i = 0; i < N * N - 1; i++) {
            for (int j = i + 1; j < N * N; j++) {
                // count pairs(i, j) such that i appears
                // before j, but i > j.
                if (arr[j] != 0 && arr[i] != 0 && arr[i] > arr[j]) {
                    invCount++;
                }
            }
        }
        return invCount;
    }

    // find Position of blank from bottom
    private int findXPosition(int[][] puzzle) {
        // start from bottom-right corner of matrix
        for (int i = N - 1; i >= 0; i--) {
            for (int j = N - 1; j >= 0; j--) {
                if (puzzle[i][j] == 0) {
                    return N - i;
                }
            }
        }
        return -1; // Blank position not found (this should not happen in a solvable puzzle)
    }

    // This function returns true if given
    // instance of N*N - 1 puzzle is solvable
    public boolean isSolvable(int[][] puzzle) {
        // Count inversions in given puzzle
        int[] puzzleArray = new int[N * N];
        int k = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                puzzleArray[k++] = puzzle[i][j];
            }
        }

        int invCount = getInvCount(puzzleArray);

        // If grid is odd, return true if inversion
        // count is even.
        if (N % 2 != 0) {
            return invCount % 2 == 0;
        } else { // grid is even
            int pos = findXPosition(puzzle);
            if (pos % 2 != 0) {
                return invCount % 2 == 0;
            } else {
                return invCount % 2 != 0;
            }
        }
    }
}