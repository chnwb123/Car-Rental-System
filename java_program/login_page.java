/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Chan Jia Zhil
 */
public class login_page implements ActionListener {

    //Read data from txtfile (User.txt) for validating username and password.
    public void login() {
        ArrayList<String> usernames = new ArrayList<>();
        ArrayList<String> passwords = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("User.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Split the line based on the '/' delimiter
                String[] parts = line.split(" / ");

                // Process each part
                if (parts.length >= 7) {
                    String username = parts[5];
                    String password = parts[6];
                    usernames.add(username);
                    passwords.add(password);
                }
            }

            String fetchedUsername = usernametf.getText();
            String fetchedPassword = passwordtf.getText();

            String checkFetchedUsername = "0";
            String checkFetchedPassword = "0";

            //Username Validation
            if (fetchedUsername.equals("Empty input is not acceptable.")) {
                usernametf.setText("Empty input is not acceptable.");
            } else if (register_page.isValidEmpty(fetchedUsername)) {
                usernametf.setText("Empty input is not acceptable.");
            } else {
                for (String eachUsername : usernames) {
                    if (fetchedUsername.equals(eachUsername)) {
                        checkFetchedUsername = "1";
                    }
                }

                if (!checkFetchedUsername.equals("1")) {
                    usernametf.setText("Username is not found.");
                }
            }

            //Password Validation
            if (register_page.isValidEmpty(fetchedPassword)) {
                passwordtf2.setText("Empty input is not acceptable.");
            } else {
                for (String eachPassword : passwords) {
                    if (fetchedPassword.equals(eachPassword)) {
                        passwordtf2.setText("");
                        checkFetchedPassword = "1";
                    }
                }

                if (!checkFetchedPassword.equals("1")) {
                    passwordtf2.setText("Password is not found.");
                }
            }

            if (checkFetchedUsername.equals("1") && checkFetchedPassword.equals("1")) {
                if (fetchedUsername.equals("admin") && fetchedPassword.equals("admin")) {
                    JOptionPane.showMessageDialog(window, "Hi Admin!");
                    gui.g.window.setVisible(true);
                    window.setVisible(false);
                } else {
                    int usernameIndexCounter = -1;
                    String check = "0";
                    
                    for (String eachUsername2 : usernames){
                        usernameIndexCounter++;
                        if (eachUsername2.equals(fetchedUsername)){
                            break;
                        }
                    }
                    
                    if (passwords.get(usernameIndexCounter).equals(fetchedPassword)){
                        check = "1";
                    }
        
                    if(check.equals("1")){
                        Customer customer = new Customer(fetchedUsername);
                        JOptionPane.showMessageDialog(window, "Hi " + fetchedUsername + "!");
                        gui.d.window.setVisible(true);
                        window.setVisible(false);
                    } else {
                        passwordtf2.setText("Username & Password doesn't matched.");
                    }
             
                }
            }
        } catch (Exception e) {
            System.out.println("Cant Log In");
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == login) {
            login();
        } else {
            window.setVisible(false);
            gui.a.window.setVisible(true);
        }
    }

    JFrame window;
    Button login;
    Button back;
    Button extrabtn;
    Button extrabtn1;
    JButton showpass;
    Label loginpage;
    Label username;
    Label password;
    TextField usernametf;
    JPasswordField passwordtf;
    JTextField passwordtf2;

    public login_page() {
        // GUI
        window = new JFrame();
        window.setSize(555, 431);
        window.setLocation(500, 150);
        window.setVisible(false);

        //Title
        loginpage = new Label("Log In");
        Panel loginpagebtn = new Panel();
        loginpagebtn.setLayout(new FlowLayout(FlowLayout.CENTER, 300, 50));
        loginpagebtn.add(loginpage);
        window.add(loginpagebtn, "North");

        //Middle Contents
        username = new Label("Username: ");
        password = new Label("Password: ");
        usernametf = new TextField();
        passwordtf = new JPasswordField();
        passwordtf2 = new JTextField();
        passwordtf2.setEditable(false);
        extrabtn = new Button();
        extrabtn1 = new Button();
        showpass = new JButton("Show Password");
        showpass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (passwordtf.getEchoChar() == 0) {
                    passwordtf.setEchoChar('•');
                    showpass.setText("Show Password");
                } else {
                    passwordtf.setEchoChar((char) 0);
                    showpass.setText("Hide Password");
                }
            }
        });
        extrabtn.setVisible(false);
        extrabtn1.setVisible(false);
        Panel loginbtns = new Panel();
        loginbtns.setLayout(new GridLayout(4, 2, 0, 25));
        loginbtns.add(username);
        loginbtns.add(usernametf);
        loginbtns.add(password);
        loginbtns.add(passwordtf);
        loginbtns.add(extrabtn);
        loginbtns.add(passwordtf2);
        loginbtns.add(extrabtn1);
        loginbtns.add(showpass);
        window.add(loginbtns, "Center");

        //Footer
        login = new Button("Log In");
        back = new Button("Back");
        login.addActionListener(this);
        back.addActionListener(this);
        Panel footer = new Panel();
        footer.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 53));
        footer.add(login);
        footer.add(back);
        window.add(footer, "South");
        Panel right = new Panel();
        Panel left = new Panel();
        right.setLayout(new FlowLayout(0, 25, 0));
        left.setLayout(new FlowLayout(0, 25, 0));
        window.add(right, "East");
        window.add(left, "West");
    }

}
