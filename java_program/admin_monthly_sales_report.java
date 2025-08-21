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
import java.util.ArrayList;
import javax.swing.JFrame;

/**
 *
 * @author Chan Jia Zhil
 */
public class admin_monthly_sales_report implements ActionListener {

    public void generateMonthlySalesReport() {
        String getYearCheck = "0";
        String getMonthCheck = "0";

        String getYear = yeartf.getText();
        String getMonth = "0" + monthtf.getText();

        ArrayList<String> CarBookingRecords = new ArrayList<>(); //Save all the Car_Bookings records
        ArrayList<String> dates = new ArrayList<>(); //Save all the dates from the Car_Bookings rows
        ArrayList<String> years = new ArrayList<>(); //Save all the years from the Car_Bookings rows
        ArrayList<String> months = new ArrayList<>(); //Save all the months from the Car_Bookings rows
        ArrayList<String> TotalEarned = new ArrayList<>();
        int FinalTotalEarned = 0;

        try (BufferedReader br = new BufferedReader(new FileReader("Car_Bookings.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] dateArray = line.split(" / ");
                if (dateArray.length == 12 && !dateArray[11].equals("")) {
                    dates.add(dateArray[11]);
                    CarBookingRecords.add(line);
                }
            }
        } catch (Exception e) {
            System.out.println("Cant read (Car_Bookings.txt)");
        }

        // split the dates inside date array list to get separate year and month
        for (String eachDateItem : dates) {
            String[] YearAndMonthArray = eachDateItem.split("-");
            if (YearAndMonthArray.length >= 3) {
                years.add(YearAndMonthArray[0]);
                months.add(YearAndMonthArray[1]);
            }
        }

        for (String eachYear : years) {
            if (eachYear.equals(getYear)) {
                getYearCheck = "1";
                break;
            } else {
                yeartf.setText("This year is not available.");
            }
        }

        for (String eachMonth : months) {
            if (eachMonth.equals(getMonth)) {
                getMonthCheck = "1";
                break;
            } else {
                monthtf.setText("This month is not available.");
            }
        }

        if (getYearCheck.equals("1") && getMonthCheck.equals("1")) {
            String yearSelected = yeartf.getText();
            String monthSelected = "0" + monthtf.getText();

            try (BufferedReader br = new BufferedReader(new FileReader("Car_Bookings.txt"))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] selectedRows = line.split(" / ");
                    if (selectedRows.length == 12 && selectedRows[10].equals("Approved") && selectedRows[11].startsWith(yearSelected + "-" + monthSelected)) {
                        TotalEarned.add(selectedRows[9]);
                    }
                }
            } catch (Exception e) {
                System.out.println("Cant read (Car_Bookings.txt)");
            }

            for (String eachTotalEarned : TotalEarned) {
                int intEachTotalEarned = Integer.parseInt(eachTotalEarned);
                FinalTotalEarned = FinalTotalEarned + intEachTotalEarned;
            }

            msreport.setText(String.valueOf(FinalTotalEarned));
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == generate) {
            generateMonthlySalesReport();
        } else {
            window.setVisible(false);
            gui.j.window.setVisible(true);
        }
    }

    JFrame window;
    Label year;
    Label month;
    Label admingmsrpage;
    TextField yeartf;
    TextField monthtf;
    TextField msreport;
    Button generate;
    Button extrabtn;
    Label extralabel1;
    Button back;

    public admin_monthly_sales_report() {
        //GUI
        window = new JFrame();
        window.setSize(900, 500);
        window.setLocation(200, 150);
        window.setVisible(false);

        // Title
        admingmsrpage = new Label("Admin Generate Monthly Sales Report Page");
        Panel admingmsrpagebtn = new Panel();
        admingmsrpagebtn.setLayout(new FlowLayout(FlowLayout.CENTER, 300, 100));
        admingmsrpagebtn.add(admingmsrpage);
        window.add(admingmsrpagebtn, "North");

        // Middle Contents
        year = new Label("Enter the year: ");
        yeartf = new TextField();
        month = new Label("Enter the month: ");
        monthtf = new TextField();
        extrabtn = new Button();
        extrabtn.setVisible(false);
        msreport = new TextField();
        msreport.setEditable(false);
        extralabel1 = new Label("RM: ");
        generate = new Button("Generate");
        generate.addActionListener(this);
        Panel twobtns = new Panel();
        twobtns.setLayout(new GridLayout(4, 2, 0, 10));
        twobtns.add(year);
        twobtns.add(yeartf);
        twobtns.add(month);
        twobtns.add(monthtf);
        twobtns.add(extralabel1);
        twobtns.add(msreport);
        twobtns.add(extrabtn);
        twobtns.add(generate);
        window.add(twobtns);

        //Bottom
        Panel bottom = new Panel();
        bottom.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 60));
        window.add(bottom, "South");

        //Left and Right margin
        back = new Button("Back");
        back.addActionListener(this);
        Panel right = new Panel();
        Panel left = new Panel();
        right.setLayout(new FlowLayout(0, 100, 0));
        right.add(back);
        left.setLayout(new FlowLayout(0, 100, 0));
        window.add(right, "East");
        window.add(left, "West");
    }
}
