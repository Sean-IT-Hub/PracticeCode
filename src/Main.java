import Model.Student;
import View.MainView;
import View.StudentView;

public class Main {
    public static void main(String[] args) {
        StudentView test = new StudentView();
        MainView menu = new MainView();
        menu.showMenu();
        test.printStudentList();
    }
}