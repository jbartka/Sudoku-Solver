import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 *
 * A class that is responsible for prompting the user for the input and
 * displaying the values of the sudoku board and solutions
 *
 * @author Justas Bardauskas
 * @version 1.0 Build 2023.03.05
 */
public class SudokuTest {
    /**
     * Main function which prompts the user for file inputs in order to
     * construct and display the Sudoku boards
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String puzzleFileName;
        String solutionFileName;

        // Using do-while loop to ask the user for the input first and only
        // then handling the errors depending on the input
        do {
            System.out.print("Enter filename of puzzle: ");
            puzzleFileName = sc.nextLine();
            if (readFileAsString(puzzleFileName).equals("File not Found")){
                System.out.println("File not Found. Please try again");
            }
        } while (readFileAsString(puzzleFileName).equals("File not Found"));

        do {
            System.out.print("Enter filename of solution (optional): ");
            solutionFileName = sc.nextLine();
            if (solutionFileName.equals("")){
                solveSudoku(puzzleFileName, solutionFileName);
            } else{
                if (!readFileAsString(solutionFileName).equals("File not " +
                        "Found")){
                    solveSudoku(puzzleFileName, solutionFileName);
                } else {
                    System.out.println("File not Found. Please try again");
                }
            }

        } while (readFileAsString(solutionFileName).equals("File not Found") &&
                !solutionFileName.equals(""));
    }

    /**
     * Reads the Sudoku file as a String. Returns exception if the file is
     * not found. In this class, this method is used to see if the passed file
     * is in the directory
     *
     * @param filename the String of the file
     * @return the file in String format
     */
    private static String readFileAsString(String filename) {
        try {
            return Files.readString(Paths.get(filename));
        } catch (IOException e) {
            return "File not Found";
        }
    }

    /**
     * A helper function that should handle the "Solution is correct!" vs.
     * "Solution is NOT correct!" printing part and returns a boolean that is
     * true if actual matches expected (and false if not).
     *
     * @param actual SudokuPuzzle that is obtained from a solve function.
     * @param expected SudokuPuzzle that is expected from a file
     * @return boolean that is true if actual matches expected (and false if
     * not).
     */
    public static boolean checkSolution(SudokuPuzzle actual,
                                        SudokuPuzzle expected) {
        if (actual.equals(expected)){
            System.out.println("Solution is correct!");
        } else {
            System.out.println("Solution is NOT correct!");
        }
       return actual.equals(expected);
    }

    /**
     * A function that accepts two filenames as parameters - one for the
     * puzzle to solve (puzzleFile), and one that represents the solution file
     * (which could be empty). Solves the puzzle in puzzleFile, and checks
     * whether the solved puzzle matches the solution (only if provided).
     * Returns the solved puzzle from puzzleFile.
     *
     * @param puzzleFile String puzzleFile passed by the user
     * @param solutionFile String solutionFile passed by the user
     */
    public static String solveSudoku(String puzzleFile, String solutionFile) {
        SudokuPuzzle sudokuPuzzle = new SudokuPuzzle(puzzleFile);
        SudokuPuzzle sudokuSolution = new SudokuPuzzle(solutionFile);
        SudokuSolver sudokuSolver = new SudokuSolver(sudokuPuzzle);

        // A function used to initialize both Sudoku boards from file to
        // integer array
        sudokuPuzzle.initializeSudoku();

        System.out.println();
        System.out.println("Starting puzzle:");
        System.out.println(sudokuPuzzle);

        System.out.println();
        sudokuSolver.solve();

        System.out.println("Solved puzzle:");
        System.out.println(sudokuPuzzle);
        System.out.println();

        if (!solutionFile.equals("")) {
            sudokuSolution.initializeSudoku();
            checkSolution(sudokuPuzzle, sudokuSolution);
        }

        return sudokuPuzzle.toString();
    }

}
