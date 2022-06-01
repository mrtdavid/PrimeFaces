<%-- 
    Document   : UnoList
    Created on : 12/05/2022, 01:56:02 PM
    Author     : Alumno
--%>

<%@page import="org.gerdoc.dao.Marca"%>
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
            List<Marca>marcaList = null;
            marcaList = MySqlConnection.getMarcaList();
            if( marcaList == null || marcaList.size() == 0 )
            {
                System.out.println("Error base vacia");
                return;
            }
        %>
        <table BORDER="1">
            <tr>
                <td>
                    NOMBRE
                </td>
                <td>
                    DESCRIPCION
                </td>
                <td>
                    URL
                </td>
            </tr>
            <%
            for( Marca marca : marcaList)
            {
            %>
            <tr>
                <td>
                    <%=marca.getNombre()%>
                </td>
                <td>
                    <%=marca.getDescripcion()%>
                </td>
                <td>
                    <%=marca.getUrl()%>
                </td>
            </tr>
            <%
            }
            %>
        </table>
    </body>
</html>
