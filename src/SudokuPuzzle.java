import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * A class that is responsible for particular configuration of a Sudoku puzzle:
 * either a starting configuration, a solved puzzle, or an
 * intermediate configuration. It modifies the Sudoku board and sets/re-sets
 * the values of the Sudoku board.
 *
 * @author Justas Bardauskas
 * @version 1.0 Build 2023.03.05
 */
public class SudokuPuzzle {
    // The file with the stored Sudoku puzzle
    private String puzzleFile;
    // The number of rows in the Sudoku puzzle (final)
    private final int NUMBER_OF_ROWS = 9;
    // The number of columns in the Sudoku puzzle (final)
    private final int NUMBER_OF_COLUMNS = 9;
    // The initialized empty sudokuBoard
    private int[][] sudokuBoard = new int[NUMBER_OF_ROWS][NUMBER_OF_COLUMNS];

    /**
     * SudokuPuzzle custom constructor. This constructor takes the puzzle file
     * (which contains a Sudoku table) and initializes it
     *
     * @param puzzleFile a puzzle file that contains a Sudoku table
     */
    public SudokuPuzzle(String puzzleFile) {
        this.puzzleFile = puzzleFile;
    }

    /**
     * Gets the number of rows in a Sudoku table (accessible getter method)
     *
     * @return the int of the number representing the number of rows in the
     * Sudoku table
     */
    public int getNumRows() {
        return NUMBER_OF_ROWS;
    }

    /**
     * Gets the number of columns in a Sudoku table (accessible getter method)
     *
     * @return the int of the number representing the number of rows in the
     * Sudoku table
     */
    public int getNumCols() {
        return NUMBER_OF_COLUMNS;
    }

    /**
     * Gets the value of a cell at row, column in a Sudoku table (accessible
     * getter method)
     *
     * @param row the int row of the cell of the sudokuBoard
     * @param col the int column of the cell of the sudokuBoard
     * @return the int of the value at the specified Sudoku board location
     */
    public int getValue(int row, int col) {
        return sudokuBoard[row][col];
    }

    /**
     * Gets the value of a cell at row, column in a Sudoku table (accessible
     * getter method)
     *
     * @param row the int row of the cell of the sudokuBoard
     * @param col the int column of the cell of the sudokuBoard
     */
    public void makeMove(int row, int col, int value) {
        sudokuBoard[row][col] = value;
    }

    /**
     * Resets the value of a cell to zero
     *
     * @param row the int row of the cell of the sudokuBoard
     * @param col the int column of the cell of the sudokuBoard
     */
    public void resetMove(int row, int col) {
        sudokuBoard[row][col] = 0;
    }

    /**
     * Checks if the cell is empty
     *
     * @param row the int row of the cell of the sudokuBoard
     * @param col the int column of the cell of the sudokuBoard
     * @return true/false depending on whether the cell is empty or not
     */
    public boolean isEmptyCell(int row, int col) {
        if (sudokuBoard[row][col] == 0){
            return true;
        }
        return false;
    }

    /**
     * Reads the Sudoku file as a String. Returns exception if the file is
     * not found
     *
     * @param filename the String array of the file name
     * @return the file in String format
     */
    public String readFileAsString(String filename){
        Scanner scan;
        String sudokuBoard = "";
        try {
            scan = new Scanner(new File(filename));
        } catch (Exception e) {
            // Failed to read file - good idea to print an error and exit/return
            System.out.println("Failed to read file.");
            return null;
        }
        // While there’s more of the file to read
        while (scan.hasNext()) {
            // Read the next line
            String line = scan.nextLine();
            sudokuBoard += line + "\n";
        }
        scan.close();

        return sudokuBoard;
    }

    /**
     * Takes the row and column and returns all the possible moves for a
     * Sudoku cell.
     *
     * @param row the int row of the cell of the sudokuBoard
     * @param col the int column of the cell of the sudokuBoard
     * @return uniqueValues ArrayList of integer values that are possible
     * moves for a Sudoku cell
     */
    public ArrayList<Integer> getPossibleMovesForACell(int row, int col) {
        ArrayList<Integer> uniqueValues = new ArrayList<>();

        // Populates the ArrayList with values 1-9
        for (int value = 1; value <= 9; value++) {
            uniqueValues.add(value);
        }

        // Remove the moves from the ArrayList that are inapplicable to a cell
        checkColumns(col, uniqueValues);
        checkRows(row, uniqueValues);
        checkBoxes(row, col, uniqueValues);

        return uniqueValues;
    }

    /**
     * A helper method that checks a 3x3 grid and removes the values that are
     * not valid moves for a Sudoku cell
     *
     * @param row the int row of the cell of the sudokuBoard
     * @param col the int column of the cell of the sudokuBoard
     * @param uniqueValues the ArrayList of all possible moves for a cell
     */
    private void checkBoxes(int row, int col, ArrayList<Integer> uniqueValues) {
        // Defines which quadrant on 3x3 grid is the cell located
        int startRow = 3 * (row / 3);
        int startCol = 3 * (col / 3);

        // Iterates through all the numbers in the quadrant and find which
        // ones are valid moves
        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (uniqueValues.contains(sudokuBoard[i][j])){
                    // Removes the value from the ArrayList if it exists and
                    // is not a valid move
                    uniqueValues.remove(uniqueValues.indexOf(sudokuBoard[i][j]));
                }
            }
        }
    }

    /**
     * A helper method that checks the columns and removes the values that are
     * not valid moves for a Sudoku cell
     *
     * @param col the int column of the cell of the sudokuBoard
     * @param uniqueValues the ArrayList of all possible moves for a cell
     */
    private void checkColumns(int col, ArrayList<Integer> uniqueValues) {
        for (int i = 0; i < NUMBER_OF_ROWS; i++) {
            // Removes the value from the ArrayList if it exists and is not a
            // valid move
            if (uniqueValues.contains(sudokuBoard[i][col])){
                uniqueValues.remove(uniqueValues.indexOf(sudokuBoard[i][col]));
            }
        }
    }

    /**
     * A helper method that checks the rows and removes the values that are
     * not valid moves for a Sudoku cell
     *
     * @param row the int row of the cell of the sudokuBoard
     * @param uniqueValues the ArrayList of all possible moves for a cell
     */
    private void checkRows(int row, ArrayList<Integer> uniqueValues) {
        // Checks values in the row and remove them from possibleValues
        for (int i = 0; i < NUMBER_OF_COLUMNS; i++) {
            // Removes the value from the ArrayList if it exists and is not a
            // valid move
            if (uniqueValues.contains(sudokuBoard[row][i])){
                uniqueValues.remove(uniqueValues.indexOf(sudokuBoard[row][i]));
            }
        }
    }

    /**
     * Initializes the sudoku board from file contents into an integer 2-D array
     */
    public void initializeSudoku(){
        String[] numbers = readFileAsString(puzzleFile).replace("\n", " ")
                .split(" ");
        // The index keeps track of how many numbers have been added from the
        // numbers string to a Sudoku board
        int index = 0;
        for (int row = 0; row < NUMBER_OF_ROWS; row++) {
            for (int col = 0; col < NUMBER_OF_COLUMNS; col++) {
                sudokuBoard[row][col] = Integer.parseInt(numbers[index]);
                index++;
            }
        }
    }

    /**
     * A method meant to compare the two objects. Through casting, the program
     * will compare if the two objects are the same
     *
     * @param obj the arbitrary object that will be casted
     * @return true/false depending on whether the two objects are the same
     * or not
     */
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof SudokuPuzzle) {
            SudokuPuzzle sudokuPuzzle = (SudokuPuzzle) obj;
            for (int row = 0; row < sudokuPuzzle.getNumRows(); row++) {
                for (int col = 0; col < sudokuPuzzle.getNumCols(); col++) {
                    if (sudokuPuzzle.getValue(row, col) != this.getValue(row,
                            col)){
                        return false;
                    }
                }
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * Converts the Sudoku board puzzle objects into a String
     *
     * @return sudokuString a String representation of a Sudoku board
     */
    @Override
    public String toString() {
        String sudokuString = "";
        for (int row = 0; row < NUMBER_OF_ROWS; row++) {
            for (int col = 0; col < NUMBER_OF_COLUMNS; col++) {
                if (sudokuBoard[row][col] == 0) {
                    sudokuString += "_";
                } else {
                    sudokuString += sudokuBoard[row][col];
                }
                sudokuString += " ";
            }
            if (row < NUMBER_OF_ROWS - 1) {
                sudokuString += "\n";
            }
        }
        return sudokuString;
    }
}
