# Sudoku Solver Project

This repository contains a Java-based application for solving Sudoku puzzles. It includes classes for representing Sudoku puzzles, moves, solving logic, and a test interface for interacting with users.

## Features
- **SudokuMove**: Represents a single move in a Sudoku puzzle, with functionality for backtracking.
- **SudokuPuzzle**: Manages the state of a Sudoku board, including loading puzzles from files and validating moves.
- **SudokuSolver**: Implements a backtracking algorithm to solve Sudoku puzzles programmatically.
- **SudokuTest**: Provides a command-line interface for users to input puzzles and view solutions.

## Files Overview
1. **SudokuMove.java**:
   - Represents individual moves on the Sudoku board.
   - Includes methods to retrieve move attributes like value, row, and column.

2. **SudokuPuzzle.java**:
   - Manages the state of the Sudoku board.
   - Includes methods to initialize the board, validate moves, and display the puzzle.

3. **SudokuSolver.java**:
   - Implements a backtracking algorithm to solve Sudoku puzzles.
   - Uses a stack-based approach to store moves and backtrack when necessary.

4. **SudokuTest.java**:
   - Provides a command-line interface for loading puzzles and testing solutions.
   - Supports validation of solutions against provided answers.

## How to Run
1. **Prerequisites**:
   - Java Development Kit (JDK) installed on your system.
   - A text editor or IDE for editing and running Java files.

2. **Compile**:
   Navigate to the directory containing the files and compile the Java files:
   ```bash
   javac *.java
   ```

3. **Run**:
   Execute the test class:
   ```bash
   java SudokuTest
   ```

4. **Provide Input**:
   - Input the filename of the Sudoku puzzle to be solved when prompted.
   - Optionally, input the filename of the expected solution for validation.

## Sample Input Format
The puzzle file should contain a 9x9 Sudoku grid, with each row on a new line and spaces separating numbers. Use `0` for empty cells. Example:
```
5 3 0 0 7 0 0 0 0
6 0 0 1 9 5 0 0 0
0 9 8 0 0 0 0 6 0
8 0 0 0 6 0 0 0 3
4 0 0 8 0 3 0 0 1
7 0 0 0 2 0 0 0 6
0 6 0 0 0 0 2 8 0
0 0 0 4 1 9 0 0 5
0 0 0 0 8 0 0 7 9
```

## Sample Output
When the program runs, it reads the input puzzle file and outputs the solved Sudoku board. If an optional solution file is provided, the solution is validated. Example:

### Starting Puzzle
```
5 3 _ _ 7 _ _ _ _
6 _ _ 1 9 5 _ _ _
_ 9 8 _ _ _ _ 6 _
8 _ _ _ 6 _ _ _ 3
4 _ _ 8 _ 3 _ _ 1
7 _ _ _ 2 _ _ _ 6
_ 6 _ _ _ _ 2 8 _
_ _ _ 4 1 9 _ _ 5
_ _ _ _ 8 _ _ 7 9
```

### Solved Puzzle
```
5 3 4 6 7 8 9 1 2
6 7 2 1 9 5 3 4 8
1 9 8 3 4 2 5 6 7
8 5 9 7 6 1 4 2 3
4 2 6 8 5 3 7 9 1
7 1 3 9 2 4 8 5 6
9 6 1 5 3 7 2 8 4
2 8 7 4 1 9 6 3 5
3 4 5 2 8 6 1 7 9
```

### Validation
- If a solution file is provided and matches the solved puzzle:
  ```
  Solution is correct!
  ```
- If the solution does not match:
  ```
  Solution is NOT correct!
  ```

## Author
This project was created by **Justas Bardauskas**.

## License
This project is licensed under the MIT License
```
