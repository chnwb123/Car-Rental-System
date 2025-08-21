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
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Chan Jia Zhil
 */
public class admin_manage_user_booking implements ActionListener{
    
    public void readData(){
        // Set Column Headers and add old records from (Car_Bookings.txt)
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        String[] columnNames = {
            "Car Brand", "Car Model", "Car Plate No.", "Car Color", "No. Car Seats",
            "Collision Damage Waiver (CDW)", "Car Price Per Day", "Car Price Per Month",
            "Username", "Price Paid", "Status", "Date"
        };
        model.setColumnIdentifiers(columnNames);
        String rec;
        try {
            BufferedReader br = new BufferedReader(new FileReader("Car_Bookings.txt"));
            while ((rec = br.readLine()) != null) {
                String[] record = rec.split(" / ");
                model.addRow(record);
            } 
        } catch (Exception e) {
            System.out.println("Cannot read data from Car.txt");
        }
    }
    
    public void approve(){   
        // Get selected row and update the status part to approved.
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int sRow = table.getSelectedRow();
        String getStatus; // String getStatus is used to get the status from the selected row.
        String amountCollected; // int amountCollected is used to get the amount paid by the user.
        if (sRow != -1){
            getStatus = model.getValueAt(sRow, 10).toString();
            amountCollected = model.getValueAt(sRow, 9).toString();
            if (getStatus.equals("Approved")){
                JOptionPane.showMessageDialog(window, "This booking has been approved.");
            } else if (getStatus.equals("Declined")){
                JOptionPane.showMessageDialog(window, "This booking has been declined.");
            } else {
                model.setValueAt("Approved", sRow, 10);
                Admin pay = new Admin();
                pay.paymentCollection(amountCollected);
                String TotalAmountReceived = Admin.showPrice(); // The Total collected amount of money will be printed
                amountreceivedtf.setText(TotalAmountReceived);
                JOptionPane.showMessageDialog(window, "Approved successfully.");
            }
            
        } else {
            JOptionPane.showMessageDialog(window, "Please select a row to approve.");
        }
    }
    
    public void approve2() {
        // Add the current table data to the (Car_Bookings.txt) file
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int nor = table.getRowCount();
        String rec;
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("Car_Bookings.txt"));
            for (int i = 0; i < nor; i++) {
                rec = model.getValueAt(i, 0) + " / " + model.getValueAt(i, 1) + " / "
                        + model.getValueAt(i, 2) + " / " + model.getValueAt(i, 3) + " / "
                        + model.getValueAt(i, 4) + " / " + model.getValueAt(i, 5) + " / "
                        + model.getValueAt(i, 6) + " / " + model.getValueAt(i, 7) + " / "
                        + model.getValueAt(i, 8) + " / " + model.getValueAt(i, 9) + " / "
                        + model.getValueAt(i, 10) + " / " + model.getValueAt(i, 11) + " / ";
                bw.write(rec + "\n");
            }
            bw.close();      
        } catch (Exception exc) {
            System.out.println("Cant write...");
        }
    }
    
    public void decline(){
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int sRow = table.getSelectedRow();
        String getStatus;
        if (sRow != -1){
            getStatus = model.getValueAt(sRow, 10).toString();
            if (getStatus.equals("Declined")){
                JOptionPane.showMessageDialog(window, "This booking has been declined.");
            } else if (getStatus.equals("Approved")){
                JOptionPane.showMessageDialog(window, "This booking has been approved.");
            }
            else {
                model.setValueAt("Declined", sRow, 10);
                JOptionPane.showMessageDialog(window, "Declined successfully.");
            }
            
        } else {
            JOptionPane.showMessageDialog(window, "Please select a row to decline.");
        }
    }
    
    public void decline2() {
        // Add the current table data to the (Car_Bookings.txt) file
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int nor = table.getRowCount();
        String rec;
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("Car_Bookings.txt"));
            for (int i = 0; i < nor; i++) {
                rec = model.getValueAt(i, 0) + " / " + model.getValueAt(i, 1) + " / "
                        + model.getValueAt(i, 2) + " / " + model.getValueAt(i, 3) + " / "
                        + model.getValueAt(i, 4) + " / " + model.getValueAt(i, 5) + " / "
                        + model.getValueAt(i, 6) + " / " + model.getValueAt(i, 7) + " / "
                        + model.getValueAt(i, 8) + " / " + model.getValueAt(i, 9) + " / "
                        + model.getValueAt(i, 10) + " / " + model.getValueAt(i, 11) + " / ";
                bw.write(rec + "\n");
            }
            bw.close();      
        } catch (Exception exc) {
            System.out.println("Cant write...");
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
    
    
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == show){
            readData();
            approve.setEnabled(true);
            decline.setEnabled(true);
            refresh.setEnabled(true);
            show.setVisible(false);       
        } else if (e.getSource() == approve){
            approve();
            approve2();           
        } else if (e.getSource() == decline){
            decline();
            decline2();
        } else if (e.getSource() == refresh){
            delete();
            readData();
            JOptionPane.showMessageDialog(window, "Refreshed Successfully.");
        } else {
            window.setVisible(false);
            gui.g.window.setVisible(true);
        }
    }
    
    JFrame window;
    Label admin_mubpage;
    Label amountreceived;
    TextField amountreceivedtf;
    JTable table;
    Button approve;
    Button decline;
    Button show;
    Button back;
    Button refresh;
    Button extrabtn1;
    
    public admin_manage_user_booking(){
        //GUI
        window = new JFrame();
        window.setSize(1400, 500);
        window.setLocation(80, 150);
        window.setVisible(false);
        
        //Title
        admin_mubpage = new Label("Admin Manage User Booking/s Page");
        Panel amubpagebtn = new Panel();
        amubpagebtn.setLayout(new FlowLayout(FlowLayout.CENTER, 300, 50));
        amubpagebtn.add(admin_mubpage);
        window.add(amubpagebtn, "North");
        
        //Middle Contents
        DefaultTableModel model = new DefaultTableModel(0, 10){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        table = new JTable(model);
        table.getColumnModel().getColumn(0).setPreferredWidth(10);
        table.getColumnModel().getColumn(1).setPreferredWidth(10);
        table.getColumnModel().getColumn(2).setPreferredWidth(10);
        table.getColumnModel().getColumn(3).setPreferredWidth(10);
        table.getColumnModel().getColumn(4).setPreferredWidth(10);
        table.getColumnModel().getColumn(5).setPreferredWidth(60);
        table.getColumnModel().getColumn(6).setPreferredWidth(10);
        table.getColumnModel().getColumn(7).setPreferredWidth(10);
        table.getColumnModel().getColumn(8).setPreferredWidth(10);
        table.getColumnModel().getColumn(9).setPreferredWidth(10);
        table.setPreferredSize(new Dimension(1200, 150));
        JScrollPane scrollpane = new JScrollPane(table);
        scrollpane.setPreferredSize(new Dimension(1200, 150));
        Panel footer = new Panel();
        footer.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 25));
        footer.add(scrollpane);
        window.add(footer, "Center");
        
        //Right and left margin
        approve = new Button("Approve");
        decline = new Button("Decline");
        show = new Button("Show User Booking/s");
        back = new Button("Back");
        refresh = new Button("Refresh");
        extrabtn1= new Button();
        extrabtn1.setVisible(false);
        approve.setEnabled(false);
        decline.setEnabled(false);
        refresh.setEnabled(false);
        approve.addActionListener(this);
        decline.addActionListener(this);
        show.addActionListener(this);
        back.addActionListener(this);
        refresh.addActionListener(this);
        Panel right = new Panel();
        Panel left = new Panel();
        right.setLayout(new GridLayout(6, 1, 5, 5));
        right.add(approve);
        right.add(decline);
        right.add(refresh);
        right.add(show);
        right.add(back);
        right.add(extrabtn1);
        left.setLayout(new FlowLayout(0, 25, 0));
        window.add(right, "East");
        window.add(left, "West");
        
        //Bottom 
        amountreceived = new Label("Total Amount Received: ");
        amountreceivedtf = new TextField(20);
        amountreceivedtf.setEditable(false);
        Panel bottom = new Panel();
        bottom.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 25));
        bottom.add(amountreceived);
        bottom.add(amountreceivedtf);
        window.add(bottom, "South");
    }
}

