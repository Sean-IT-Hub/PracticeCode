package View;
import Logic.CandidateLogic;
import Logic.CastVote;
import Logic.VoterLogic;
import java.util.Scanner;

public class MainView {
    CandidateLogic c = new CandidateLogic();
    VoterLogic v = new VoterLogic();
    CastVote cv = new CastVote();
    public void showMenu(){
        Scanner sc = new Scanner(System.in);
        int choice;
        //loop of the choices
        do {
            System.out.println("1. Add Candidate");
            System.out.println("2. Register Voter");
            System.out.println("3. Cast Vote");
            System.out.println("4. Check Voter Status");
            System.out.println("5. Count Vote");
            System.out.println("6. Display Candidate");
            System.out.println("7. DisplayVoterInfo");
            System.out.println("8. GetWinner");
            System.out.println("9. Display Result");
            System.out.println("10. Exit");
            System.out.println();
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    c.addCandidate();
                    break;
                case 2:
                    v.registerVoter();
                    break;
                case 3:
                    cv.castVote();
                    break;
            }
        } while (choice != 10);
        sc.close();
    }
}
