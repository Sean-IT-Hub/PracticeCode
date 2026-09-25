package Model;
import static Logic.CreateStudent.studentList;
import java.util.Scanner;

public class Student {
    Scanner sc = new Scanner(System.in);
    private String studentName;
    private String studentNumber;
    private String course;
    private String yearLevel;
    private boolean isCandidate;
    private boolean isVoter;
    private boolean hasVoted;


    public String getStudentName() {
        return studentName;
    }
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
    public String getStudentNumber(){
        return studentNumber;
    }
    public void setStudentNumber(String studentNumber){
        this.studentNumber = studentNumber;
    }
    public String getCourse(){
        return course;
    }
    public void setCourse(String course){
        this.course = course;
    }
    public String getYearLevel(){
        return yearLevel;
    }
    public void setYearLevel(String yearLevel){
        this.yearLevel = yearLevel;
    }
    public boolean isCandidate(){
        return isCandidate;
    }
    public void setCandidate(boolean isCandidate){
        this.isCandidate = isCandidate;
    }
    public boolean isVoter(){
        return isVoter;
    }
    public void setVoter(boolean isVoter){
        this.isVoter = isVoter;
    }
    public boolean hasVoted() {
        return hasVoted;
    }
    public void setHasVoted(boolean hasVoted) {
        this.hasVoted = hasVoted;
    }

}
