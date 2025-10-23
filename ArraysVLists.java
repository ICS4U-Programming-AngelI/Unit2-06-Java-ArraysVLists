import java.util.Scanner; // Import Scanner for user input
import java.util.ArrayList; // Import ArrayList to store numbers
import java.util.Arrays; // Import Arrays for sorting
import java.io.File; // Import File class to access files
import java.io.FileNotFoundException; // Import exception handling for missing files

/**
 * This program reads integers from a user-chosen file,
 * calculates and displays the mean and median.
 * It performs input validation and separates logic into functions.
 *
 * Author: Angel
 * Version: 1.0
 * Since: 2025-10-22
 */
public final class ArraysVLists { // Class name matches the filename

    // Private constructor to prevent instantiation
    private ArraysVLists() {
        // Throws exception if someone tries to create an instance
        throw new IllegalStateException("Utility class");
    }

    /**
     * Calculates the mean (average) of an integer array.
     * @param arr the array of integers
     * @return the mean as a double
     */
    public static double calcMean(final int[] arr) {
        double sum = 0; // Initialize sum variable
        for (int num : arr) {  // Loop through each number
            sum += num; // Add number to sum
        }
        return sum / arr.length; // Return sum divided by length
    }

    /**
     * Calculates the median of an integer array.
     * Assumes the array is already sorted.
     * @param arr the sorted array of integers
     * @return the median as a double
     */
    public static double calcMedian(final int[] arr) {
        int size = arr.length; // Get the size of the array
        if (size % 2 == 0) { // If even number of elements
            // Average middle two elements
            return (arr[size / 2 - 1] + arr[size / 2]) / 2.0;
        } else { // If odd number of elements
            return arr[size / 2]; // Return middle element
        }
    }

    /**
     * The entry point of the program.
     * @param args the command line arguments
     */
    public static void main(final String[] args) {
        // Create Scanner for console input
        Scanner scanner = new Scanner(System.in);

        // Ask user to choose a file
        System.out.println("Choose a file to read:");
        System.out.println("1. File 1.txt"); // Option 1
        System.out.println("2. File 2.txt"); // Option 2
        System.out.println("3. File 3.txt"); // Option 3
        System.out.print("Enter choice (1-3): "); // Ask for user input

        int choice; // Variable to store choice
        try {
            // Convert user input to int
            choice = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) { // If input is not an integer
            // Display error message and exit
            System.out.println("Invalid choice.");
            System.out.println("Please enter a number between 1 and 3.");
            scanner.close(); // Close scanner
            return; // Exit program
        }

        // Determine filename based on user's choice
        String filename;
        if (choice == 1) { // If user chose 1
            filename = "File 1.txt"; // Set filename
        } else if (choice == 2) { // If user chose 2
            filename = "File 2.txt"; // Set filename
        } else if (choice == 3) { // If user chose 3
            filename = "File 3.txt"; // Set filename
        } else { // If invalid choice
            // Display error message and exit
            System.out.println("Invalid choice.");
            System.out.println("Please enter a number between 1 and 3.");
            scanner.close(); // Close scanner
            return; // Exit program
        }

        // Create list to hold integers
        ArrayList<Integer> numList = new ArrayList<>();
        // Create File object for chosen filename
        File file = new File(filename);

        // Check if file exists
        if (!file.exists()) {
            System.out.println("No file"); // Print message if file missing
            scanner.close(); // Close scanner
            return; // Exit program
        }

        // Read numbers from file
        try (Scanner fileScanner = new Scanner(file)) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine().trim(); // Trim whitespace
                if (line.isEmpty()) {
                    continue; // Skip empty lines
                }

                try {
                    // Convert line to integer
                    int num = Integer.parseInt(line);
                    numList.add(num); // Add integer to list
                // Handle invalid integers
                } catch (NumberFormatException e) {
                    // Print error message
                    System.out.println("This is an error");
                    scanner.close(); // Close scanner
                    return; // Exit program
                }
            }
        } catch (FileNotFoundException e) { // Handle missing file
            System.out.println("No file"); // Print error message
            scanner.close(); // Close scanner
            return; // Exit program
        }

        // Check if any numbers were read
        if (numList.isEmpty()) {
            // Print message if list empty
            System.out.println("No file");
            scanner.close(); // Close scanner
            return; // Exit program
        }

        // Convert ArrayList to array
        int[] numArray = new int[numList.size()];
        for (int i = 0; i < numList.size(); i++) {
            // Transfer numbers to array
            numArray[i] = numList.get(i);
        }

        // Sort the array before calculations
        Arrays.sort(numArray); // Sort in ascending order

        // Display numbers in file
        System.out.println("Numbers in the file:");

        // Print numbers 10 per line, space-separated
        int numbersPerLine = 10; // Number of numbers per row
        for (int i = 0; i < numArray.length; i++) {
            System.out.print(numArray[i]); // Print number
            if ((i + 1) % numbersPerLine == 0 || i == numArray.length - 1) {
                // Move to next line after 10
                System.out.println();
            } else {
                System.out.print(" "); // Space between numbers
            }
        }

        // Calculate mean of numbers
        double mean = calcMean(numArray);
        // Calculate median of numbers
        double median = calcMedian(numArray);

        // Display mean formatted to 2 decimals
        System.out.printf("Mean: %.2f%n", mean);
        // Display median formatted to 2 decimals
        System.out.printf("Median: %.2f%n", median);

        scanner.close(); // Close scanner
    }
}
