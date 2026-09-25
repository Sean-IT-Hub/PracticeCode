package Logic;

import Model.Student;
import static Logic.CreateStudent.studentList;
import java.util.Scanner;

public class CastVote {

    Scanner sc = new Scanner(System.in);

    public void castVote() {

        System.out.print("Enter your student number: ");
        String studentNumber = sc.nextLine();

        Student voter = null;

        // Find the voter
        for (Student student : studentList) {
            if (student.getStudentNumber().equals(studentNumber)
                    && student.isVoter()) {

                voter = student;
                break;
            }
        }

        // i ccheck kung voter ung student
        if (voter == null) {
            System.out.println("You are not registered as a voter!");
            return;
        }

        // Check kung naka vote naba ung student
        if (voter.hasVoted()) {
            System.out.println("You have already voted!");
            return;
        }

        // Display ung added candidates
        System.out.println();
        System.out.println("===== CANDIDATES =====");

        int candidateNumber = 1;

        for (Student candidate : studentList) {
            if (candidate.isCandidate()) {
                System.out.println(candidateNumber + ". "
                        + candidate.getStudentName()
                        + " - " + candidate.getCourse());

                candidateNumber++;
            }
        }

        if (candidateNumber == 1) {
            System.out.println("No candidates available.");
            return;
        }

        // Ask voter na mag choose
        System.out.print("Enter the number of canditate to vote: ");
        int choice = sc.nextInt();
        sc.nextLine();

        // Find candidate
        int currentNumber = 1;
        Student selectedCandidate = null;

        for (Student candidate : studentList) {

            if (candidate.isCandidate()) {

                if (currentNumber == choice) {
                    selectedCandidate = candidate;
                    break;
                }

                currentNumber++;
            }
        }

        // if invalid ung choice
        if (selectedCandidate == null) {
            System.out.println("Invalid candidate choice!");
            return;
        }
        // Mark voter as already voted
        voter.setHasVoted(true);

        System.out.println();
        System.out.println("Vote cast successfully!");
        System.out.println("You voted for: "
                + selectedCandidate.getStudentName());
    }
}