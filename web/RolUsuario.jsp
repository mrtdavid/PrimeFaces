<%-- 
    Document   : UnoList
    Created on : 12/05/2022, 01:56:02 PM
    Author     : Alumno
--%>

<%@page import="org.gerdoc.dao.RolUsuario"%>
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
            List<RolUsuario>rolUsuarioList = null;
            rolUsuarioList = MySqlConnection.getRolUsuarioList();
            if( rolUsuarioList == null || rolUsuarioList.size() == 0 )
            {
                System.out.println("Error base vacia");
                return;
            }
        %>
        <table BORDER="1">
            <tr>
                <td>
                    ROL
                </td>
                <td>
                    DESCRIPCION
                </td>
            </tr>
            <%
            for( RolUsuario rolUsuario : rolUsuarioList)
            {
            %>
            <tr>
                <td>
                    <%=rolUsuario.getRol()%>
                </td>
                <td>
                    <%=rolUsuario.getDescripcion()%>
                </td>
            </tr>
            <%
            }
            %>
        </table>
    </body>
</html>
