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

import org.gerdoc.dao.Producto;
import org.gerdoc.service.MySqlConnection;

/**
 *
 * @author Alumno
 */
@ManagedBean
@ViewScoped
public class ProductoHelper  implements Serializable
{
    private Producto producto;
    private boolean edit;

    public ProductoHelper() 
    {
    }
    
    public boolean loadProducto( )
    {
        if( producto == null )
        {
            producto = new Producto( );
        }
        return producto != null;
    }
    
    public void addProducto( )
    {
        if( !MySqlConnection.addProducto(producto) )
        {
            System.out.println("Error");
        }
        else
        {
            producto = null;
        }
    }
    
    public void editProducto( String producto1 )
    {
        if( producto == null)
        {
            return;
        }
        producto = MySqlConnection.getProductoByProducto(producto1);
        if( producto == null )
        {
            System.out.println("Error");
            return;
        }
        edit = true;
    }
    
    public List<Producto> getProductoList( )
    {
        return MySqlConnection.getProductoList();
    }
    
    public void updateProducto()
    {
        if( !MySqlConnection.updateProducto(producto) )
        {
            System.out.println("Error");
        }
        else
        {
            producto = null;
            edit = false;
        }
    }
    
    public void deleteProducto( String producto )
    {
        if( !MySqlConnection.deleteProducto( producto ) )
        {
            System.out.println("Error");
        }
        else
        {
            producto = null;
        }
    }
   
    public Producto getProducto() 
    {
        if( producto == null )
        {
            if( !loadProducto() )
            {
                return null;
            }
        }
        return producto;
    }

    public void setProducto(Producto producto) 
    {
        this.producto = producto;
    }

    public boolean isEdit() {
        return edit;
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
    }
    
    
}
