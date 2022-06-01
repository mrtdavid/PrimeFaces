<%-- 
    Document   : UnoList
    Created on : 12/05/2022, 01:56:02 PM
    Author     : Alumno
--%>

<%@page import="org.gerdoc.dao.Rol"%>
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
            List<Rol>rolList = null;
            rolList = MySqlConnection.getRolList();
            if( rolList == null || rolList.size() == 0 )
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
            for( Rol rol : rolList)
            {
            %>
            <tr>
                <td>
                    <%=rol.getRol()%>
                </td>
                <td>
                    <%=rol.getDescripcion()%>
                </td>
            </tr>
            <%
            }
            %>
        </table>
    </body>
</html>
