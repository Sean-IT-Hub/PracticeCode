package View;
import Logic.CreateStudent;
import Model.Student;
import java.util.ArrayList;

public class StudentView {
    public void printStudentList(){
        ArrayList<Student> studeViewList = CreateStudent.studentList;
        System.out.println("Student List: ");
        for(Student student: studeViewList){
            if (student.isCandidate()){
                System.out.println("(Candidate)");
            }
            if (student.isVoter()){
                System.out.println("(Voter)");
            }
            System.out.println("Name: " + student.getStudentName());
            System.out.println("Student Number: " + student.getStudentNumber());
            System.out.println("Course: " + student.getCourse());
            System.out.println("Year Level: " + student.getYearLevel());
            System.out.println();
        }
    }
}
