package Logic;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class AuditLog {

    public static ArrayList<String> logs = new ArrayList<>();

    public static void auditLog(String studentNum, String action) {

        String dateTime = LocalDateTime.now().toString();

        String log = "\nStudentNum: " + studentNum
                + "\nAction: " + action
                + "\nDateTime: " + dateTime;

        logs.add(log);

        System.out.println("\n" + log);
    }
}