import java.io.*;
import java.util.*;

public class Main {
    static StudentBST database = new StudentBST();

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter Database Filename: ");
        String filename = input.nextLine();

        File file = new File(filename);
        if (!file.exists()) {
            System.out.println("*** Error: File Not Found. ***");
            return;
        }

        try (Scanner fileScanner = new Scanner(file)) {
            while (fileScanner.hasNextLine()) {
                String[] parts = fileScanner.nextLine().trim().split(" ");
                if (parts.length >= 4) {
                    int id = Integer.parseInt(parts[0]);
                    String name = parts[1] + " " + parts[2];
                    double grade = Double.parseDouble(parts[3]);
                    database.insert(new Student(id, name, grade));
                }
            }
        } catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
            return;
        }

        while (true) {
            System.out.println("Select an action:");
            System.out.println("1. Register New Student");
            System.out.println("2. Display Existing Student Record");
            System.out.println("3. Update Existing Student Record");
            System.out.println("4. Display Student with Highest Grade");
            System.out.println("5. Display Student with Lowest Grade");
            System.out.println("6. Exit");
            System.out.print("Enter Choice #: ");

            int choice = Integer.parseInt(input.nextLine());

            switch (choice) {
                case 1 -> registerNewStudent(input);
                case 2 -> displayStudent(input);
                case 3 -> updateStudent(input);
                case 4 -> System.out.println(database.findHighestGrade());
                case 5 -> System.out.println(database.findLowestGrade());
                case 6 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }

    static void registerNewStudent(Scanner input) {
        System.out.print("Enter Student ID: ");
        int id = Integer.parseInt(input.nextLine());
        System.out.print("Enter Student Name: ");
        String name = input.nextLine();
        System.out.print("Enter Student Grade: ");
        double grade = Double.parseDouble(input.nextLine());
        database.insert(new Student(id, name, grade));
    }

    static void displayStudent(Scanner input) {
        System.out.print("Enter Student ID: ");
        int id = Integer.parseInt(input.nextLine());
        Student s = database.search(id);
        if (s == null) {
            System.out.println("*** Error - Student ID Not Found! ***");
        } else {
            System.out.println(s);
        }
    }

    static void updateStudent(Scanner input) {
        System.out.print("Enter Student ID: ");
        int id = Integer.parseInt(input.nextLine());
        Student s = database.search(id);
        if (s == null) {
            System.out.println("*** Error - Student ID Not Found! ***");
            return;
        }
        System.out.println(s);
        System.out.print("Enter New Grade: ");
        double newGrade = Double.parseDouble(input.nextLine());
        s.setGrade(newGrade);
    }
}
