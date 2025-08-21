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
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
public class register_page implements ActionListener {

    //First & Last Name Validation
    public static boolean isValidName(String s) {
        String pattern = "^[a-zA-Z\\s]+$";
        return s.matches(pattern);
    }

    //Email Validation
    public static boolean isValidEmail(String s2) {
        return s2.contains("@") && s2.contains(".com");
    }

    //Phone Number Validation
    public static boolean isValidPhoneNum(String s3) {
        String regex = "^[0-9]+$";
        return s3.matches(regex);
    }

    public static boolean isValidPhoneNum2(String s4) {
        int minimum_length = 10;
        int maximum_length = minimum_length + 2;
        return (s4.length() >= minimum_length && s4.length() < maximum_length);
    }

    //Address Validation
    public static boolean isValidEmpty(String s5) {
        return s5.isEmpty();
    }

    public static boolean isValidPassword2(String s8) {
        String regex2 = ".*[a-zA-Z].*";
        String regex3 = ".*[\\W_].*";
        String regex4 = ".*\\d+.*";
        return (s8.matches(regex2) && s8.matches(regex3) && s8.matches(regex4));
    }

    public static boolean isValidPassword3(String s9) {
        int minimum_length = 8;
        int maximum_length = minimum_length + 8;
        return (s9.length() >= minimum_length && s9.length() < maximum_length);
    }

    //Read data from txtfile (User.txt) for validating email, phone number, and username.
    public static void register() {
        ArrayList<String> emails = new ArrayList<>();
        ArrayList<String> phonenums = new ArrayList<>();
        ArrayList<String> usernames = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("User.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Split the line based on the '/' delimiter
                String[] parts = line.split(" / ");

                // Process each part
                if (parts.length >= 7) {
                    String email = parts[2];
                    String phonenum = parts[3];
                    String username = parts[5];
                    emails.add(email);
                    phonenums.add(phonenum);
                    usernames.add(username);
                }
            }

            String[] user_information = new String[7];
            String fname = firstnametf.getText();
            String lname = lastnametf.getText();
            String email = emailtf.getText();
            String pnum = phonenumtf.getText();
            String ads = addressta.getText();
            String uname = usernametf.getText();
            String pword = passwordtf.getText();
            String pword2 = password2tf.getText();

            String checkfname = "0";
            String checklname = "0";
            String checkemail = "0";
            String checkpnum = "0";
            String checkads = "0";
            String checkuname = "0";
            String checkpword = "0";
            String checkpword2 = "0";

            // First Name Validation
            if (fname.equals("Empty input is not acceptable.")) {
                firstnametf.setText("Empty input is not acceptable.");
            } else if (fname.equals("Must contain whitespaces and alphabets only.")) {
                firstnametf.setText("Must contain whitespaces and alphabets only.");
            } else if (isValidEmpty(fname)) {
                firstnametf.setText("Empty input is not acceptable.");
            } else if (!isValidName(fname)) {
                firstnametf.setText("Must contain whitespaces and alphabets only.");
            } else {
                checkfname = "1";
                user_information[0] = fname;
            }

            // Last Name Validation
            if (lname.equals("Empty input is not acceptable.")) {
                lastnametf.setText("Empty input is not acceptable.");
            } else if (lname.equals("Must contain whitespaces and alphabets only.")) {
                lastnametf.setText("Must contain whitespaces and alphabets only.");
            } else if (isValidEmpty(lname)) {
                lastnametf.setText("Empty input is not acceptable.");
            } else if (!isValidName(lname)) {
                lastnametf.setText("Must contain whitespaces and alphabets only.");
            } else {
                checklname = "1";
                user_information[1] = lname;
            }

            // Email Validation
            if (email.equals("Email is registered.")) {
                emailtf.setText("Email is registered.");
            } else if (email.equals("Email must be valid.")) {
                emailtf.setText("Email must be valid.");
            } else if (email.equals("Empty input is not acceptable.")) {
                emailtf.setText("Empty input is not acceptable.");
            } else if (isValidEmpty(email)) {
                emailtf.setText("Empty input is not acceptable.");
            } else if (!isValidEmail(email)) {
                emailtf.setText("Email must be valid.");
            } else {
                for (String eachEmail : emails) {
                    if (email.equals(eachEmail)) {
                        emailtf.setText("Email is registered.");
                    }
                }

                if (!emailtf.getText().equals("Email is registered.")) {
                    checkemail = "1";
                    user_information[2] = email;
                }
            }

            //Phone Num Validation
            if (pnum.equals("Empty input is not acceptable.")) {
                phonenumtf.setText("Empty input is not acceptable.");
            } else if (pnum.equals("Phone number is registered.")) {
                phonenumtf.setText("Phone number is registered.");
            } else if (pnum.equals("Phone number must only contain numbers.")) {
                phonenumtf.setText("Phone number must only contain numbers.");
            } else if (pnum.equals("The length of phone number must be 10 or 11.")) {
                phonenumtf.setText("The length of phone number must be 10 or 11.");
            } else if (isValidEmpty(pnum)) {
                phonenumtf.setText("Empty input is not acceptable.");
            } else if (!isValidPhoneNum(pnum)) {
                phonenumtf.setText("Phone number must only contain numbers.");
            } else if (!isValidPhoneNum2(pnum)) {
                phonenumtf.setText("The length of phone number must be 10 or 11.");
            } else {
                for (String eachPhonenum : phonenums) {
                    if (eachPhonenum.equals(pnum)) {
                        phonenumtf.setText("Phone number is registered.");
                    }
                }

                if (!phonenumtf.getText().equals("Phone number is registered.")) {
                    checkpnum = "1";
                    user_information[3] = pnum;
                }
            }

            // Address Validation
            if (ads.equals("Empty input is not acceptable.")) {
                addressta.setText("Empty input is not acceptable.");
            } else if (isValidEmpty(ads)) {
                addressta.setText("Empty input is not acceptable.");
            } else {
                checkads = "1";
                user_information[4] = ads;
            }

            // Username Validation
            if (uname.equals("Empty input is not acceptable.")) {
                usernametf.setText("Empty input is not acceptable.");
            } else if (uname.equals("Username is registered.")) {
                usernametf.setText("Username is registered.");
            } else if (isValidEmpty(uname)) {
                usernametf.setText("Empty input is not acceptable.");
            } else {
                for (String eachUsername : usernames) {
                    if (eachUsername.equals(uname)) {
                        usernametf.setText("Username is registered.");
                    }
                }

                if (!usernametf.getText().equals("Username is registered.")) {
                    checkuname = "1";
                    user_information[5] = uname;
                }

            }

            // Password Validation
            if (isValidEmpty(pword)) {
                passwordtf2.setText("Password cannot be empty.");
            } else if (!isValidPassword2(pword)) {
                passwordtf2.setText("At least 1 alphabet and symbol and number.");
            } else if (!isValidPassword3(pword)) {
                passwordtf2.setText("The length of password must be 8 to 15.");
            }  else {
                passwordtf2.setText("");
                checkpword = "1";
                user_information[6] = pword;
            }

            // Re-type Password Validation
            if (isValidEmpty(pword2)) {
                password2tf2.setText("Re-typed password cannot be empty.");
            } else if (!pword.equals(pword2)) {
                password2tf2.setText("Re-typed password must be the same.");
            } else {
                password2tf2.setText("");
                checkpword2 = "1";
            }

            // If all the inputs are validated with no problem detected, then it will be inserted into
            // the txtfile.
            if (checkfname.equals("1") && checklname.equals("1") && checkemail.equals("1")
                    && checkpnum.equals("1") && checkads.equals("1") && checkuname.equals("1")
                    && checkpword.equals("1") && checkpword2.equals("1")) {
                try {
                    BufferedWriter writer1 = new BufferedWriter(new FileWriter("User.txt", true));
                    for (int i = 0; i < user_information.length; i++) {
                        writer1.write(user_information[i]);
                        writer1.write(" / ");
                    }
                    writer1.newLine();
                    writer1.close();
                    firstnametf.setText("");
                    lastnametf.setText("");
                    emailtf.setText("");
                    phonenumtf.setText("");
                    addressta.setText("");
                    usernametf.setText("");
                    passwordtf.setText("");
                    password2tf.setText("");
                    JOptionPane.showMessageDialog(window, "Registered Successfully");
                    gui.a.window.setVisible(true);
                    window.setVisible(false);
                } catch (Exception exc) {
                    System.out.println("cant write");
                }
            } else {
                JOptionPane.showMessageDialog(window, "Please ensure all the inputs are correct.");
            }

        } catch (IOException e) {
            System.out.println("Cant Register");
        }
    }

    //Action Listener
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == register) {
            register();

        } else if (e.getSource() == back) {
            window.setVisible(false);
            gui.a.window.setVisible(true);
        }

    }

    static JFrame window;
    Button register;
    Button back;
    Button extrabtn;
    Button extrabtn1;
    Button extrabtn2;
    JButton showpass;
    Label registerpage;
    Label firstname;
    Label lastname;
    Label email;
    Label phonenum;
    Label address;
    Label username;
    Label password;
    Label password2;
    static TextField firstnametf;
    static TextField lastnametf;
    static TextField emailtf;
    static TextField phonenumtf;
    static TextArea addressta;
    static TextField usernametf;
    static JPasswordField passwordtf;
    static JPasswordField password2tf;
    static JTextField passwordtf2;
    static JTextField password2tf2;

    public register_page() {
        // GUI
        window = new JFrame();
        window.setSize(700, 700);
        window.setLocation(500, 70);
        window.setVisible(false);

        //Title
        registerpage = new Label("Register");
        Panel registerpagebtn = new Panel();
        registerpagebtn.setLayout(new FlowLayout(FlowLayout.CENTER, 300, 50));
        registerpagebtn.add(registerpage);
        window.add(registerpagebtn, "North");

        // Middle Contents
        firstname = new Label("First Name: ");
        lastname = new Label("Last Name: ");
        email = new Label("Email Address: ");
        phonenum = new Label("Telephone Number: ");
        address = new Label("Address: ");
        username = new Label("Preffered Username: ");
        password = new Label("Preffered Password: ");
        password2 = new Label("Re-type Preffered Password: ");
        firstnametf = new TextField(10);
        lastnametf = new TextField(10);
        emailtf = new TextField(10);
        phonenumtf = new TextField(10);
        addressta = new TextArea(5, 10);
        usernametf = new TextField(10);
        passwordtf = new JPasswordField(10);
        password2tf = new JPasswordField(10);
        passwordtf2 = new JTextField(10);
        password2tf2 = new JTextField(10);
        passwordtf2.setEditable(false);
        password2tf2.setEditable(false);
        extrabtn = new Button();
        extrabtn1 = new Button();
        extrabtn2 = new Button();
        extrabtn.setVisible(false);
        extrabtn1.setVisible(false);
        extrabtn2.setVisible(false);
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
        Panel registerbtns = new Panel();
        registerbtns.setLayout(new GridLayout(11, 2, 0, 25));
        registerbtns.add(firstname);
        registerbtns.add(firstnametf);
        registerbtns.add(lastname);
        registerbtns.add(lastnametf);
        registerbtns.add(email);
        registerbtns.add(emailtf);
        registerbtns.add(phonenum);
        registerbtns.add(phonenumtf);
        registerbtns.add(address);
        registerbtns.add(addressta);
        registerbtns.add(username);
        registerbtns.add(usernametf);
        registerbtns.add(password);
        registerbtns.add(passwordtf);
        registerbtns.add(extrabtn);
        registerbtns.add(passwordtf2);
        registerbtns.add(extrabtn1);
        registerbtns.add(showpass);
        registerbtns.add(password2);
        registerbtns.add(password2tf);
        registerbtns.add(extrabtn2);
        registerbtns.add(password2tf2);
        
        window.add(registerbtns, "Center");

        // Footer
        register = new Button("Register");
        back = new Button("Back");
        register.addActionListener(this);
        back.addActionListener(this);
        Panel footer = new Panel();
        footer.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 30));
        footer.add(register);
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
