
/**
 * This is the second project for the semester.
 * Written by : Bhuwan Upadhyaya
 * Date : 4/6/2024
 *
 * This program will prompt the user to enter the name of the file that contains some numbers.
 * It will then convert those numbers into the 2D array.
 * The 2D array is a forest where only 0 is the way through forest where there is
 * no obstacles beside 1,2,3 represents ROCKS, WATER AND VOLCANO RESPECTIVELY.
 *
 *
 * When we run the program the hiker will start his journey from the random position
 * and will be directed towards the south from the path where there is no obstacles.
 * At last it will print out the total number of moves it takes for the safe trip.
 *
 *
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class BUProjectTwo {

    // Constants representing different types of terrain
    private static final int CLEAR = 0;
    private static final int ROCKS = 1;
    private static final int WATER = 2;
    private static final int VOLCANO = 3;

    // Directions
    private static final int NORTH = 0;
    private static final int EAST = 1;
    private static final int SOUTH = 2;
    private static final int WEST = 3;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Enter the file name: ");
        String filename = keyboard.nextLine();
        File file = new File(filename);

        Scanner inputFile = new Scanner(file);
        if (!file.exists()) {
            System.out.println("The file Numbers.txt is not found.");
            System.exit(0);
        }

        // Read the first line to get dimensions
        String[] dimensions = inputFile.nextLine().split(",");  // , is the delimiter
        int rows = Integer.parseInt(dimensions[1]);
        int cols = Integer.parseInt(dimensions[2]);

        // Create a 2D array to store the map contents
        int[][] map = new int[rows][cols];

        // Read the map contents and store them in the 2D array
        for (int i = 0; i < rows; i++) {
            String[] rowValues = inputFile.nextLine().split(",");
            for (int j = 0; j < cols; j++) {
                map[i][j] = Integer.parseInt(rowValues[j]);
            }
        }

        // Print the map calling the printMap method.
        printMap(map);
        System.out.println("  ");

        // Randomly select a starting position
        Random random = new Random();
        int startRow = random.nextInt(rows);
        int startCol = random.nextInt(cols);

        // Attempt to guide the hiker
        guideHiker(map, startRow, startCol);

    }

    // Method to print the map
    private static void printMap(int[][] map) {
        char rowName = 'A';
        System.out.println("The following is the map for forest: \n");
        System.out.print("  ");
        for (int i = 1; i <= map[0].length; i++) {

            System.out.print(i + " ");
        }

        System.out.println();
        for (int i = 0; i < map.length; i++) {
            System.out.print(rowName++ + " ");
            for (int j = 0; j < map[i].length; j++) {
                switch (map[i][j]) {
                    case CLEAR:
                        System.out.print("0 ");
                        break;
                    case ROCKS:
                        System.out.print("1 ");
                        break;
                    case WATER:
                        System.out.print("2 ");
                        break;
                    case VOLCANO:
                        System.out.print("3 ");
                        break;
                }
            }
            System.out.println();
        }
    }

    // Method to guide the hiker through the forest
    private static void guideHiker(int[][] map, int startRow, int startCol) {
        int moves = 0;
        int row = startRow;
        int col = startCol;

        System.out.println("Attempting to start at " + convertToPosition(row, col));
        System.out.println("Map square " + convertToPosition(row, col) + " is empty.");
        System.out.println("Entering the forest at map square " + convertToPosition(row, col));

        while (row > 0) { // Hiker is not in the northernmost row
            // Check if northern move is possible
            if (map[row - 1][col] == CLEAR) {
                row--;
                System.out.println("Moving North to map square " + convertToPosition(row, col));
            } else {
                // Determine the direction to move to find a clear path
                int direction = determineDirection(map, row, col);
                switch (direction) {
                    case EAST:
                        col++;
                        System.out.println("Moving East to map square " + convertToPosition(row, col));
                        break;
                    case WEST:
                        col--;
                        System.out.println("Moving West to map square " + convertToPosition(row, col));
                        break;
                    case SOUTH:
                        row++;
                        System.out.println("Moving South to map square " + convertToPosition(row, col));
                        break;
                    default:
                        System.out.println("No safe path available. Aborting.");
                        return;
                }
            }
            moves++;
        }

        System.out.println("Exiting the forest at map square " + convertToPosition(row, col));
        System.out.println("Safe trip completed in " + moves + " moves.");
    }

    // Method to determine the direction to move to find a clear path
    /**
     * 
     * @param map the 2d array from file
     * @param row 
     * @param col
     * @return  default return is south
     */
    private static int determineDirection(int[][] map, int row, int col) {
        // Check if moving east is possible
        if (col < map[row].length - 1 && map[row][col + 1] == CLEAR) {
            return EAST;
        }
        // Check if moving west is possible
        if (col > 0 && map[row][col - 1] == CLEAR) {
            return WEST;
        }
        // Default to moving south
        return SOUTH;
    }

     
    /**
     * Method to convert row and column to position string
     * @param row row of the 2d array
     * @param col column of the 2d array
     * @return 
     */
    private static String convertToPosition(int row, int col) {
        return (char) ('A' + row) + "-" + (col + 1);
    }
}
