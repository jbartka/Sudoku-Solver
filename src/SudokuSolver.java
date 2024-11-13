import java.util.ArrayDeque;
import java.util.ArrayList;

/**
 *
 * A class that is responsible for solving the Sudoku board passed as a
 * parameter
 *
 * @author Justas Bardauskas
 * @version 1.0 Build 2023.03.05
 */
public class SudokuSolver {
    // The Sudoku puzzle with its methods
    private SudokuPuzzle puzzle;
    // The ArrayDeque of every stored move
    private ArrayDeque<SudokuMove> movesArrayDeque;
    // The SudokuMove of every nextMove (will be re-used for backtracking)
    private SudokuMove nextMove;

    /**
     * SudokuSolver custom constructor. This constructor takes the SudokuPuzzle
     * type variable and initializes it
     *
     * @param puzzle a Sudoku puzzle with all its methods
     */
    public SudokuSolver(SudokuPuzzle puzzle) {
        this.puzzle = puzzle;
        movesArrayDeque = new ArrayDeque<>();
        nextMove = null;
    }

    /**
     * A function that serves as an "expert" who can solve a given SudokuPuzzle
     *
     */
    public void solve() {
        int row = 0;
        int col = 0;
        // Will be handful to determine cases when we backtrack
        boolean traceback = false;

        while(row < puzzle.getNumRows()) {
            while(col < puzzle.getNumCols()) {
                if (this.puzzle.isEmptyCell(row, col)) {
                    // Uses value zero if no value has ever been placed
                    // on the board
                    if (!traceback) {
                        nextMove = placeMove(row, col, 0);
                    }

                    // Checks if next move has any more options (if it does
                    // not, then we know that the cell has the correct value
                    if(nextMove != null) {
                        movesArrayDeque.push(nextMove);
                        puzzle.makeMove(row, col, nextMove.getValue());
                        traceback = false;
                    } else {
                        // Resets the value of the cell as a previous value
                        SudokuMove previousMove = movesArrayDeque.pop();
                        row = previousMove.getRow();
                        col = previousMove.getCol();
                        puzzle.resetMove(row, col);
                        nextMove = placeMove(row, col, previousMove.getValue());
                        // Indicating that in this case we will have to
                        // backtrack in order to find a correct value for a cell
                        traceback = true;
                    }
                }
                // traceback == false means that when we are backtracking, we
                // are staying at the same cell, meaning that the column
                // value will not change
                if(!traceback) {
                    col++;
                }
            }
            // traceback == false means that when we are backtracking, we
            // are staying at the same cell, meaning that the row
            // value will not change
            if(!traceback) {
                row++;
                col = 0;
            }
        }
    }

    /**
     * A method that finds all the possible moves for a cell and determines
     * which one should be placed next
     *
     * @param row the int row of the cell of the sudokuBoard
     * @param col the int column of the cell of the sudokuBoard
     * @param value the value of the cell of the sudokuBoard
     * @return sudokuMove a move that will be made on the cell
     */
    private SudokuMove placeMove(int row, int col, int value) {
        // Renders the values of all possible moves for a cell
        ArrayList<Integer> allPossibleMoves = puzzle.
                getPossibleMovesForACell(row, col);
        SudokuMove sudokuMove = null;
        if (!allPossibleMoves.isEmpty()) {
            for (int i = 0; i < allPossibleMoves.size(); i++) {
                // The next value that will be placed on the cell during
                // backtracking is the next larger value than the current value
                if(allPossibleMoves.get(i) > value) {
                    sudokuMove = new SudokuMove(row, col, allPossibleMoves.
                            get(i));
                    // Break out of the loop once we find and return a
                    // SudokuMove
                    break;
                }
            }
        }
        return sudokuMove;
    }
}
