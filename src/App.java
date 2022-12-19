import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class App {
    private JPanel pnlMain;
    private JRadioButton customerRadioButton;
    private JRadioButton employeeRadioButton;
    private JRadioButton managerRadioButton;
    private JTextField textField1;
    private JTextArea textArea1;
    private JButton SAVEButton;

    public static void main(String[] args) {
        List<Person> personList = new ArrayList<>();
        personList.add(new Clerk("James", 25, 7, 12500));
        giveReward(personList, 1);

    }

    static void giveReward(List<Person> persons, int n) {

    }
}
