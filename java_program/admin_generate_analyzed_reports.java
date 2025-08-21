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
public class admin_generate_analyzed_reports implements ActionListener {
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == gadpr){
            window.setVisible(false);
            gui.k.window.setVisible(true);
        } else if (e.getSource() == gmp){
            window.setVisible(false);
            gui.l.window.setVisible(true);
        } else{
            window.setVisible(false);
            gui.g.window.setVisible(true);
        }
    }
    
    JFrame window;
    Label admingarpage;
    Label footer1;
    Button gadpr;
    Button gmp;
    Button back;
    
    public admin_generate_analyzed_reports(){
        //GUI
        window = new JFrame();
        window.setSize(1350, 500);
        window.setLocation(200, 150);
        window.setVisible(false);

        // Title
        admingarpage = new Label("Admin Generate Analyzed Reports Page");
        Panel admingarpagebtn = new Panel();
        admingarpagebtn.setLayout(new FlowLayout(FlowLayout.CENTER, 300, 100));
        admingarpagebtn.add(admingarpage);
        window.add(admingarpagebtn, "North");
        
        //Middle Contents
        gadpr = new Button("Generate Analyzed Overall Approved & Declined Percentage Report");
        gmp = new Button("Generate Monthly Report");
        back = new Button("Back");
        gadpr.addActionListener(this);
        gmp.addActionListener(this);
        back.addActionListener(this);
        gadpr.setBackground(Color.blue);
        gmp.setBackground(Color.blue);
        back.setBackground(Color.blue);
        gadpr.setForeground(Color.white);
        gmp.setForeground(Color.white);
        back.setForeground(Color.white);
        Panel twobtns = new Panel();
        twobtns.setLayout(new GridLayout(1, 3, 50, 0));
        twobtns.add(gadpr);
        twobtns.add(gmp);
        twobtns.add(back);
        window.add(twobtns, "Center");
        
        //Left and Right margin
        Panel right = new Panel();
        Panel left = new Panel();
        right.setLayout(new FlowLayout(0, 25, 0));
        left.setLayout(new FlowLayout(0, 25, 0));
        window.add(right, "East");
        window.add(left, "West"); 
        
        //Bottom
        footer1 = new Label();
        Panel footer = new Panel();
        footer.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 80));
        footer.add(footer1);
        window.add(footer, "South");
    }
    
}
