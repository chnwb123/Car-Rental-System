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
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
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

public class user_book_car implements ActionListener {
    
    private final LocalDate date = LocalDate.now();
    
    public void readData() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        String[] columnNames = {
            "Car Brand", "Car Model", "Car Plate No.", "Car Color", "No. Car Seats",
            "Collision Damage Waiver (CDW)", "Car Price Per Day", "Car Price Per Month"
        };
        model.setColumnIdentifiers(columnNames);
        String rec;
        try {
            BufferedReader br = new BufferedReader(new FileReader("Car.txt"));
            while ((rec = br.readLine()) != null) {
                String[] record = rec.split(" / ");
                model.addRow(record);
            }
        } catch (Exception e) {
            System.out.println("Cannot read data from Car.txt");
        }
    }
    
    public void delete(){
        //Delete the whole table
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int nor = table.getRowCount();
        for (int i = nor - 1; i >= 0; i-- ){
            model.removeRow(i);
        }
    }
    
    String amountpaid1;
    String amountpaid2 = "";
    public void checkAmountToBePaid(){
        int sRow1 = table.getSelectedRow();
            if (sRow1 != -1){
                amountpaid1 = JOptionPane.showInputDialog("Enter amount to be paid: ");
                    if ((!register_page.isValidPhoneNum(amountpaid1)) || amountpaid1.equals("") || (Integer.parseInt(amountpaid1) <= 0)){
                        JOptionPane.showMessageDialog(window, "Booking process is cancelled");
                        
                    } else {
                        amountpaid2 = amountpaid1;
                        Book1();
                        Book2();
                    }
            } else {
                JOptionPane.showMessageDialog(window, "Please select a row to book.");
            }
    }
    
    public void Book1(){
        // Create array to get previous data from Car_Bookings.txt
        ArrayList<String> previousCarBookingsArray = new ArrayList<>();
        String read;
        try{
            BufferedReader br3 = new BufferedReader(new FileReader("Car_Bookings.txt"));
            while ((read = br3.readLine()) != null){
                if (read.isEmpty()){
                    continue;
                }
                previousCarBookingsArray.add(read);
            }
        } catch (Exception e2){
            System.out.println("Cannot read data from Car_Bookings.txt");
        }
    
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        // Add selected row and taken old data to the Car_Bookings.txt
        int sRow2 = table.getSelectedRow();
            if (sRow2 != -1) {
                String username = Customer.showUsername();
                String carbrand = model.getValueAt(sRow2, 0).toString();
                String carmodel = model.getValueAt(sRow2, 1).toString();
                String carplateno = model.getValueAt(sRow2, 2).toString();
                String carcolor = model.getValueAt(sRow2, 3).toString();
                String carseats = model.getValueAt(sRow2, 4).toString();
                String carCDW = model.getValueAt(sRow2, 5).toString();
                String carpricepd = model.getValueAt(sRow2, 6).toString();
                String carpricepm = model.getValueAt(sRow2, 7).toString();
                String rec = carbrand + " / " + carmodel + " / " + carplateno + " / " + carcolor + " / "
                        + carseats + " / " + carCDW + " / " + carpricepd + " / " + carpricepm + " / "
                        + username + " / " + amountpaid2 + " / " + "Pending..." + " / " + date + " / ";
                try {
                    BufferedWriter bw = new BufferedWriter(new FileWriter("Car_Bookings.txt"));
                    for (String eachbooking : previousCarBookingsArray){
                        bw.write(eachbooking + "\n");
                        }
                    bw.write(rec + "\n");
                    bw.close();
                } catch (Exception exc) {
                    System.out.println("Cant write...");
                }
                
                //Delete booked row to get the latest update
                model.removeRow(sRow2);       
                JOptionPane.showMessageDialog(window, "You've successfuly booked.");
            } else {
                JOptionPane.showMessageDialog(window, "Please select a row to book.");
            }
    }

    public void Book2() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        // Add the updated table to the Car.txt
        int nor = table.getRowCount();
        String rec2;
        try {
            try (BufferedWriter bw2 = new BufferedWriter(new FileWriter("Car.txt"))) {
                for (int i = 0; i < nor; i++) {
                    rec2 = model.getValueAt(i, 0) + " / " + model.getValueAt(i, 1) + " / "
                            + model.getValueAt(i, 2) + " / " + model.getValueAt(i, 3) + " / "
                            + model.getValueAt(i, 4) + " / " + model.getValueAt(i, 5) + " / "
                            + model.getValueAt(i, 6) + " / " + model.getValueAt(i, 7) + " / ";
                    bw2.write(rec2 + "\n");
                }
            }
            } catch (IOException exc2) {
            System.out.println("Cant write...");
            }    
    }
 
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back) {
            window.setVisible(false);
            gui.d.window.setVisible(true);
            
        } else if (e.getSource() == book) {
            checkAmountToBePaid();       
            
        } else if (e.getSource() == refresh){
            delete();
            readData();
            JOptionPane.showMessageDialog(window, "Refreshed Successfully.");
        } else {
            readData();
            book.setEnabled(true);
            refresh.setEnabled(true);
            show.setVisible(false);      
        }
    }

    JFrame window;
    JTable table;
    Label user_bookcarpage;
    Button show;
    Button book;
    Button back;
    Button refresh;
    Button extrabtn1;
    Button extrabtn2;

    public user_book_car() {
        //GUI
        window = new JFrame();
        window.setSize(1500, 500);
        window.setLocation(30, 150);
        window.setVisible(false);

        //Title
        user_bookcarpage = new Label("User Car Booking Page");
        Panel ubcpagebtn = new Panel();
        ubcpagebtn.setLayout(new FlowLayout(FlowLayout.CENTER, 300, 50));
        ubcpagebtn.add(user_bookcarpage);
        window.add(ubcpagebtn, "North");

        //Middle Contents
        DefaultTableModel model = new DefaultTableModel(0, 8){
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
        book = new Button("Book");
        back = new Button("Back");
        show = new Button("Show Cars");
        refresh = new Button("Refresh");
        extrabtn1 = new Button();
        extrabtn2 = new Button();
        extrabtn1.setVisible(false);
        extrabtn2.setVisible(false);
        book.addActionListener(this);
        back.addActionListener(this);
        show.addActionListener(this);
        refresh.addActionListener(this);
        book.setEnabled(false);
        refresh.setEnabled(false);
        Panel right = new Panel();
        Panel left = new Panel();
        right.setLayout(new GridLayout(6, 1, 5, 5));
        right.add(book);
        right.add(refresh);
        right.add(show);
        right.add(back);
        right.add(extrabtn1);
        right.add(extrabtn2);
        left.setLayout(new FlowLayout(0, 25, 0));
        window.add(right, "East");
        window.add(left, "West");
    }
}
