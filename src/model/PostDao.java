/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author David
 */
public class PostDao {
    private static final String SQL_INGRESAR=
            "INSERT INTO post(posNombre, posDescripcion) "
            + "VALUES (?, ?)";
    private static final String SQL_ACTUALIZAR=
            "UPDATE post SET posNombre = ?, posDescripcion = ? "
            + "WHERE posId = ?";
    private static final String SQL_ELIMINAR=
            "DELETE FROM post WHERE posId = ?";
    private static final String SQL_BUSCAR= 
            "SELECT * FROM post WHERE posId = ?";
    private static final String SQL_LISTAR=
            "SELECT * FROM post";
    
    private static final Conexion cnn=Conexion.saberEstado();
    
    public boolean ingresar(Post x) {
        PreparedStatement ps;
        int bandera;
        try {
            ps=cnn.getCnn().prepareStatement(SQL_INGRESAR);
            ps.setString(1, x.getPosNombre());
            ps.setString(2, x.getPosDescripcion());
            bandera=ps.executeUpdate();
            if(bandera > 0){
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("Registro a la Base de Datos con problema");
        }finally{
            cnn.cerrarConexion();
        }
        return false;
    }

    
    public boolean actualizar(Post x) {
        PreparedStatement ps;
        int bandera;
        try {
            
            ps=cnn.getCnn().prepareStatement(SQL_ACTUALIZAR);
            ps.setString(1,x.getPosNombre());
            ps.setString(2,x.getPosDescripcion());
            ps.setInt(3,x.getPosId());
            System.out.println(ps.toString());
            bandera=ps.executeUpdate();
            System.out.println(bandera);
            if(bandera>0){
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("Actualizar a la Base de Datos con problema");
        }finally{
            cnn.cerrarConexion();
        }
        return false;
    }

    public boolean eliminar(Post x) {
        PreparedStatement ps;
        int bandera;
        try{
            ps=cnn.getCnn().prepareStatement(SQL_ELIMINAR);
            ps.setInt(1, x.getPosId());
            bandera=ps.executeUpdate();
            if(bandera>0){
                return true;
            }
        }catch(SQLException ex){
            System.out.println("Error al eliminar de la Base de Datos");
        }finally{
            cnn.cerrarConexion();
        }
        return false;
    }

    public Post buscar(Post x) {
        PreparedStatement ps;
        ResultSet rs;
        Post p = null;
        try{
            ps = cnn.getCnn().prepareStatement(SQL_BUSCAR);
            ps.setInt(1, x.getPosId());
            rs = ps.executeQuery();
            while(rs.next()){
                p = new Post(rs.getInt("posId"),
                        rs.getString("posNombre"),
                        rs.getString("posDescripcion"));
            }
        }catch(SQLException ex){
            System.out.println("Error al Buscar Producto");
        }finally{
            cnn.cerrarConexion();
        }
        return p;
    }

    public ArrayList<Post> listar() {
    
        PreparedStatement ps;
        ResultSet rs;
        ArrayList<Post> posts= new ArrayList();
        try {
            ps=cnn.getCnn().prepareStatement(SQL_LISTAR);
            rs=ps.executeQuery();
            while(rs.next()){
                posts.add(
                        new Post(rs.getInt("posId"),
                                rs.getString("posNombre"),
                                rs.getString("posDescripcion")));
            }
        } catch (SQLException ex) {
            System.out.println("Error al listar Productos");
        }
        return posts;
    }
}
