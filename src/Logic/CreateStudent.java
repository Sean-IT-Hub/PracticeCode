package Logic;
import Model.Student;
import java.util.ArrayList;

public class CreateStudent {
    public static ArrayList<Student> studentList = new ArrayList<>();
    Student student = new Student();

    public CreateStudent(String name, String studentNumber, String course, String yearLevel) {
        student.setStudentName(name);
        student.setStudentNumber(studentNumber);
        student.setCourse(course);
        student.setYearLevel(yearLevel);
    }
    public void addStudent() {
        studentList.add(student);
    }
    public ArrayList<Student> viewStudent(){
        return studentList;
    }

}


