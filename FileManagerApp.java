import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileManagerApp {
    private static FileManager fileManager = new FileManager();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            displayMainMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    displayFileNames();
                    break;
                case 2:
                    addFile(scanner);
                    break;
                case 3:
                    deleteFile(scanner);
                    break;
                case 4:
                    searchFile(scanner);
                    break;
                case 5:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }

        scanner.close();
        System.out.println("Application closed.");
    }

    private static void displayMainMenu() {
        System.out.println("------ Main Menu ------");
        System.out.println("1. Retrieve file names");
        System.out.println("2. Add a file");
        System.out.println("3. Delete a file");
        System.out.println("4. Search for a file");
        System.out.println("5. Close the application");
        System.out.print("Enter your choice: ");
    }

    private static void displayFileNames() {
        List<String> fileNames = fileManager.getFileNames();

        if (fileNames.isEmpty()) {
            System.out.println("No files found.");
        } else {
            System.out.println("File names:");
            for (String fileName : fileNames) {
                System.out.println(fileName);
            }
        }
    }

    private static void addFile(Scanner scanner) {
        System.out.print("Enter the file name to add: ");
        String fileName = scanner.nextLine();

        try {
            fileManager.addFile(fileName);
            System.out.println("File added successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void deleteFile(Scanner scanner) {
        System.out.print("Enter the file name to delete: ");
        String fileName = scanner.nextLine();

        try {
            fileManager.deleteFile(fileName);
            System.out.println("File deleted successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void searchFile(Scanner scanner) {
        System.out.print("Enter the file name to search: ");
        String fileName = scanner.nextLine();

        boolean found = fileManager.searchFile(fileName);

        if (found) {
            System.out.println("File found.");
        } else {
            System.out.println("File not found.");
        }
    }
}

class FileManager {
    private List<String> fileNames = new ArrayList<>();

    public void addFile(String fileName) {
        if (fileName.isEmpty()) {
            throw new IllegalArgumentException("File name cannot be empty.");
        }

        fileNames.add(fileName);
    }

    public void deleteFile(String fileName) {
        if (fileName.isEmpty()) {
            throw new IllegalArgumentException("File name cannot be empty.");
        }

        if (!fileNames.contains(fileName)) {
            throw new IllegalArgumentException("File not found.");
        }

        fileNames.remove(fileName);
    }

    public boolean searchFile(String fileName) {
        if (fileName.isEmpty()) {
            throw new IllegalArgumentException("File name cannot be empty.");
        }

        return fileNames.contains(fileName);
    }

    public List<String> getFileNames() {
        return new ArrayList<>(fileNames);
    }
}
