/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gerdoc.service;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.gerdoc.dao.Dos;
import org.gerdoc.dao.Rol;
import org.gerdoc.dao.Uno;
import org.gerdoc.dao.Usuario;

/**
 *
 * @author gerdoc
 */
public class MySqlConnection implements Serializable
{
    public static String user = "root";
    public static String password = "n0m3l0";
    public static String db = "tarea1";
    public static boolean testDriver( )
    {
        try 
        {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance( );
            return true;
        } 
        catch (Exception ex) 
        {
            ex.printStackTrace();
        }
        return false;
    }
    
    public static Connection getConnection( String user, String password , String db , String server )
    {
        String url = null;
        if( user == null || password == null || db == null || server == null )
        {
            return null;
        }
        if( "".equals(user) || "".equals(password) || "".equals(db) || "".equals(server) )
        {
            return null;
        }
        url = String.format( "jdbc:mysql://%s/%s?user=%s&password=%s" , server , db , user , password );
        try 
        {
            if( !testDriver() )
            {
                return null;
            }
            return DriverManager.getConnection(url);
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
        return null;
    }
    
    public static Connection getConnection( )
    {
        return getConnection( user, password, db , "127.0.0.1" );
    }
    
    public static void closeConnection( Connection connection )
    {
        try 
        {
            if( connection == null )
            {
                return;
            }
            if(connection.isClosed() )
            {
                return;
            }
            connection.close();
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
    }
    
    public static List<Uno> getUnoList( )
    {
        List<Uno>unoList = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        Uno uno = null;
        
        try 
        {
            connection = getConnection( );
            if( connection == null )
            {
                return null;
            }
            statement = connection.createStatement( );
            if( statement == null )
            {
                return null;
            }
            resultSet = statement.executeQuery( "SELECT * FROM TBL_UNO" );
            if( resultSet == null )
            {
                return null;
            }
            unoList = new ArrayList<>();
            while( resultSet.next() )
            {
                uno = new Uno();
                uno.setId( resultSet.getInt(1) );
                uno.setCampo1(resultSet.getInt(2) );
                uno.setCampo2(resultSet.getString(3) );
                uno.setCampo3(resultSet.getString(4) );
                uno.setCampo4(resultSet.getDate(5) );
                unoList.add(uno);
            }
            resultSet.close();
            closeConnection(connection);
            return unoList;
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
        return null;
    }
    
    public static List<Dos> getDosList( )
    {
        List<Dos>dosList = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        Dos dos = null;
        
        try 
        {
            connection = getConnection( );
            if( connection == null )
            {
                return null;
            }
            statement = connection.createStatement( );
            if( statement == null )
            {
                return null;
            }
            resultSet = statement.executeQuery( "SELECT * FROM TBL_DOS" );
            if( resultSet == null )
            {
                return null;
            }
            dosList = new ArrayList<>();
            while( resultSet.next() )
            {
                dos = new Dos();
                dos.setId( resultSet.getInt(1) );
                dos.setCampo1(resultSet.getInt(2) );
                dos.setCampo2(resultSet.getString(3) );
                dosList.add(dos);
            }
            resultSet.close();
            closeConnection(connection);
            return dosList;
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
        return null;
    }
    
    public static boolean addDos( Dos dos )
    {
        Connection connection = null;        
        String sql = null;
        PreparedStatement preparedStatement = null;
        int row = 0;
        try 
        {
            if( dos == null || dos.getCampo1() == null || dos.getCampo2() == null )
            {
                return false;
            }
            connection = getConnection( );
            if( connection == null )
            {
                return false;
            }
            sql = "insert into tbl_dos(campo1,campo2) values( ? , ? )";
            preparedStatement = connection.prepareStatement(sql);
            if( preparedStatement == null )
            {
                return false;
            }
            preparedStatement.setInt(1, dos.getCampo1() );
            preparedStatement.setString(2, dos.getCampo2());
            row = preparedStatement.executeUpdate();
            if( row == 0 )
            {
                return false;
            }
            preparedStatement.close();
            closeConnection(connection);
            return true;
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
        return false;
    }
    
    public static Dos getDosById( Integer id )
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Dos dos = null;
        String sql = "SELECT * FROM TBL_DOS where id = ?";
        
        try 
        {
            connection = getConnection( );
            if( connection == null )
            {
                return null;
            }
            preparedStatement = connection.prepareStatement(sql);
            if( preparedStatement == null )
            {
                return null;
            }
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if( resultSet == null )
            {
                return null;
            }
            while( resultSet.next() )
            {
                dos = new Dos();
                dos.setId( resultSet.getInt(1) );
                dos.setCampo1(resultSet.getInt(2) );
                dos.setCampo2(resultSet.getString(3) );
            }
            preparedStatement.close();
            resultSet.close();
            closeConnection(connection);
            return dos;
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
        return null;
    }
    
    public static boolean updateDos( Dos dos )
    {
        Connection connection = null;        
        String sql = null;
        PreparedStatement preparedStatement = null;
        int row = 0;
        try 
        {
            if( dos == null || dos.getCampo1() == null || dos.getCampo2() == null || dos.getId() == null )
            {
                return false;
            }
            connection = getConnection( );
            if( connection == null )
            {
                return false;
            }
            sql = "update tbl_dos set campo1= ?,campo2=? where id = ?";
            preparedStatement = connection.prepareStatement(sql);
            if( preparedStatement == null )
            {
                return false;
            }
            preparedStatement.setInt(1, dos.getCampo1() );
            preparedStatement.setString(2, dos.getCampo2());
            preparedStatement.setInt(3, dos.getId());
            row = preparedStatement.executeUpdate();
            if( row == 0 )
            {
                return false;
            }
            preparedStatement.close();
            closeConnection(connection);
            return true;
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
        return false;
    }
    
    public static boolean deleteDos( Integer id )
    {
        Connection connection = null;        
        String sql = null;
        PreparedStatement preparedStatement = null;
        int row = 0;
        try 
        {
            if( id == null || id == 0 )
            {
                return false;
            }
            connection = getConnection( );
            if( connection == null )
            {
                return false;
            }
            sql = "delete from tbl_dos where id = ?";
            preparedStatement = connection.prepareStatement(sql);
            if( preparedStatement == null )
            {
                return false;
            }
            preparedStatement.setInt(1, id);
            row = preparedStatement.executeUpdate();
            if( row == 0 )
            {
                return false;
            }
            preparedStatement.close();
            closeConnection(connection);
            return true;
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
        return false;
    }
    
    public static List<Rol> getRolList( )
    {
        List<Rol>rolList = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        Rol rol = null;
        
        try 
        {
            connection = getConnection( );
            if( connection == null )
            {
                return null;
            }
            statement = connection.createStatement( );
            if( statement == null )
            {
                return null;
            }
            resultSet = statement.executeQuery( "SELECT * FROM TBL_ROL" );
            if( resultSet == null )
            {
                return null;
            }
            rolList = new ArrayList<>();
            while( resultSet.next() )
            {
                rol = new Rol();
                rol.setRol( resultSet.getString(1) );
                rol.setDescripcion(resultSet.getString(2) );
                rolList.add(rol);
            }
            resultSet.close();
            closeConnection(connection);
            return rolList;
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
        return null;
    }
    
    public static boolean addRol( Rol rol )
    {
        Connection connection = null;        
        String sql = null;
        PreparedStatement preparedStatement = null;
        int row = 0;
        try 
        {
            if( rol == null || rol.getRol() == null || rol.getRol() == null )
            {
                return false;
            }
            connection = getConnection( );
            if( connection == null )
            {
                return false;
            }
            sql = "insert into tbl_rol(rol,descripcion) values( ? , ? )";
            preparedStatement = connection.prepareStatement(sql);
            if( preparedStatement == null )
            {
                return false;
            }
            preparedStatement.setString(1, rol.getRol() );
            preparedStatement.setString(2, rol.getDescripcion());
            row = preparedStatement.executeUpdate();
            if( row == 0 )
            {
                return false;
            }
            preparedStatement.close();
            closeConnection(connection);
            return true;
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
        return false;
    }
    
    public static Rol getRolByRol( String rol1 )
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Rol rol = null;
        String sql = "SELECT * FROM TBL_ROL where rol = ?";
        
        try 
        {
            connection = getConnection( );
            if( connection == null )
            {
                return null;
            }
            preparedStatement = connection.prepareStatement(sql);
            if( preparedStatement == null )
            {
                return null;
            }
            preparedStatement.setString(1, rol1);
            resultSet = preparedStatement.executeQuery();
            if( resultSet == null )
            {
                return null;
            }
            while( resultSet.next() )
            {
                rol = new Rol();
                rol.setRol( resultSet.getString(1) );
                rol.setDescripcion(resultSet.getString(2) );
            }
            preparedStatement.close();
            resultSet.close();
            closeConnection(connection);
            return rol;
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
        return null;
    }
    
    public static boolean updateRol( Rol rol )
    {
        Connection connection = null;        
        String sql = null;
        PreparedStatement preparedStatement = null;
        int row = 0;
        try 
        {
            if( rol == null || rol.getRol() == null || rol.getDescripcion() == null)
            {
                return false;
            }
            connection = getConnection( );
            if( connection == null )
            {
                return false;
            }
            sql = "update tbl_rol set rol= ?, descripcion=? where id = ?";
            preparedStatement = connection.prepareStatement(sql);
            if( preparedStatement == null )
            {
                return false;
            }
            preparedStatement.setString(1, rol.getRol());
            preparedStatement.setString(2, rol.getDescripcion());
            row = preparedStatement.executeUpdate();
            if( row == 0 )
            {
                return false;
            }
            preparedStatement.close();
            closeConnection(connection);
            return true;
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
        return false;
    }
    
    public static boolean deleteRol( String rol )
    {
        Connection connection = null;        
        String sql = null;
        PreparedStatement preparedStatement = null;
        int row = 0;
        try 
        {
            if( rol == null)
            {
                return false;
            }
            connection = getConnection( );
            if( connection == null )
            {
                return false;
            }
            sql = "delete from tbl_rol where rol = ?";
            preparedStatement = connection.prepareStatement(sql);
            if( preparedStatement == null )
            {
                return false;
            }
            preparedStatement.setString(1, rol);
            row = preparedStatement.executeUpdate();
            if( row == 0 )
            {
                return false;
            }
            preparedStatement.close();
            closeConnection(connection);
            return true;
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
        return false;
    }
    
    public static List<Usuario> getUsuarioList( )
    {
     List<Usuario>usuarioList = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        Usuario usuario = null;
        
        try 
        {
            connection = getConnection( );
            if( connection == null )
            {
                return null;
            }
            statement = connection.createStatement( );
            if( statement == null )
            {
                return null;
            }
            resultSet = statement.executeQuery( "SELECT * FROM TBL_USUARIO" );
            if( resultSet == null )
            {
                return null;
            }
            usuarioList = new ArrayList<>();
            while( resultSet.next() )
            {
                usuario = new Usuario();
                usuario.setUser( resultSet.getString(1) );
                usuario.setNombre(resultSet.getString(2) );
                usuario.setCorreo(resultSet.getString(3) );
                usuario.setPassword(resultSet.getString(4) );
                usuarioList.add(usuario);
            }
            resultSet.close();
            closeConnection(connection);
            return usuarioList;
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
        return null;
    }
    
    public static boolean addUsuario( Usuario usuario )
    {
        Connection connection = null;        
        String sql = null;
        PreparedStatement preparedStatement = null;
        int row = 0;
        try 
        {
            if( usuario == null || usuario.getUser() == null || usuario.getNombre() == null )
            {
                return false;
            }
            connection = getConnection( );
            if( connection == null )
            {
                return false;
            }
            sql = "insert into tbl_usuario(user,nombre,correo,password) values( ? , ? )";
            preparedStatement = connection.prepareStatement(sql);
            if( preparedStatement == null )
            {
                return false;
            }
            preparedStatement.setString(1, usuario.getUser() );
            preparedStatement.setString(2, usuario.getNombre());
            preparedStatement.setString(3, usuario.getCorreo() );
            preparedStatement.setString(4, usuario.getPassword());
            row = preparedStatement.executeUpdate();
            if( row == 0 )
            {
                return false;
            }
            preparedStatement.close();
            closeConnection(connection);
            return true;
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
        return false;
    }
    
    public static Usuario getUsuarioByUsuario( String usuario1 )
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Usuario usuario = null;
        String sql = "SELECT * FROM TBL_USUARIO where user = ?";
        
        try 
        {
            connection = getConnection( );
            if( connection == null )
            {
                return null;
            }
            preparedStatement = connection.prepareStatement(sql);
            if( preparedStatement == null )
            {
                return null;
            }
            preparedStatement.setString(1, usuario1);
            resultSet = preparedStatement.executeQuery();
            if( resultSet == null )
            {
                return null;
            }
            while( resultSet.next() )
            {
                usuario = new Usuario();
                usuario.setUser( resultSet.getString(1) );
                usuario.setNombre(resultSet.getString(2) );
                usuario.setCorreo( resultSet.getString(3) );
                usuario.setPassword(resultSet.getString(4) );
            }
            preparedStatement.close();
            resultSet.close();
            closeConnection(connection);
            return usuario;
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
        return null;
    }
    
    public static boolean updateUsuario( Usuario usuario )
    {
        Connection connection = null;        
        String sql = null;
        PreparedStatement preparedStatement = null;
        int row = 0;
        try 
        {
            if( usuario == null || usuario.getUser() == null || usuario.getNombre() == null || usuario.getCorreo() == null || usuario.getPassword() == null)
            {
                return false;
            }
            connection = getConnection( );
            if( connection == null )
            {
                return false;
            }
            sql = "update tbl_usuario set user= ?, nombre=?, correo=?, password=?  where user = ?";
            preparedStatement = connection.prepareStatement(sql);
            if( preparedStatement == null )
            {
                return false;
            }
            preparedStatement.setString(1, usuario.getUser());
            preparedStatement.setString(2, usuario.getNombre());
            preparedStatement.setString(3, usuario.getCorreo());
            preparedStatement.setString(4, usuario.getPassword());
            row = preparedStatement.executeUpdate();
            if( row == 0 )
            {
                return false;
            }
            preparedStatement.close();
            closeConnection(connection);
            return true;
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
        return false;
    }
    
    public static boolean deleteUsuario( String usuario )
    {
        Connection connection = null;        
        String sql = null;
        PreparedStatement preparedStatement = null;
        int row = 0;
        try 
        {
            if( usuario == null)
            {
                return false;
            }
            connection = getConnection( );
            if( connection == null )
            {
                return false;
            }
            sql = "delete from tbl_usuario where user = ?";
            preparedStatement = connection.prepareStatement(sql);
            if( preparedStatement == null )
            {
                return false;
            }
            preparedStatement.setString(1, usuario);
            row = preparedStatement.executeUpdate();
            if( row == 0 )
            {
                return false;
            }
            preparedStatement.close();
            closeConnection(connection);
            return true;
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
        return false;
    }   
}
