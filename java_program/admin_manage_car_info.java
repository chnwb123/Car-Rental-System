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
public class admin_manage_car_info implements ActionListener {

    public void readData() {
        // Set Column Headers and add old/ new records from (Car.txt)
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

    public void writeData() {
        // Add the current table data to the (Car.txt) file
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int nor = table.getRowCount();
        String rec;
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("Car.txt"));
            for (int i = 0; i < nor; i++) {
                rec = model.getValueAt(i, 0) + " / " + model.getValueAt(i, 1) + " / "
                        + model.getValueAt(i, 2) + " / " + model.getValueAt(i, 3) + " / "
                        + model.getValueAt(i, 4) + " / " + model.getValueAt(i, 5) + " / "
                        + model.getValueAt(i, 6) + " / " + model.getValueAt(i, 7) + " / ";
                bw.write(rec + "\n");
            }
            bw.close();
            JOptionPane.showMessageDialog(window, "New car has been successfully added.");
        } catch (Exception exc) {
            System.out.println("Cant write...");
        }
    }

    public void refresh() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        //Delete the whole table
        int nor = table.getRowCount();
        for (int i = nor - 1; i >= 0; i--) {
            model.removeRow(i);
        }
        
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

    public static boolean isValidCarPlateNo(String a) {
        String pattern = ".*[^a-zA-Z0-9].*";
        return a.matches(pattern);
    }

    //Data validation
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == add) {

            //Car Brand
            String carbrand = carbrandtf.getText();
            String carbrandcheck = "0";
            if (carbrand.equals("Empty input is not acceptable.")) {
                carbrandtf.setText("Empty input is not acceptable.");
            } else if (register_page.isValidEmpty(carbrand)) {
                carbrandtf.setText("Empty input is not acceptable.");
            } else {
                carbrandcheck = "1";
            }

            //Car Model
            String carmodel = carmodeltf.getText();
            String carmodelcheck = "0";
            if (carmodel.equals("Empty input is not acceptable.")) {
                carmodeltf.setText("Empty input is not acceptable.");
            } else if (register_page.isValidEmpty(carmodel)) {
                carmodeltf.setText("Empty input is not acceptable.");
            } else {
                carmodelcheck = "1";
            }

            //Car Plate No
            String carplateno = carplatenotf.getText();
            String carplatenocheck = "0";
            if (carplateno.equals("Empty input is not acceptable.")) {
                carplatenotf.setText("Empty input is not acceptable.");
            } else if (carplateno.equals("Must be a combination of alphabet/s and number/s.")) {
                carplatenotf.setText("Must be a combination of alphabet/s and number/s.");
            } else if (register_page.isValidEmpty(carplateno)) {
                carplatenotf.setText("Empty input is not acceptable.");
            } else if (register_page.isValidName(carplateno) || register_page.isValidPhoneNum(carplateno)
                    || isValidCarPlateNo(carplateno)) {
                carplatenotf.setText("Must be a combination of alphabet/s and number/s.");
            } else {
                carplatenocheck = "1";
            }

            //Car Color
            String carcolor = carcolortf.getText();
            String carcolorcheck = "0";
            if (carcolor.equals("Empty input is not acceptable.")) {
                carcolortf.setText("Empty input is not acceptable.");
            } else if (carcolor.equals("Invalid Input.")) {
                carcolortf.setText("Invalid Input.");
            } else if (register_page.isValidEmpty(carcolor)) {
                carcolortf.setText("Empty input is not acceptable.");
            } else if (!register_page.isValidName(carcolor)) {
                carcolortf.setText("Invalid Input.");
            } else {
                carcolorcheck = "1";
            }

            //Car Seats
            String carseats = carseatstf.getText();
            String carseatscheck = "0";
            if (carseats.equals("Empty input is not acceptable.")) {
                carseatstf.setText("Empty input is not acceptable.");
            } else if (carseats.equals("Invalid Input.")) {
                carseatstf.setText("Invalid Input.");
            } else if (register_page.isValidEmpty(carseats)) {
                carseatstf.setText("Empty input is not acceptable.");
            } else if (!register_page.isValidPhoneNum(carseats)) {
                carseatstf.setText("Invalid Input.");
            } else {
                carseatscheck = "1";
            }

            // Car CDW
            String carCDW = carCDWtf.getText();
            String carCDWcheck = "0";
            if (carCDW.equals("Empty input is not acceptable.")) {
                carCDWtf.setText("Empty input is not acceptable.");
            } else if (carCDW.equals("Invalid Input.")) {
                carCDWtf.setText("Invalid Input.");
            } else if (register_page.isValidEmpty(carCDW)) {
                carCDWtf.setText("Empty input is not acceptable.");
            } else if (!register_page.isValidPhoneNum(carCDW)) {
                carCDWtf.setText("Invalid Input.");
            } else {
                carCDWcheck = "1";
            }

            //Car Price Per Day
            String carpricepd = carpricepdtf.getText();
            String carpricepdcheck = "0";
            if (carpricepd.equals("Empty input is not acceptable.")) {
                carpricepdtf.setText("Empty input is not acceptable.");
            } else if (carpricepd.equals("Invalid Input.")) {
                carpricepdtf.setText("Invalid Input.");
            } else if (register_page.isValidEmpty(carpricepd)) {
                carpricepdtf.setText("Empty input is not acceptable.");
            } else if (!register_page.isValidPhoneNum(carpricepd)) {
                carpricepdtf.setText("Invalid Input.");
            } else {
                carpricepdcheck = "1";
            }

            //Car Price Per Month
            String carpricepm = carpricepmtf.getText();
            String carpricepmcheck = "0";
            if (carpricepm.equals("Empty input is not acceptable.")) {
                carpricepmtf.setText("Empty input is not acceptable.");
            } else if (carpricepm.equals("Invalid Input.")) {
                carpricepmtf.setText("Invalid Input.");
            } else if (register_page.isValidEmpty(carpricepm)) {
                carpricepmtf.setText("Empty input is not acceptable.");
            } else if (!register_page.isValidPhoneNum(carpricepm)) {
                carpricepmtf.setText("Invalid Input.");
            } else {
                carpricepmcheck = "1";
            }

            if (!carbrandcheck.equals("1") || !carmodelcheck.equals("1") || !carplatenocheck.equals("1")
                    || !carcolorcheck.equals("1") || !carseatscheck.equals("1") || !carCDWcheck.equals("1")
                    || !carpricepdcheck.equals("1") || !carpricepmcheck.equals("1")) {
                JOptionPane.showMessageDialog(window, "Please ensure all the details are correct.");
            }

            if (carbrandcheck.equals("1") && carmodelcheck.equals("1") && carplatenocheck.equals("1")
                    && carcolorcheck.equals("1") && carseatscheck.equals("1") && carCDWcheck.equals("1")
                    && carpricepdcheck.equals("1") && carpricepmcheck.equals("1")) {

                // Take data from textfield and add them to table
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                String[] columnNames = {
                    "Car Brand", "Car Model", "Car Plate No.", "Car Color", "No. Car Seats",
                    "Collision Damage Waiver (CDW)", "Car Price Per Day", "Car Price Per Month"
                };
                model.setColumnIdentifiers(columnNames);
                String[] record = {
                    carbrandtf.getText(), carmodeltf.getText(), carplatenotf.getText(), carcolortf.getText(),
                    carseatstf.getText(), carCDWtf.getText(), carpricepdtf.getText(), carpricepmtf.getText()
                };
                model.addRow(record);
                carbrandtf.setText("");
                carmodeltf.setText("");
                carplatenotf.setText("");
                carcolortf.setText("");
                carseatstf.setText("");
                carCDWtf.setText("");
                carpricepdtf.setText("");
                carpricepmtf.setText("");
                add.setEnabled(false);
                edit.setEnabled(false);
                delete.setEnabled(false);
                refresh.setEnabled(false);
                save.setEnabled(true);
            }

        } else if (e.getSource() == edit) {
            // Get selected row, put them inside text field for edting
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            int sRow = table.getSelectedRow();
            if (sRow != -1) {
                add.setEnabled(false);
                edit.setEnabled(false);
                delete.setEnabled(false);
                save.setEnabled(false);
                read.setEnabled(false);
                saveEdit.setEnabled(true);
                String carbrand = model.getValueAt(sRow, 0).toString();
                String carmodel = model.getValueAt(sRow, 1).toString();
                String carplateno = model.getValueAt(sRow, 2).toString();
                String carcolor = model.getValueAt(sRow, 3).toString();
                String carseats = model.getValueAt(sRow, 4).toString();
                String carCDW = model.getValueAt(sRow, 5).toString();
                String carpricepd = model.getValueAt(sRow, 6).toString();
                String carpricepm = model.getValueAt(sRow, 7).toString();
                carbrandtf.setText(carbrand);
                carmodeltf.setText(carmodel);
                carplatenotf.setText(carplateno);
                carcolortf.setText(carcolor);
                carseatstf.setText(carseats);
                carCDWtf.setText(carCDW);
                carpricepdtf.setText(carpricepd);
                carpricepmtf.setText(carpricepm);
            } else {
                JOptionPane.showMessageDialog(window, "Please select a row to edit.");
            }

        } else if (e.getSource() == delete) {
            // Select a row to delete
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            int sRow = table.getSelectedRow();
            if (sRow != -1) {
                model.removeRow(sRow);
                save.setEnabled(true);
                add.setEnabled(false);
                edit.setEnabled(false);
                delete.setEnabled(false);
            } else {
                JOptionPane.showMessageDialog(window, "Please select a row to delete.");
            }

        } else if (e.getSource() == saveEdit) {

            //Car Brand
            String carbrand = carbrandtf.getText();
            String carbrandcheck = "0";
            if (carbrand.equals("Empty input is not acceptable.")) {
                carbrandtf.setText("Empty input is not acceptable.");
            } else if (register_page.isValidEmpty(carbrand)) {
                carbrandtf.setText("Empty input is not acceptable.");
            } else {
                carbrandcheck = "1";
            }

            //Car Model
            String carmodel = carmodeltf.getText();
            String carmodelcheck = "0";
            if (carmodel.equals("Empty input is not acceptable.")) {
                carmodeltf.setText("Empty input is not acceptable.");
            } else if (register_page.isValidEmpty(carmodel)) {
                carmodeltf.setText("Empty input is not acceptable.");
            } else {
                carmodelcheck = "1";
            }

            //Car Plate No
            String carplateno = carplatenotf.getText();
            String carplatenocheck = "0";
            if (carplateno.equals("Empty input is not acceptable.")) {
                carplatenotf.setText("Empty input is not acceptable.");
            } else if (carplateno.equals("Must be a combination of alphabets and number.")) {
                carplatenotf.setText("Must be a combination of alphabets and number.");
            } else if (register_page.isValidEmpty(carplateno)) {
                carplatenotf.setText("Empty input is not acceptable.");
            } else if (register_page.isValidName(carplateno) || register_page.isValidPhoneNum(carplateno)
                    || isValidCarPlateNo(carplateno)) {
                carplatenotf.setText("Must be a combination of alphabet/s and number/s.");
            } else {
                carplatenocheck = "1";
            }

            //Car Color
            String carcolor = carcolortf.getText();
            String carcolorcheck = "0";
            if (carcolor.equals("Empty input is not acceptable.")) {
                carcolortf.setText("Empty input is not acceptable.");
            } else if (carcolor.equals("Invalid Input.")) {
                carcolortf.setText("Invalid Input.");
            } else if (register_page.isValidEmpty(carcolor)) {
                carcolortf.setText("Empty input is not acceptable.");
            } else if (!register_page.isValidName(carcolor)) {
                carcolortf.setText("Invalid Input.");
            } else {
                carcolorcheck = "1";
            }

            //Car Seats
            String carseats = carseatstf.getText();
            String carseatscheck = "0";
            if (carseats.equals("Empty input is not acceptable.")) {
                carseatstf.setText("Empty input is not acceptable.");
            } else if (carseats.equals("Invalid Input.")) {
                carseatstf.setText("Invalid Input.");
            } else if (register_page.isValidEmpty(carseats)) {
                carseatstf.setText("Empty input is not acceptable.");
            } else if (!register_page.isValidPhoneNum(carseats)) {
                carseatstf.setText("Invalid Input.");
            } else {
                carseatscheck = "1";
            }

            // Car CDW
            String carCDW = carCDWtf.getText();
            String carCDWcheck = "0";
            if (carCDW.equals("Empty input is not acceptable.")) {
                carCDWtf.setText("Empty input is not acceptable.");
            } else if (carCDW.equals("Invalid Input.")) {
                carCDWtf.setText("Invalid Input.");
            } else if (register_page.isValidEmpty(carCDW)) {
                carCDWtf.setText("Empty input is not acceptable.");
            } else if (!register_page.isValidPhoneNum(carCDW)) {
                carCDWtf.setText("Invalid Input.");
            } else {
                carCDWcheck = "1";
            }

            //Car Price Per Day
            String carpricepd = carpricepdtf.getText();
            String carpricepdcheck = "0";
            if (carpricepd.equals("Empty input is not acceptable.")) {
                carpricepdtf.setText("Empty input is not acceptable.");
            } else if (carpricepd.equals("Invalid Input.")) {
                carpricepdtf.setText("Invalid Input.");
            } else if (register_page.isValidEmpty(carpricepd)) {
                carpricepdtf.setText("Empty input is not acceptable.");
            } else if (!register_page.isValidPhoneNum(carpricepd)) {
                carpricepdtf.setText("Invalid Input.");
            } else {
                carpricepdcheck = "1";
            }

            //Car Price Per Month
            String carpricepm = carpricepmtf.getText();
            String carpricepmcheck = "0";
            if (carpricepm.equals("Empty input is not acceptable.")) {
                carpricepmtf.setText("Empty input is not acceptable.");
            } else if (carpricepm.equals("Invalid Input.")) {
                carpricepmtf.setText("Invalid Input.");
            } else if (register_page.isValidEmpty(carpricepm)) {
                carpricepmtf.setText("Empty input is not acceptable.");
            } else if (!register_page.isValidPhoneNum(carpricepm)) {
                carpricepmtf.setText("Invalid Input.");
            } else {
                carpricepmcheck = "1";
            }

            if (!carbrandcheck.equals("1") || !carmodelcheck.equals("1") || !carplatenocheck.equals("1")
                    || !carcolorcheck.equals("1") || !carseatscheck.equals("1") || !carCDWcheck.equals("1")
                    || !carpricepdcheck.equals("1") || !carpricepmcheck.equals("1")) {
                JOptionPane.showMessageDialog(window, "Please ensure all the details are correct.");
            }

            if (carbrandcheck.equals("1") && carmodelcheck.equals("1") && carplatenocheck.equals("1")
                    && carcolorcheck.equals("1") && carseatscheck.equals("1") && carCDWcheck.equals("1")
                    && carpricepdcheck.equals("1") && carpricepmcheck.equals("1")) {

                // Set the values from the textfields to the table
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                int sRow = table.getSelectedRow();
                model.setValueAt(carbrandtf.getText(), sRow, 0);
                model.setValueAt(carmodeltf.getText(), sRow, 1);
                model.setValueAt(carplatenotf.getText(), sRow, 2);
                model.setValueAt(carcolortf.getText(), sRow, 3);
                model.setValueAt(carseatstf.getText(), sRow, 4);
                model.setValueAt(carCDWtf.getText(), sRow, 5);
                model.setValueAt(carpricepdtf.getText(), sRow, 6);
                model.setValueAt(carpricepmtf.getText(), sRow, 7);
                carbrandtf.setText("");
                carmodeltf.setText("");
                carplatenotf.setText("");
                carcolortf.setText("");
                carseatstf.setText("");
                carCDWtf.setText("");
                carpricepdtf.setText("");
                carpricepmtf.setText("");
                add.setEnabled(false);
                edit.setEnabled(false);
                delete.setEnabled(false);
                save.setEnabled(true);
                read.setEnabled(false);
                saveEdit.setEnabled(false);
            }

        } else if (e.getSource() == save) {
            writeData();
            carbrandtf.setText("");
            carmodeltf.setText("");
            carplatenotf.setText("");
            carcolortf.setText("");
            carseatstf.setText("");
            carCDWtf.setText("");
            carpricepdtf.setText("");
            carpricepmtf.setText("");
            add.setEnabled(true);
            edit.setEnabled(true);
            delete.setEnabled(true);
            refresh.setEnabled(true);
            save.setEnabled(false);

        } else if (e.getSource() == read) {
            readData();
            read.setVisible(false);
            add.setEnabled(true);
            edit.setEnabled(true);
            delete.setEnabled(true);
            refresh.setEnabled(true);
            save.setEnabled(false);

        } else if (e.getSource() == refresh) {
            refresh();
            JOptionPane.showMessageDialog(window, "Refresh Successfully.");
        } else {
            window.setVisible(false);
            gui.g.window.setVisible(true);
        }
    }

    JFrame window;
    JTable table;
    Button add;
    Button edit;
    Button delete;
    Button saveEdit;
    Button save;
    Button read;
    Button back;
    Button refresh;
    Label extra1;
    Label extra2;
    Label extra3;
    Label extra4;
    Label extra5;
    Label extra6;
    Label extra7;
    Label extra8;
    Label carbrand;
    Label carmodel;
    Label carplateno;
    Label carcolor;
    Label carseats;
    Label carCDW;
    Label carpricepd;
    Label carpricepm;
    Label admin_mcipage;
    TextField carbrandtf;
    TextField carmodeltf;
    TextField carplatenotf;
    TextField carcolortf;
    TextField carseatstf;
    TextField carCDWtf;
    TextField carpricepdtf;
    TextField carpricepmtf;

    public admin_manage_car_info() {
        // GUI
        window = new JFrame();
        window.setSize(1400, 535);
        window.setLocation(55, 150);
        window.setVisible(false);

        // Title
        admin_mcipage = new Label("Manage Car Information Page");
        Panel mcipagebtn = new Panel();
        mcipagebtn.setLayout(new FlowLayout(FlowLayout.CENTER, 300, 50));
        mcipagebtn.add(admin_mcipage);
        window.add(mcipagebtn, "North");

        // Middle
        carbrand = new Label("Car Brand: ");
        carmodel = new Label("Car Model: ");
        carplateno = new Label("Car Plate No.: ");
        carcolor = new Label("Car Color: ");
        carseats = new Label("No. Car Seats: ");
        carCDW = new Label("Collision Damage Waiver (CDW): ");
        carpricepd = new Label("Car Price Per Day: ");
        carpricepm = new Label("Car Price Per Month: ");
        extra1 = new Label("");
        extra2 = new Label("");
        extra3 = new Label("");
        extra4 = new Label("");
        extra5 = new Label("");
        extra6 = new Label("");
        extra7 = new Label("");
        extra8 = new Label("");
        carbrandtf = new TextField();
        carmodeltf = new TextField();
        carplatenotf = new TextField();
        carcolortf = new TextField();
        carseatstf = new TextField();
        carCDWtf = new TextField();
        carpricepdtf = new TextField();
        carpricepmtf = new TextField();
        Panel mngcarinfobtns = new Panel();
        mngcarinfobtns.setLayout(new GridLayout(8, 3, 0, 5));
        mngcarinfobtns.add(carbrand);
        mngcarinfobtns.add(carbrandtf);
        mngcarinfobtns.add(extra1);
        mngcarinfobtns.add(carmodel);
        mngcarinfobtns.add(carmodeltf);
        mngcarinfobtns.add(extra2);
        mngcarinfobtns.add(carplateno);
        mngcarinfobtns.add(carplatenotf);
        mngcarinfobtns.add(extra3);
        mngcarinfobtns.add(carcolor);
        mngcarinfobtns.add(carcolortf);
        mngcarinfobtns.add(extra4);
        mngcarinfobtns.add(carseats);
        mngcarinfobtns.add(carseatstf);
        mngcarinfobtns.add(extra5);
        mngcarinfobtns.add(carCDW);
        mngcarinfobtns.add(carCDWtf);
        mngcarinfobtns.add(extra6);
        mngcarinfobtns.add(carpricepd);
        mngcarinfobtns.add(carpricepdtf);
        mngcarinfobtns.add(extra7);
        mngcarinfobtns.add(carpricepm);
        mngcarinfobtns.add(carpricepmtf);
        mngcarinfobtns.add(extra8);
        window.add(mngcarinfobtns, "Center");

        // Footer
        DefaultTableModel model = new DefaultTableModel(0, 8) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(model);
        table.setPreferredSize(new Dimension(1300, 150));
        JScrollPane scrollpane = new JScrollPane(table);
        scrollpane.setPreferredSize(new Dimension(1300, 150));
        Panel footer = new Panel();
        footer.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 25));
        Label tablelabel = new Label("Table: ");
        footer.add(tablelabel);
        footer.add(scrollpane);
        window.add(footer, "South");

        // Right and left margin
        add = new Button("Add");
        edit = new Button("Edit");
        saveEdit = new Button("Save Changes");
        delete = new Button("Delete");
        save = new Button("Save to txtfile");
        read = new Button("Get Old Records");
        back = new Button("Back");
        refresh = new Button("Refresh");
        add.addActionListener(this);
        edit.addActionListener(this);
        saveEdit.addActionListener(this);
        delete.addActionListener(this);
        save.addActionListener(this);
        read.addActionListener(this);
        back.addActionListener(this);
        refresh.addActionListener(this);
        add.setEnabled(false);
        edit.setEnabled(false);
        saveEdit.setEnabled(false);
        delete.setEnabled(false);
        save.setEnabled(false);
        refresh.setEnabled(false);
        Panel right = new Panel();
        Panel left = new Panel();
        right.setLayout(new GridLayout(8, 1, 5, 5));
        right.add(add);
        right.add(edit);
        right.add(saveEdit);
        right.add(delete);
        right.add(save);
        right.add(read);
        right.add(refresh);
        right.add(back);
        left.setLayout(new FlowLayout(0, 25, 0));
        window.add(right, "East");
        window.add(left, "West");
    }
}
