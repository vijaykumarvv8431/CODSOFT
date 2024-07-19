package StudentRegistrationSystem;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Course {
    private String courseCode;
    private String title;
    private String description;
    private int capacity;
    private int enrolled;
    private String schedule;

    public Course(String courseCode, String title, String description, int capacity, String schedule) {
        this.courseCode = courseCode;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.enrolled = 0;
        this.schedule = schedule;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getEnrolled() {
        return enrolled;
    }

    public String getSchedule() {
        return schedule;
    }

    public boolean registerStudent() {
        if (enrolled < capacity) {
            enrolled++;
            return true;
        }
        return false;
    }

    public boolean dropStudent() {
        if (enrolled > 0) {
            enrolled--;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        System.out.println("-------------------------------------------------------------------------------------------------");
        return "Course Code: " + courseCode + "\nTitle: " + title + "\nDescription: " + description
                + "\nCapacity: " + capacity + "\nEnrolled: " + enrolled + "\nSchedule: " + schedule;
    }
}



class Student {
    private String studentID;
    private String name;
    private List<Course> registeredCourses;

    public Student(String studentID, String name) {
        this.studentID = studentID;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public String getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }

    public List<Course> getRegisteredCourses() {
        return registeredCourses;
    }

    public boolean registerForCourse(Course course) {
        if (course.registerStudent()) {
            registeredCourses.add(course);
            return true;
        }
        return false;
    }

    public boolean dropCourse(Course course) {
        if (registeredCourses.remove(course)) {
            course.dropStudent();
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Student ID: " + studentID + ", Name: " + name + ", Registered Courses: " + registeredCourses;
    }
}


class RegistrationSystem {
    private List<Course> courses;
    private List<Student> students;

    public RegistrationSystem() {
        courses = new ArrayList<>();
        students = new ArrayList<>();
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public List<Course> getCourses() {
        return courses;
    }

    public List<Student> getStudents() {
        return students;
    }

    public Course findCourseByCode(String courseCode) {
        for (Course course : courses) {
            if (course.getCourseCode().equalsIgnoreCase(courseCode)) {
                return course;
            }
        }
        return null;
    }

    public Student findStudentByID(String studentID) {
        for (Student student : students) {
            if (student.getStudentID().equalsIgnoreCase(studentID)) {
                return student;
            }
        }
        return null;
    }

    public void displayCourses() {
        for (Course course : courses) {
            System.out.println(course);
        }
    }
    public void displayStudents() {
        for (Student student : students) {
            System.out.println(student);
        }
    }
}
public class StudentRegistration {
    public static void main(String[] args) {
        RegistrationSystem system = new RegistrationSystem();
        Scanner scanner = new Scanner(System.in);

        // Add courses
        system.addCourse(new Course("CS101", "Introduction to Computer Science", "Basics of CS", 30, "MWF 10-11"));
        system.addCourse(new Course("CS102", "Java programming", "Introduction to java", 40, "TTh 11:30-12:30"));
        system.addCourse(new Course("15CS61T", "Software Testing", "Introduction to Software testing", 30, "MTW 9-10"));
        system.addCourse(new Course("15CS62T", "Network Security", "Introduction to Networking", 50, "TTh 12:30-1:30"));

        // Add  students
        system.addStudent(new Student("1001", "lavanya J M "));
        system.addStudent(new Student("1002", "Bhumika"));
        system.addStudent(new Student("1003", "Rajendra"));
        system.addStudent(new Student("1004", "Prathik K S"));
        system.addStudent(new Student("1005", "Pavam A M"));

        while (true) {
            System.out.println("\n1. List of Courses\n2. Register for a Course\n3. Drop a Course\n4. Number of Students\n5. exit");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            if (choice == 1) {
                system.displayCourses();
                System.out.println("-------------------------------------------------------------------------------------------------");
            } else if (choice == 2) {
                System.out.print("Enter Student ID: ");
                String studentID = scanner.nextLine();
                Student student = system.findStudentByID(studentID);
                if (student != null) {
                    System.out.print("Enter Course Code: ");
                    String courseCode = scanner.nextLine();
                    Course course = system.findCourseByCode(courseCode);
                    if (course != null) {
                        if (student.registerForCourse(course)) {
                            System.out.println("Registered successfully.");
                            System.out.println("-------------------------------------------------------------------------------------------------");
                        } else {
                            System.out.println("Registration failed. Course might be full.");
                            System.out.println("-------------------------------------------------------------------------------------------------");
                        }
                    } else {
                        System.out.println("Course not found.");
                        System.out.println("-------------------------------------------------------------------------------------------------");
                    }
                } else {
                    System.out.println("Student not found.");
                    System.out.println("-------------------------------------------------------------------------------------------------");
                }
            } else if (choice == 3) {
                System.out.print("Enter Student ID: ");
                String studentID = scanner.nextLine();
                Student student = system.findStudentByID(studentID);
                if (student != null) {
                    System.out.print("Enter Course Code: ");
                    String courseCode = scanner.nextLine();
                    Course course = system.findCourseByCode(courseCode);
                    if (course != null) {
                        if (student.dropCourse(course)) {
                            System.out.println("Dropped successfully.");
                            System.out.println("-------------------------------------------------------------------------------------------------");
                        } else {
                            System.out.println("Drop failed. Student might not be registered for the course.");
                            System.out.println("-------------------------------------------------------------------------------------------------");
                        }
                    } else {
                        System.out.println("Course not found.");
                        System.out.println("-------------------------------------------------------------------------------------------------");
                    }
                } else {
                    System.out.println("Student not found.");
                    System.out.println("-------------------------------------------------------------------------------------------------");
                }
            } else if (choice == 4) {
                system.displayStudents();
                System.out.println("-------------------------------------------------------------------------------------------------");
            } 
            else if(choice==5){
                break;
            }
            else {
                System.out.println("Invalid choice.");
            }
        }

        scanner.close();
    }
}
