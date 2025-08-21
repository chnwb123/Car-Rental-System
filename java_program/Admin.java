/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment;

/**
 *
 * @author Chan Jia Zhil
 */
public class Admin extends User {
    private static int TotalPaymentCollection = 0;

    public Admin() {

    }

    public Admin(String username) {
        super(username);
    }

    public static String showUsername() {
        return Username;
    }
    
    public static String showPrice(){
        return String.valueOf(TotalPaymentCollection);
    }

    public void paymentCollection(String amountCollected) {
        int a = Integer.parseInt(amountCollected);
        TotalPaymentCollection = TotalPaymentCollection + a;
    }
}
