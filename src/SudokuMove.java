/**
 *
 * A class that is responsible for setting a single move into Sudoku board
 * in places that are empty. This class is also re-used for backtracking
 * if we need to extract the value, row, col for editing values for
 * specific Sudoku cell
 *
 * @author Justas Bardauskas
 * @version 1.0 Build 2023.03.05
 */
public class SudokuMove {
    // The value of the Sudoku cell
    private int value;
    // The row of the Sudoku cell
    private int row;
    // The column of the Sudoku cell
    private int col;

    /**
     * SudokuMove custom constructor. This constructor takes the value, row,
     * and column variables of Sudoku cell and initializes the variables
     *
     * @param value a value of the cell (or the value that will be pushed
     *              into a cell)
     * @param row a row index of the cell
     * @param col a column index of the cell
     */
    public SudokuMove(int row, int col, int value) {
        this.row = row;
        this.col = col;
        this.value = value;
    }

    /**
     * Gets the value of the cell and returns it (accessible getter method)
     *
     * @return the int of the value of the Sudoku cell
     */
    public int getValue() {
        return value;
    }

    /**
     * Gets the row of the cell and returns it (accessible getter method)
     *
     * @return the int of the row of the Sudoku cell
     */
    public int getRow() {
        return row;
    }

    /**
     * Gets the column of the cell and returns it (accessible getter method)
     *
     * @return the int of the column of the Sudoku cell
     */
    public int getCol() {
        return col;
    }
}
