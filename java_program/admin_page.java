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
public class admin_page implements ActionListener {
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == mgcarinfo) {
            window.setVisible(false);
            gui.e.window.setVisible(true);
            
        } else if (e.getSource() == mguserbook) {
            window.setVisible(false);
            gui.f.window.setVisible(true);

        } else if  (e.getSource() == gntreport){
            window.setVisible(false);
            gui.j.window.setVisible(true);
        } 
        else {
            window.setVisible(false);
            gui.a.window.setVisible(true);
        }
    }

    JFrame window;
    Label adminpage;
    Button mgcarinfo;
    Button mguserbook;
    Button gntreport;
    Button returnhomepage;
    Label footer1;

    public admin_page() {
        //GUI
        window = new JFrame();
        window.setSize(900, 500);
        window.setLocation(500, 150);
        window.setVisible(false);

        // Title
        adminpage = new Label("Admin Page");
        Panel adminpagebtn = new Panel();
        adminpagebtn.setLayout(new FlowLayout(FlowLayout.CENTER, 300, 100));
        adminpagebtn.add(adminpage);
        window.add(adminpagebtn, "North");

        // Middle contents
        mgcarinfo = new Button("Manage Car Information");
        mguserbook = new Button("Manage User's Booking");
        gntreport = new Button("Generate Analyzed Reports");
        returnhomepage = new Button("Return to homepage");
        mgcarinfo.addActionListener(this);
        mguserbook.addActionListener(this);
        gntreport.addActionListener(this);
        returnhomepage.addActionListener(this);
        mgcarinfo.setBackground(Color.blue);
        mguserbook.setBackground(Color.blue);
        gntreport.setBackground(Color.blue);
        returnhomepage.setBackground(Color.blue);
        mgcarinfo.setForeground(Color.white);
        mguserbook.setForeground(Color.white);
        gntreport.setForeground(Color.white);
        returnhomepage.setForeground(Color.white);
        Panel twobtns = new Panel();
        twobtns.setLayout(new GridLayout(1, 4, 50, 0));
        twobtns.add(mgcarinfo);
        twobtns.add(mguserbook);
        twobtns.add(gntreport);
        twobtns.add(returnhomepage);
        window.add(twobtns, "Center");
        
        //Footer
        footer1 = new Label("");
        Panel footer = new Panel();
        footer.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 80));
        footer.add(footer1);
        window.add(footer, "South");
        
        //Left and Right margin
        Panel right = new Panel();
        Panel left = new Panel();
        right.setLayout(new FlowLayout(0, 25, 0));
        left.setLayout(new FlowLayout(0, 25, 0));
        window.add(right, "East");
        window.add(left, "West");
    }
}
