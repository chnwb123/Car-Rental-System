/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment;

import java.awt.Button;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Chan Jia Zhil
 */
public class user_check_booking implements ActionListener {
          
    public void checkbook1() {
        String username = Customer.showUsername();
        //Get only the rows which are relevant to the user, and insert into table to show users their booking statuses.
        ArrayList<String> userBookingRecords = new ArrayList<>(); 
        
        try (BufferedReader br = new BufferedReader(new FileReader("Car_Bookings.txt"))){
            String line;
            while ((line = br.readLine()) != null){
                String [] bookingrecords = line.split(" / ");
                if (bookingrecords.length > 9 && bookingrecords[8].equals(username)){
                    userBookingRecords.add(line);
                }
            }
        } catch (Exception e){
            System.out.println("Cant read (Car_Bookings.txt)");
        }
        
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        String[] columnNames = {
            "Car Brand", "Car Model", "Car Plate No.", "Car Color", "No. Car Seats",
            "Collision Damage Waiver (CDW)", "Car Price Per Day", "Car Price Per Month",
            "Status"
        };
        model.setColumnIdentifiers(columnNames);
        
        for(String bookingrecord : userBookingRecords){
            String [] record = bookingrecord.split(" / ");
            String [] recordData = new String[9];
            System.arraycopy(record, 0, recordData, 0, 8);
            recordData[8] = record[10];
            model.addRow(recordData);
        }
    }
    
    public void checkbook2(){
        //Delete the whole table
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int nor = table.getRowCount();
        for (int i = nor - 1; i >= 0; i-- ){
            model.removeRow(i);
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back) {
            window.setVisible(false);
            gui.d.window.setVisible(true);

        } else if (e.getSource() == checkbook) {
            checkbook1();
            checkbook.setVisible(false);
            refresh.setEnabled(true);

        } else if (e.getSource() == refresh){
            checkbook2();
            checkbook1();
            JOptionPane.showMessageDialog(window, "Refreshed successfully.");
        }
    }

    JFrame window;
    JTable table;
    Label user_cbpage;
    Button checkbook;
    Button back;
    Button refresh;
    Button extrabtn1;
    Button extrabtn2;

    public user_check_booking() {
        //GUI
        window = new JFrame();
        window.setSize(1500, 500);
        window.setLocation(30, 150);
        window.setVisible(false);

        //Title
        user_cbpage = new Label("User Check Booking Page");
        Panel ucbpagebtn = new Panel();
        ucbpagebtn.setLayout(new FlowLayout(FlowLayout.CENTER, 300, 50));
        ucbpagebtn.add(user_cbpage);
        window.add(ucbpagebtn, "North");

        //Middle Contents
        DefaultTableModel model = new DefaultTableModel(0, 11){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        table = new JTable(model);
        table.setPreferredSize(new Dimension(1300, 150));
        JScrollPane scrollpane = new JScrollPane(table);
        scrollpane.setPreferredSize(new Dimension(1300, 150));
        Panel footer = new Panel();
        footer.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 25));
        footer.add(scrollpane);
        window.add(footer, "Center");

        //Right and left margin
        back = new Button("Back");
        checkbook = new Button("Check Own Booking/s");
        refresh =  new Button("Refresh");
        extrabtn1 = new Button();
        extrabtn2 = new Button();
        extrabtn1.setVisible(false);
        extrabtn2.setVisible(false);
        refresh.setEnabled(false);
        back.addActionListener(this);
        checkbook.addActionListener(this);
        refresh.addActionListener(this);
        Panel right = new Panel();
        Panel left = new Panel();
        right.setLayout(new GridLayout(5, 1, 15, 5));
        right.add(checkbook);      
        right.add(refresh);
        right.add(back);
        right.add(extrabtn1);
        right.add(extrabtn2);
        left.setLayout(new FlowLayout(0, 25, 0));
        window.add(right, "East");
        window.add(left, "West");
    }
}
