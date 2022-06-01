<%-- 
    Document   : UnoList
    Created on : 12/05/2022, 01:56:02 PM
    Author     : Alumno
--%>

<%@page import="org.gerdoc.dao.Producto"%>
<%@page import="org.gerdoc.dao.Dos"%>
<%@page import="org.gerdoc.dao.Uno"%>
<%@page import="java.util.List"%>
<%@page import="org.gerdoc.service.MySqlConnection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <%
            List<Producto>productoList = null;
            productoList = MySqlConnection.getProductoList();
            if( productoList == null || productoList.size() == 0 )
            {
                System.out.println("Error base vacia");
                return;
            }
        %>
        <table BORDER="1">
            <tr>
                <td>
                    ID
                </td>
                <td>
                    NOMBRE
                </td>
                <td>
                    DESCRIPCION
                </td>
                <td>
                    URL
                </td>
                <td>
                    PRECIO
                </td>
                <td>
                    COSTO
                </td>
            </tr>
            <%
            for( Producto producto : productoList)
            {
            %>
            <tr>
                <td>
                    <%=producto.getId()%>
                </td>
                <td>
                    <%=producto.getNombre()%>
                </td>
                <td>
                    <%=producto.getDescripcion()%>
                </td>
                <td>
                    <%=producto.getUrl()%>
                </td>
                <td>
                    <%=producto.getPrecio()%>
                </td>
                <td>
                    <%=producto.getCosto()%>
                </td>
            </tr>
            <%
            }
            %>
        </table>
    </body>
</html>
