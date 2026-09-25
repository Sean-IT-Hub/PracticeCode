package Logic;
import Model.Student;
import static Logic.CreateStudent.studentList;
import java.util.Scanner;

public class CandidateLogic {
    Scanner sc = new Scanner(System.in);
    public void addCandidate() {
        System.out.print("Enter candidate number: ");
        String sNumber = sc.nextLine();
        System.out.print("Enter name: ");
        String name = sc.nextLine();
        System.out.print("Enter course: ");
        String course = sc.nextLine();
        System.out.print("Enter year level: ");
        String yearLevel = sc.nextLine();
        Student candidate = new Student();
        //store the data
        candidate.setStudentNumber(sNumber);
        candidate.setStudentName(name);
        candidate.setCourse(course);
        candidate.setYearLevel(yearLevel);
        //set as the candidate
        candidate.setCandidate(true);
        //add in the arraylist
        studentList.add(candidate);
        AuditLog.auditLog(sNumber, "Add Candidate");
        System.out.println("Candidate added successfully!");
        System.out.println();
    }
}
