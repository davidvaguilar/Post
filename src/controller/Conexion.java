/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David
 */
public class Conexion {
    
    public static final String URL= "jdbc:mysql://localhost:3306/test?zeroDateTimeBehavior=convertToNull";
    public static final String USERNAME= "dvillegas";
    public static final String PASSWORD= "I5483114";
    public static Conexion instance;
    private Connection cnn;
    

    private Conexion() {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            cnn=DriverManager.getConnection(URL,USERNAME, PASSWORD);
        }catch(ClassNotFoundException ex){
            ex.printStackTrace();
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public synchronized static Conexion saberEstado(){
        if(instance==null){
            instance=new Conexion();
        }
        return instance;
    }

    public Connection getCnn() {
        return cnn;
    }
    
    public void cerrarConexion(){
        instance = null;
    }
}
