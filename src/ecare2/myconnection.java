/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecare2;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author tpc
 */


public class myconnection {
    public String url ="jdbc:mysql://localhost:3306/ecare1";
    public String login="root";
    public String pwd ="root";
    public Connection cnx;
    public static myconnection ct;

    private myconnection() {
        try {
           cnx = DriverManager.getConnection(url, login, pwd);
            System.out.println("Cnx etablie");
        } catch (SQLException ex) {
            System.out.println("Problème de cnx");
            System.out.println(ex.getMessage());
        }
    
    }
    public Connection getConnection(){
        return cnx;
    }
    public static myconnection getInstance(){
        if(ct == null)
            ct = new myconnection();
        return ct;
        
    } 
    
}


