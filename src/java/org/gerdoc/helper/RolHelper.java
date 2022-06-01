/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gerdoc.helper;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.gerdoc.dao.Rol;
import org.gerdoc.service.MySqlConnection;

/**
 *
 * @author Alumno
 */
@ManagedBean
@ViewScoped
public class RolHelper  implements Serializable
{
    private Rol rol;
    private boolean edit;

    public RolHelper() 
    {
    }
    
    public boolean loadRol( )
    {
        if( rol == null )
        {
            rol = new Rol( );
        }
        return rol != null;
    }
    
    public void addRol( )
    {
        if( !MySqlConnection.addRol(rol) )
        {
            System.out.println("Error");
        }
        else
        {
            rol = null;
        }
    }
    
    public void editRol( String rol1 )
    {
        if( rol == null)
        {
            return;
        }
        rol = MySqlConnection.getRolByRol(rol1);
        if( rol == null )
        {
            System.out.println("Error");
            return;
        }
        edit = true;
    }
    
    public List<Rol> getRolList( )
    {
        return MySqlConnection.getRolList();
    }
    
    public void updateRol()
    {
        if( !MySqlConnection.updateRol(rol) )
        {
            System.out.println("Error");
        }
        else
        {
            rol = null;
            edit = false;
        }
    }
    
    public void deleteRol( String rol )
    {
        if( !MySqlConnection.deleteRol( rol ) )
        {
            System.out.println("Error");
        }
        else
        {
            rol = null;
        }
    }
   
    public Rol getRol() 
    {
        if( rol == null )
        {
            if( !loadRol() )
            {
                return null;
            }
        }
        return rol;
    }

    public void setRol(Rol rol) 
    {
        this.rol = rol;
    }

    public boolean isEdit() {
        return edit;
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
    }
    
    
}
