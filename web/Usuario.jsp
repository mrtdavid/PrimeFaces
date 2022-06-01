<%-- 
    Document   : UnoList
    Created on : 12/05/2022, 01:56:02 PM
    Author     : Alumno
--%>

<%@page import="org.gerdoc.dao.Usuario"%>
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
            List<Usuario>usuarioList = null;
            usuarioList = MySqlConnection.getUsuarioList();
            if( usuarioList == null || usuarioList.size() == 0 )
            {
                System.out.println("Error base vacia");
                return;
            }
        %>
        <table BORDER="1">
            <tr>
                <td>
                    USER
                </td>
                <td>
                    NOMBRE
                </td>
                <td>
                    CORREO
                </td>
                <td>
                    PASSWORD
                </td>
            </tr>
            <%
            for( Usuario usuario : usuarioList)
            {
            %>
            <tr>
                <td>
                    <%=usuario.getUser()%>
                </td>
                <td>
                    <%=usuario.getNombre()%>
                </td>
                <td>
                    <%=usuario.getCorreo()%>
                </td>
                <td>
                    <%=usuario.getPassword()%>
                </td>
            </tr>
            <%
            }
            %>
        </table>
    </body>
</html>
