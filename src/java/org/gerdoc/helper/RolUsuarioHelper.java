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

import org.gerdoc.dao.RolUsuario;
import org.gerdoc.service.MySqlConnection;

/**
 *
 * @author Alumno
 */
@ManagedBean
@ViewScoped
public class RolUsuarioHelper  implements Serializable
{
    private RolUsuario rolUsuario;
    private boolean edit;

    public RolUsuarioHelper() 
    {
    }
    
    public boolean loadRolUsuario( )
    {
        if( rolUsuario == null )
        {
            rolUsuario = new RolUsuario( );
        }
        return rolUsuario != null;
    }
    
    public void addRolUsuario( )
    {
        if( !MySqlConnection.addRolUsuario(rolUsuario) )
        {
            System.out.println("Error");
        }
        else
        {
            rolUsuario = null;
        }
    }
    
    public void editRolUsuario( String rolUsuario1 )
    {
        if( rolUsuario == null)
        {
            return;
        }
        rolUsuario = MySqlConnection.getRolUsuarioByRolUsuario(rolUsuario1);
        if( rolUsuario == null )
        {
            System.out.println("Error");
            return;
        }
        edit = true;
    }
    
    public List<RolUsuario> getRolUsuarioList( )
    {
        return MySqlConnection.getRolUsuarioList();
    }
    
    public void updateRolUsuario()
    {
        if( !MySqlConnection.updateRolUsuario(rolUsuario) )
        {
            System.out.println("Error");
        }
        else
        {
            rolUsuario = null;
            edit = false;
        }
    }
    
    public void deleteRolUsuario( String rolUsuario )
    {
        if( !MySqlConnection.deleteRolUsuario( rolUsuario ) )
        {
            System.out.println("Error");
        }
        else
        {
            rolUsuario = null;
        }
    }
   
    public RolUsuario getRolUsuario() 
    {
        if( rolUsuario == null )
        {
            if( !loadRolUsuario() )
            {
                return null;
            }
        }
        return rolUsuario;
    }

    public void setRolUsuario(RolUsuario rolUsuario) 
    {
        this.rolUsuario = rolUsuario;
    }

    public boolean isEdit() {
        return edit;
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
    }
    
    
}
