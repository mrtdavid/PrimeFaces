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

import org.gerdoc.dao.Usuario;
import org.gerdoc.service.MySqlConnection;

/**
 *
 * @author Alumno
 */
@ManagedBean
@ViewScoped
public class UsuarioHelper  implements Serializable
{
    private Usuario usuario;
    private boolean edit;

    public UsuarioHelper() 
    {
    }
    
    public boolean loadUsuario( )
    {
        if( usuario == null )
        {
            usuario = new Usuario( );
        }
        return usuario != null;
    }
    
    public void addUsuario( )
    {
        if( !MySqlConnection.addUsuario(usuario) )
        {
            System.out.println("Error");
        }
        else
        {
            usuario = null;
        }
    }
    
    public void editUsuario( String usuario1 )
    {
        if( usuario == null)
        {
            return;
        }
        usuario = MySqlConnection.getUsuarioByUsuario(usuario1);
        if( usuario == null )
        {
            System.out.println("Error");
            return;
        }
        edit = true;
    }
    
    public List<Usuario> getUsuarioList( )
    {
        return MySqlConnection.getUsuarioList();
    }
    
    public void updateUsuario()
    {
        if( !MySqlConnection.updateUsuario(usuario) )
        {
            System.out.println("Error");
        }
        else
        {
            usuario = null;
            edit = false;
        }
    }
    
    public void deleteUsuario( String usuario )
    {
        if( !MySqlConnection.deleteUsuario( usuario ) )
        {
            System.out.println("Error");
        }
        else
        {
            usuario = null;
        }
    }
   
    public Usuario getUsuario() 
    {
        if( usuario == null )
        {
            if( !loadUsuario() )
            {
                return null;
            }
        }
        return usuario;
    }

    public void setUsuario(Usuario usuario) 
    {
        this.usuario = usuario;
    }

    public boolean isEdit() {
        return edit;
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
    }
    
    
}
