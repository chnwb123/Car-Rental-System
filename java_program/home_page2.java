/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment;

import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

/**
 *
 * @author Chan Jia Zhil
 */
public class home_page2 implements ActionListener{
    
    public void actionPerformed(ActionEvent e){
        if (e.getSource() == login){
            window.setVisible(false);
            gui.b.window.setVisible(true);
        } else if (e.getSource() == register){
            window.setVisible(false);
            gui.c.window.setVisible(true);
        } else {
            System.exit(0);
        }
    }
    
    JFrame window; 
    Button login;
    Button register;
    Button exit;
    Label homepage;
    Label footer1;
    
    public home_page2(){
        //GUI
        window = new JFrame();
        window.setSize(500, 500);
        window.setLocation(500, 150);
        window.setVisible(true);
        
        // Title
        homepage = new Label("CG Car Rental Management System");
        Panel homepagebtn = new Panel();
        homepagebtn.setLayout(new FlowLayout(FlowLayout.CENTER, 300, 100));
        homepagebtn.add(homepage);
        window.add(homepagebtn, "North");
        
        // Middle contents
        login = new Button("Log In");
        register = new Button("Register");
        exit = new Button("Exit");
        login.addActionListener(this);
        register.addActionListener(this);
        exit.addActionListener(this);
        login.setBackground(Color.blue);
        register.setBackground(Color.blue);
        exit.setBackground(Color.blue);
        login.setForeground(Color.white);
        register.setForeground(Color.white);
        exit.setForeground(Color.white);
        Panel twobtns = new Panel();
        twobtns.setLayout(new GridLayout(1, 3, 50, 0));
        twobtns.add(login);
        twobtns.add(register);
        twobtns.add(exit);
        window.add(twobtns, "Center");
        
        // Footer
        footer1 = new Label("Developed by Group 1");
        Panel footer = new Panel();
        footer.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 80));
        footer.add(footer1);
        window.add(footer, "South");
        Panel right = new Panel();
        Panel left = new Panel();
        right.setLayout(new FlowLayout(0, 25, 0));
        left.setLayout(new FlowLayout(0, 25, 0));
        window.add(right, "East");
        window.add(left, "West");
    }
}