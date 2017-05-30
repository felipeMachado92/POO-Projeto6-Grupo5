<%-- 
    Document   : testes
    Created on : 29/05/2017, 23:18:58
    Author     : Felipe
--%>

<%@page import="java.util.List"%>
<%@page import="br.com.fatecpg.dao.TesteDao"%>
<%@page import="br.com.fatecpg.quiz.Teste"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Testes</h1>
        <%if(request.getParameter("deleta") != null){
            
        }%>
        <h2>Escolha o teste que deseja fazer</h2>
        <%try{%>
            <button><a href="novoTeste.jsp">Incluir Teste</a></button>
            <% TesteDao dao = new TesteDao();
            List<Teste> list = dao.pegaTestes();%>
            <table border =1>
                <%for(Teste t: list){%>
                <tr>
                    <td><%= t.getIdTeste()%> <%= t.getNomeTeste()%></td>
                </tr>
                <tr>
                    <td<%= t.getDescTeste()%></td
                </tr>
                <tr>
                    <td>
                        <button><a href="teste.jsp?id=<%=t.getIdTeste()%>">Iniciar</a></button>
                        <button><a href="editaTeste.jsp?id=<%=t.getIdTeste()%>">Editar</a></button>
                        <input type="submit" name="deleta" value="Excluir">
                    </td>
                </tr>
                <%}%>
            </table>
        <%}catch(Exception ex){%>
            <h3>Erro ao carregar a lista: <%= ex.getMessage()%></h3>
        <%}%>
    </body>
</html>
