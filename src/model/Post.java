/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author David
 */
public class Post {
    private Integer posId;
    private String posNombre;
    private String posDescripcion;

    public Post() {
    }

    public Post(Integer posId, String posNombre, String posDescripcion) {
        this.posId = posId;
        this.posNombre = posNombre;
        this.posDescripcion = posDescripcion;
    }

    public Integer getPosId() {
        return posId;
    }

    public void setPosId(Integer posId) {
        this.posId = posId;
    }

    public String getPosNombre() {
        return posNombre;
    }

    public void setPosNombre(String posNombre) {
        this.posNombre = posNombre;
    }

    public String getPosDescripcion() {
        return posDescripcion;
    }

    public void setPosDescripcion(String posDescripcion) {
        this.posDescripcion = posDescripcion;
    }

    @Override
    public String toString() {
        return "Post{" + "posId=" + posId + ", posNombre=" + posNombre + ", posDescripcion=" + posDescripcion + '}';
    }

    
    
}
