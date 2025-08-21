/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment;

/**
 *
 * @author Chan Jia Zhil
 */
public class gui {
    
    static home_page2 a;
    static login_page b;
    static register_page c;
    static user_page d;
    static admin_manage_car_info e;
    static admin_manage_user_booking f;
    static admin_page g;
    static user_book_car h;
    static user_check_booking i;
    static admin_generate_analyzed_reports j;
    static admin_approved_declined_percentage_report k;
    static admin_monthly_sales_report l;
    
    public static void main(String[] args) {
        a = new home_page2();
        b = new login_page();
        c = new register_page();
        d = new user_page();
        e = new admin_manage_car_info();
        f = new admin_manage_user_booking();
        g = new admin_page();
        h = new user_book_car();
        i = new user_check_booking();
        j = new admin_generate_analyzed_reports();
        k = new admin_approved_declined_percentage_report();
        l = new admin_monthly_sales_report();
    }
}
