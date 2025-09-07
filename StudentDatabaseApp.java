import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class StudentDatabaseApp {
    public static void main(String[] args) {
        StudentDAO dao = new StudentDAO();
        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to Student Database App");

        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.hasNextInt() ? sc.nextInt() : -1;
            sc.nextLine(); // consume newline

            try {
                switch (choice) {
                    case 1 : {
                        System.out.print("Enter name: ");
                        String name = sc.nextLine();
                        System.out.print("Enter age: ");
                        int age = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter grade: ");
                        String grade = sc.nextLine();
                        dao.addStudent(new Student(name, age, grade));
                        System.out.println("Student added!");
                    }
                    case 2 : {
                        List<Student> students = dao.viewStudents();
                        if (students.isEmpty()) {
                            System.out.println("No students found.");
                        } else {
                            students.forEach(System.out::println);
                        }
                    }
                    case 3 : {
                        System.out.print("Enter student ID: ");
                        int id = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter new name: ");
                        String name = sc.nextLine();
                        System.out.print("Enter new age: ");
                        int age = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter new grade: ");
                        String grade = sc.nextLine();
                        boolean updated = dao.updateStudent(new Student(id, name, age, grade));
                        System.out.println(updated ? "Student updated!" : "Student not found.");
                    }
                    case 4 : {
                        System.out.print("Enter student ID to delete: ");
                        int id = sc.nextInt();
                        boolean deleted = dao.deleteStudent(id);
                        System.out.println(deleted ? "Student deleted!" : "Student not found.");
                    }
                    case 5 : {
                        System.out.println("Exiting...");
                        return;
                    }
                    default : System.out.println("Invalid choice.");
                }
            } catch (SQLException e) {
                System.out.println("Database error: " + e.getMessage());
            }
        }
    }
}

