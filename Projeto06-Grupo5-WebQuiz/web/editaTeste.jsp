<%-- 
    Document   : editaTeste
    Created on : 30/05/2017, 04:08:31
    Author     : Felipe
--%>

<%@page import="br.com.fatecpg.quiz.Teste"%>
<%@page import="br.com.fatecpg.dao.TesteDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%try{
            int id = Integer.parseInt(request.getParameter("id"));
            TesteDao daoT = new TesteDao();
            Teste test = new Teste();
            test = daoT.pegaTeste(id);%>
            <input type="text" name="nomeTeste"> 
        <%}catch(Exception ex){%>
        <h3>Erro: <%=ex.getMessage()%></h3>
        <%}%>
    </body>
</html>
