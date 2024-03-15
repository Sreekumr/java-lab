import java.sql.*;
import java.util.Scanner;

class operations {
    Scanner scan = new Scanner(System.in);
    public void addStudent(Statement st) throws SQLException {
        System.out.print("Enter student name: ");
        String name = scan.nextLine();
        System.out.print("Enter student's date of birth (DD-MM-YYYY): ");
        String dob = scan.nextLine();
        System.out.print("Enter student's contact number: ");
        String contactNumber = scan.nextLine();
        System.out.print("Enter student's address: ");
        String address = scan.nextLine();
        System.out.print("Enter enrollment date (YYYY-MM-DD): ");
        String enrollmentDate = scan.nextLine();
        System.out.print("Enter course ID: ");
        int courseId = scan.nextInt();
        System.out.print("Enter teacher ID: ");
        int teacherId = scan.nextInt();
        String sql = String.format(
                "INSERT INTO students (name, dob, contact_number, address, enrollment_date, course_id, teacher_id) " +
                        "VALUES ('%s', '%s', '%s', '%s', '%s', %d, %d)",
                name, dob, contactNumber, address, enrollmentDate, courseId, teacherId);
        st.executeUpdate(sql);
        System.out.println("Student added successfully.");
    }

    public void addTeacher(Statement st) throws SQLException {
        System.out.print("Enter teacher name: ");
        String name = scan.nextLine();
        System.out.print("Enter qualification: ");
        String qualification = scan.nextLine();
        System.out.print("Enter experience: ");
        String experience = scan.nextLine();
        System.out.print("Enter contact number: ");
        String contactNumber = scan.nextLine();
        System.out.print("Enter email: ");
        String email = scan.nextLine();

        String sql = String.format("INSERT INTO teachers (name, qualification, experience, contact_number, email) " +
                "VALUES ('%s', '%s', '%s', '%s', '%s')", name, qualification, experience, contactNumber, email);
        st.executeUpdate(sql);
        System.out.println("Teacher added successfully.");
    }

    public void addCourse(Statement st) throws SQLException {
        System.out.print("Enter course name: ");
        String courseName = scan.nextLine();
        System.out.print("Enter duration (in months): ");
        int duration = scan.nextInt();
        System.out.print("Enter fee: ");
        double fee = scan.nextDouble();

        String sql = String.format("INSERT INTO courses (course_name, duration, fee) " +
                "VALUES ('%s', %d, %.2f)", courseName, duration, fee);
        st.executeUpdate(sql);
        System.out.println("Course added successfully.");
    }

    public void markAttendance(Statement st) throws SQLException {
        System.out.print("Enter student ID: ");
        int studentId = scan.nextInt();
        scan.nextLine();
        System.out.print("Enter date (YYYY-MM-DD): ");
        String date = scan.nextLine();
        System.out.print("Enter status (Present/Absent): ");
        String status = scan.nextLine();

        String sql = String.format("INSERT INTO attendance (student_id, date, status) " +
                "VALUES (%d, '%s', '%s')", studentId, date, status);
        st.executeUpdate(sql);
        System.out.println("Attendance marked successfully.");
    }

    public void enterPerformance(Statement st) throws SQLException {
        System.out.print("Enter student ID: ");
        int studentId = scan.nextInt();
        System.out.print("Enter grade: ");
        String grade = scan.next();
        System.out.print("Enter assignment score: ");
        int assignmentScore = scan.nextInt();
        scan.nextLine();
        System.out.print("Enter feedback: ");
        String feedback = scan.nextLine();

        String sql = String.format("INSERT INTO performance (student_id, grade, assignment_score, feedback) " +
                "VALUES (%d, '%s', %d, '%s')", studentId, grade, assignmentScore, feedback);
        st.executeUpdate(sql);
        System.out.println("Performance entered successfully.");
    }

    public void recordFeesPayment(Statement st) throws SQLException {
        System.out.print("Enter student ID: ");
        int studentId = scan.nextInt();
        System.out.print("Enter amount: ");
        double amount = scan.nextDouble();
        scan.nextLine();
        System.out.print("Enter payment date (YYYY-MM-DD): ");
        String paymentDate = scan.nextLine();
        System.out.print("Enter status (Paid/Unpaid): ");
        String status = scan.nextLine();

        String sql = String.format("INSERT INTO fees (student_id, amount, payment_date, status) " +
                "VALUES (%d, %.2f, '%s', '%s')", studentId, amount, paymentDate, status);
        st.executeUpdate(sql);
        System.out.println("Fees payment recorded successfully.");
    }

    public void addParent(Statement st) throws SQLException {
        System.out.print("Enter parent name: ");
        String name = scan.nextLine();
        System.out.print("Enter contact number: ");
        String contactNumber = scan.nextLine();
        System.out.print("Enter email: ");
        String email = scan.nextLine();
        System.out.print("Enter student ID: ");
        int studentId = scan.nextInt();

        String sql = String.format("INSERT INTO parents (name, contact_number, email, student_id) " +
                "VALUES ('%s', '%s', '%s', %d)", name, contactNumber, email, studentId);
        st.executeUpdate(sql);
        System.out.println("Parent added successfully.");
    }

    public void displayStudentDetails(Statement st) throws SQLException {
        System.out.print("Enter student ID: ");
        int studentId = scan.nextInt();
        scan.nextLine();

        String sql = "SELECT s.name, s.dob, s.contact_number, s.address, s.enrollment_date, c.course_name, f.status " +
                "FROM students s " +
                "JOIN courses c ON s.course_id = c.course_id " +
                "LEFT JOIN fees f ON s.student_id = f.student_id " +
                "WHERE s.student_id = " + studentId;

        ResultSet rs = st.executeQuery(sql);

        if (rs.next()) {
            System.out.println("\nStudent Details:");
            System.out.println("Name: " + rs.getString("name"));
            System.out.println("Date of Birth: " + rs.getString("dob"));
            System.out.println("Contact Number: " + rs.getString("contact_number"));
            System.out.println("Address: " + rs.getString("address"));
            System.out.println("Enrollment Date: " + rs.getString("enrollment_date"));
            System.out.println("Course Name: " + rs.getString("course_name"));
            System.out.println("Fees Status: " + (rs.getString("status") != null ? rs.getString("status") : "Unpaid"));
        } else {
            System.out.println("Student not found!");
        }
    }
}

public class TuitionCenterManagementSystem {

    public static void main(String[] args) throws ClassNotFoundException {
        Connection con;
        Statement st;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            // System.out.println("1");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tuition_center?characterEncoding=utf8",
                    "root", "");
            st = con.createStatement();
            // System.out.println("2");
            Scanner scan = new Scanner(System.in);
            int choice;
            boolean exit = false;
            while (!exit) {
                System.out.println();
                System.out.println("  Tuition Center Management System  ");
                System.out.println("===================================");
                System.out.println("1. Add Student        5. Enter Performance");
                System.out.println("2. Add Teacher        6. Record Fees Payment");
                System.out.println("3. Add Course         7. Add Parent");
                System.out.println("4. Mark Attendance    8. displayStudentDetails");
                System.out.println(" \t \t9. EXIT");
                System.out.println();
                System.out.print("       Enter your choice: ");
                choice = scan.nextInt();
                scan.nextLine();
                operations obj = new operations();

                switch (choice) {
                    case 1:
                        obj.addStudent(st);
                        break;
                    case 2:
                        obj.addTeacher(st);
                        break;
                    case 3:
                        obj.addCourse(st);
                        break;
                    case 4:
                        obj.markAttendance(st);
                        break;
                    case 5:
                        obj.enterPerformance(st);
                        break;
                    case 6:
                        obj.recordFeesPayment(st);
                        break;
                    case 7:
                        obj.addParent(st);
                        break;
                    case 8:
                        obj.displayStudentDetails(st);
                        break;

                    case 9:
                        exit = true;
                        break;
                    default:
                        System.out.println("\n!!!!Invalid choice. Please try again!!!!.");
                }
            }
            // System.out.println("3");
            scan.close();
        } catch (SQLException e) {
            System.out.println("Error" + e);
        }
    }
}
