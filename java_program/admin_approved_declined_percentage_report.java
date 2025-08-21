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
import java.math.RoundingMode;
import java.text.DecimalFormat;
import javax.swing.JFrame;

/**
 *
 * @author Chan Jia Zhil
 */
public class admin_approved_declined_percentage_report implements ActionListener{
        
    public static final DecimalFormat percformat = new DecimalFormat("0.00");
    
    public void generateReport(){
        //int approvedCounter and declinedCounter is used to count the number of Approved and Declined
        //int rowCount is used to count the number of rows
        int approvedCounter = 0;
        int declinedCounter = 0;
        int rowCount = 0;
        
        //Read the Car_Bookings file and check all lines to count the number of Approved and Declined
        try (BufferedReader br = new BufferedReader(new FileReader("Car_Bookings.txt"))){
            String line;
            while ((line = br.readLine()) != null){
                rowCount++;
                String [] adRecords = line.split(" / ");
                if (adRecords.length >= 12){
                    String status = adRecords[10].trim();
                    if(status.equals("Approved")){
                        approvedCounter++;
                    } else if (status.equals("Declined")){
                        declinedCounter++;
                    }
                }
            }
        
        //Formulas to get the percentage of Approved and Declined
        double percentageOfApproved = (double) approvedCounter / (approvedCounter + declinedCounter) * 100;
        double percentageOfDeclined = (double) declinedCounter / (approvedCounter + declinedCounter) * 100;
        percformat.setRoundingMode(RoundingMode.DOWN);
        String percentageOfApproved1 = percformat.format(percentageOfApproved);
        String percentageOfDeclined1 = percformat.format(percentageOfDeclined);
        String finalPerc = percentageOfApproved1 + "%" + "   /   " + percentageOfDeclined1 + "%";
        percentagetf.setText(finalPerc);
     
        } catch (Exception e){
            System.out.println("Cant read (Car_Bookings.txt)");
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == generate){
            generateReport();
            
        } else {
            window.setVisible(false);
            gui.j.window.setVisible(true);
        }
    }
    
    JFrame window;
    Label adminadpreportpage;
    Label adpperclabel;
    TextField percentagetf;
    Button generate;
    Button back;
    
    public admin_approved_declined_percentage_report(){
        //GUI
        window = new JFrame();
        window.setSize(800, 500);
        window.setLocation(500, 150);
        window.setVisible(false);

        // Title
        adminadpreportpage = new Label("Admin Generate Analyzed Overall Approved & Declined Percentage Report Page");
        Panel adminadpreportpagebtn = new Panel();
        adminadpreportpagebtn.setLayout(new FlowLayout(FlowLayout.CENTER, 300, 100));
        adminadpreportpagebtn.add(adminadpreportpage);
        window.add(adminadpreportpagebtn, "North");
        
        //Middle Contents
        adpperclabel = new Label("Overall Approved & Declined Percentage: ");
        percentagetf = new TextField();
        percentagetf.setEditable(false);
        generate = new Button("Generate");
        generate.addActionListener(this);
        Panel mid = new Panel();
        mid.setLayout(new GridLayout(3, 1, 10, 10));
        mid.add(adpperclabel);
        mid.add(percentagetf);
        mid.add(generate);
        window.add(mid);
        
        //Bottom
        Panel bottom = new Panel();
        bottom.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 75));
        window.add(bottom, "South");
        
        //Left and Right margin
        back = new Button("Back");
        back.addActionListener(this);
        Panel right = new Panel();
        Panel left = new Panel();
        right.setLayout(new FlowLayout(0, 130, 0));
        right.add(back);
        left.setLayout(new FlowLayout(0, 130, 0));
        window.add(right, "East");
        window.add(left, "West");
    }
    
}
