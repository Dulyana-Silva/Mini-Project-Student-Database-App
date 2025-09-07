public class Student {
    private int id;
    private String name;
    private int age;
    private String grade;

    public Student(int id, String name, int age, String grade) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.grade = grade;
    }

    public Student(String name, int age, String grade) {
        this(0, name, age, grade);
    }

    // Getters and setters
    public int getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getGrade() { return grade; }

    @Override
    public String toString() {
        return String.format("ID: %d | Name: %s | Age: %d | Grade: %s", id, name, age, grade);
    }
}
