/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gerdoc.dao;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Alumno
 */
public class RolUsuario implements Serializable
{
    private String rol;
    private String descripcion;

    public RolUsuario() 
    {       
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

  
    
    
}