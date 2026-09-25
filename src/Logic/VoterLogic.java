package Logic;
import Model.Student;
import static Logic.CreateStudent.studentList;
import java.util.Scanner;

public class VoterLogic {
    Scanner sc = new Scanner(System.in);
    public void registerVoter(){
        System.out.print("Enter student number: ");
        String vNumber = sc.nextLine();
        System.out.print("Enter name: ");
        String vName = sc.nextLine();
        System.out.print("Enter course: ");
        String vCourse = sc.nextLine();
        System.out.print("Enter year level: ");
        String vYearLevel = sc.nextLine();
        Student voter = new Student();
        voter.setStudentNumber(vNumber);
        voter.setStudentName(vName);
        voter.setCourse(vCourse);
        voter.setYearLevel(vYearLevel);
        voter.setVoter(true);
        studentList.add(voter);
        AuditLog.auditLog(vNumber, "Register Voter");
        System.out.println("Voter added successfully!");
        System.out.println();
    }
}
