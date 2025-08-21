/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment;

/**
 *
 * @author Chan Jia Zhil
 */
abstract class User {
    public static String Username;
    public static String Password;
    
    public User(){
        
    }
    
    public User(String username){
        Username = username;
    }

    public static String showUsername() {
        return Username;
    }
    
    public void Pay(){
        
    }
}
