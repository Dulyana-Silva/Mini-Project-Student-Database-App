import java.sql.*;
import java.util.Scanner;

public class StudentDatabaseApp {
    private static final String URL = "jdbc:mysql://localhost:3306/studentdb";
    private static final String USER = "root";
    private static final String PASSWORD = "1234"; 

    private static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    private static void addStudent(Connection conn, Scanner sc) throws SQLException {
        System.out.print("Enter student name: ");
        String name = sc.nextLine();
        System.out.print("Enter age: ");
        int age = sc.nextInt();
        sc.nextLine(); // consume newline
        System.out.print("Enter grade: ");
        String grade = sc.nextLine();

        String sql = "INSERT INTO students (name, age, grade) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, age);
            pstmt.setString(3, grade);
            pstmt.executeUpdate();
            System.out.println("Student added successfully!");
        }
    }

    private static void viewStudents(Connection conn) throws SQLException {
        String sql = "SELECT * FROM students";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("\n--- Student List ---");
            boolean found = false;
            while (rs.next()) {
                found = true;
                System.out.printf("ID: %d | Name: %s | Age: %d | Grade: %s%n",
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("grade"));
            }
            if (!found) {
                System.out.println("No students found.");
            }
        }
    }

    private static void updateStudent(Connection conn, Scanner sc) throws SQLException {
        System.out.print("Enter student ID to update: ");
        int id = sc.nextInt();
        sc.nextLine(); // consume newline
        System.out.print("Enter new name: ");
        String name = sc.nextLine();
        System.out.print("Enter new age: ");
        int age = sc.nextInt();
        sc.nextLine(); // consume newline
        System.out.print("Enter new grade: ");
        String grade = sc.nextLine();

        String sql = "UPDATE students SET name=?, age=?, grade=? WHERE id=?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, age);
            pstmt.setString(3, grade);
            pstmt.setInt(4, id);
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Student updated successfully!");
            } else {
                System.out.println("No student found with that ID.");
            }
        }
    }

    private static void deleteStudent(Connection conn, Scanner sc) throws SQLException {
        System.out.print("Enter student ID to delete: ");
        int id = sc.nextInt();
        sc.nextLine(); // consume newline

        String sql = "DELETE FROM students WHERE id=?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Student deleted successfully!");
            } else {
                System.out.println("No student found with that ID.");
            }
        }
    }

    public static void main(String[] args) {
        try (Connection conn = connect(); Scanner sc = new Scanner(System.in)) {
            System.out.println("Welcome to Student Database App");

            while (true) {
                System.out.println("\nChoose an option:");
                System.out.println("1. Add Student");
                System.out.println("2. View Students");
                System.out.println("3. Update Student");
                System.out.println("4. Delete Student");
                System.out.println("5. Exit");
                System.out.print("Enter choice: ");

                int choice;
                String line = sc.nextLine().trim();
                try {
                    choice = Integer.parseInt(line);
                } catch (NumberFormatException nfe) {
                    System.out.println("Please enter a valid number (1-5).");
                    continue;
                }

                try {
                    switch (choice) {
                        case 1:
                            addStudent(conn, sc);
                            break;
                        case 2:
                            viewStudents(conn);
                            break;
                        case 3:
                            updateStudent(conn, sc);
                            break;
                        case 4:
                            deleteStudent(conn, sc);
                            break;
                        case 5:
                            System.out.println("Exiting program...");
                            return;
                        default:
                            System.out.println("Invalid choice, try again.");
                            break;
                    }
                } catch (SQLException e) {
                    System.out.println("Database error: " + e.getMessage());
                }
            }
        } catch (SQLException e) {
            System.out.println("Failed to connect to database: " + e.getMessage());
        }
    }
}
