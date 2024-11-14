import com.sun.jdi.IntegerType;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class App extends JFrame{
    private JPanel pnlMain;
    private JRadioButton rbCustomer;
    private JRadioButton rbClerk;
    private JRadioButton rbManager;
    private JTextField tfName;
    private JTextArea taPersons;
    private JButton btnSave;
    private JTextField tfAge;
    private JTextField tfMonths;
    private JTextField tfSalary;
    private JButton btnClear;
    private JTextField tfLoad;
    private JButton btnLoad;
    private JButton btnSayHi;
    private JButton btnSavePerson;
    private JButton btnLoadPerson;
    private JButton btnReward;

    private List<Person> persons;

    public App() {
        persons = new ArrayList<>();
        // TODO add implementations for all milestones here

        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if(rbCustomer.isSelected()) {
                        if(tfName.getText().length() == 0 || tfAge.getText().length() == 0)
                            throw new InputMismatchException("No text fields must be empty");
                        String name = tfName.getText();
//                        if(true) {
//                            throw new InputMismatchException("Input must me an integer!");
//                        }
                        int age = Integer.parseInt(tfAge.getText());
                        persons.add(new Customer(name, age));
                        taPersons.append(persons.size() + ". Customer - " + name + "(" + age + ")\n");
                        rbCustomer.setSelected(false);
                    } else if(rbClerk.isSelected()) {
                        if(tfName.getText().length() == 0 || tfAge.getText().length() == 0 || tfMonths.getText().length() == 0 || tfSalary.getText().length() == 0)
                            throw new InputMismatchException("No text fields must be empty");
                        String name = tfName.getText();
//                        if(false) {
//                            throw new InputMismatchException("Input must me an integer!");
//                        }
                        int age = Integer.parseInt(tfAge.getText());
//                        if(false) {
//                            throw new InputMismatchException("Input must me a number!");
//                        }
                        int months = Integer.parseInt(tfMonths.getText());
//                        if(false) {
//                            throw new InputMismatchException("Input must me a number!");
//                        }
                        double salary = Integer.parseInt(tfSalary.getText());
                        persons.add(new Clerk(name, age, months, salary));
                        taPersons.append(persons.size() + ". Clerk - " + name + "(" + age + ")\n");
                        rbClerk.setSelected(false);
                    } else if(rbManager.isSelected()) {
                        if(tfName.getText().length() == 0 || tfAge.getText().length() == 0 || tfMonths.getText().length() == 0 || tfSalary.getText().length() == 0)
                            throw new InputMismatchException("No text fields must be empty");
                        String name = tfName.getText();
//                        if(false) {
//                            throw new InputMismatchException("Input must me an integer!");
//                        }
                        int age = Integer.parseInt(tfAge.getText());
//                        if(false) {
//                            throw new InputMismatchException("Input must me a number!");
//                        }
                        int months = Integer.parseInt(tfMonths.getText());
//                        if(false) {
//                            throw new InputMismatchException("Input must me a number!");
//                        }
                        double salary = Integer.parseInt(tfSalary.getText());
                        persons.add(new Manager(name, age, months, salary));
                        taPersons.append(persons.size() + ". Manager - " + name + "(" + age + ")\n");
                        rbManager.setSelected(false);
                    } else {
                        throw new InputMismatchException("Pick a person type");
                    }
                    btnClear.doClick();
                } catch (InputMismatchException i) {
                    JOptionPane.showMessageDialog(null,i.getMessage());
                }
            }
        });

        rbCustomer.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                tfMonths.setEditable(false);
                tfSalary.setEditable(false);
            }
        });

        rbClerk.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                tfMonths.setEditable(true);
                tfSalary.setEditable(true);
            }
        });

        rbManager.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                tfMonths.setEditable(true);
                tfSalary.setEditable(true);
            }
        });

        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tfName.setText("");
                tfAge.setText("");
                tfMonths.setText("");
                tfSalary.setText("");
                tfLoad.setText("");
            }
        });

        btnLoad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = 1;
                try {
                    if(tfLoad.getText().length() == 0)
                        throw new InputMismatchException("Load must not be empty!");
                    if(Integer.parseInt(tfLoad.getText()) > persons.size())
                        throw new InputMismatchException("That person is not on the list!");
                    for(Person p : persons) {
                        if(i == Integer.parseInt(tfLoad.getText())) {

                            tfName.setText(p.getName());
                            String str = "";
                            tfAge.setText(str += p.getAge());
                            if(p instanceof Customer) {
                                rbCustomer.setSelected(true);
                            } else {
                                if(p instanceof Clerk) {
                                    rbClerk.setSelected(true);
                                } else if(p instanceof Manager) {
                                    rbManager.setSelected(true);
                                }
                                str = "";
                                tfMonths.setText(str += ((Employee)p).getMonths_worked());
                                str = "";
                                tfSalary.setText(str += ((Employee)p).getSalary());
                                break;
                            }
                        }
                        i++;
                    }
                } catch (InputMismatchException ei) {
                    JOptionPane.showMessageDialog(null,ei.getMessage());
                }

            }
        });

        btnSayHi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(Person p : persons) {
                    System.out.println(p.toString());
                }
            }
        });

        btnReward.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = 1;
                try {
                    for(Person p : persons) {
                        if(i == Integer.parseInt(tfLoad.getText())) {
                            if(!(p instanceof Employee))
                                throw new IOException(p.getName() + " is not an employee!");
                            int sal = (int) ((Employee)p).thirteenthMonth();
                            btnLoad.doClick();
                            giveReward(sal, p.getName());
                        }
                        i++;
                    }
                } catch (IOException io) {
                    JOptionPane.showMessageDialog(null,io.getMessage());
                }
            }
        });

        btnSavePerson.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String str = "";
                try(BufferedWriter writer = new BufferedWriter(new FileWriter("PeopleOfTheWorld"))) {
                    for(Person p : persons) {
                        str += p.getName() + "," + p.getAge();
                        if(p instanceof Employee) {
                            str += "," + ((Employee) p).getMonths_worked() + "," + ((Employee) p).getSalary();
                            if(p instanceof Clerk)
                                str += "," + "isClerk";
                        }
                        str += "\n";
                    }
                    writer.write(str);
                } catch (Exception e1) {

                }
                taPersons.setText("");
            }
        });

        btnLoadPerson.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                persons = new ArrayList<>();
                try (BufferedReader reader = new BufferedReader(new FileReader("PeopleOfTheWorld"))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        String[] res = line.split(",");
                        if (res.length == 2) {
                            persons.add(new Customer(res[0], Integer.parseInt(res[1])));
                        } else {
                            if (res.length == 4) {
                                persons.add(new Manager(res[0], Integer.parseInt(res[1]), Integer.parseInt(res[2]), Double.parseDouble(res[3])));
                            } else {
                                System.out.println(res.length);
                                persons.add(new Clerk(res[0], Integer.parseInt(res[1]), Integer.parseInt(res[2]), Double.parseDouble(res[3])));
                            }
                        }
                    }
                } catch (Exception e1) {

                }
                int i = 1;
                for (Person p : persons) {
                    if(p instanceof Clerk) {
                        taPersons.append(i + ". Clerk - " + p.getName() + "(" + p.getAge() + ")\n");
                    } else if(p instanceof Manager) {
                        taPersons.append(i + ". Manager - " + p.getName() + "(" + p.getAge() + ")\n");
                    } else {
                        taPersons.append(i + ". Customer - " + p.getName() + "(" + p.getAge() + ")\n");
                    }
                    i++;
                }
            }
        });
    }

    public static void main(String[] args) {
        // add here how to make GUI visible
        App a = new App();
        a.setContentPane(a.pnlMain);
        a.setSize(new Dimension(700, 500));
        a.setDefaultCloseOperation(EXIT_ON_CLOSE);
        a.setVisible(true);
    }

    static void giveReward(int n, String name) {
        JOptionPane.showMessageDialog(null,n + " - " + name);
    }
}