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
public class user_page implements ActionListener {
    
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bookcar) {
            window.setVisible(false);
            gui.h.window.setVisible(true);

        } else if (e.getSource() == checkbook) {
            window.setVisible(false);
            gui.i.window.setVisible(true);

        } else {
            window.setVisible(false);
            gui.a.window.setVisible(true);
        }
    }

    JFrame window;
    Label userpage;
    Button bookcar;
    Button checkbook;
    Button returnhomepage;
    Label footer1;

    public user_page() {
        //GUI
        window = new JFrame();
        window.setSize(800, 500);
        window.setLocation(500, 150);
        window.setVisible(false);

        //Title
        userpage = new Label("User Page");
        Panel userpagebtn = new Panel();
        userpagebtn.setLayout(new FlowLayout(FlowLayout.CENTER, 300, 100));
        userpagebtn.add(userpage);
        window.add(userpagebtn, "North");

        //Middle Contents
        bookcar = new Button("Book Car");
        checkbook = new Button("Check Own Car Booking/s");
        returnhomepage = new Button("Return to homepage");
        bookcar.addActionListener(this);
        checkbook.addActionListener(this);
        returnhomepage.addActionListener(this);
        bookcar.setBackground(Color.blue);
        checkbook.setBackground(Color.blue);
        returnhomepage.setBackground(Color.blue);
        bookcar.setForeground(Color.white);
        checkbook.setForeground(Color.white);
        returnhomepage.setForeground(Color.white);
        Panel twobtns = new Panel();
        twobtns.setLayout(new GridLayout(1, 3, 50, 0));
        twobtns.add(bookcar);
        twobtns.add(checkbook);
        twobtns.add(returnhomepage);
        window.add(twobtns, "Center");

        //Footer
        footer1 = new Label("");
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
