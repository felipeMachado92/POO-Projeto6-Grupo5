<%-- 
    Document   : rank
    Created on : 30/05/2017, 11:05:51
    Author     : Felipe
--%>

<%@page import="br.com.fatecpg.dao.PartidaDao"%>
<%@page import="br.com.fatecpg.quiz.Partida"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Rank das 10 maiores pontuações</h1>
        <%try{%>
            <% PartidaDao dao = new PartidaDao();
            List<Partida> list = dao.rankPartidas();%>
            <table border =1>
                <tr>
                    <th>Jogador</th>
                    <th>Pontuação</th>
                    <th>Data/Hora</th>
                </tr>
                <%for(Partida p: list){%>
                <tr>
                    <td><%= p.getUsuario().getNome()%></td>
                    <td><%= p.getPontuacao()%></td>
                    <td><%= p.getDataHora()%></td>
                </tr>
                <%}%>
            </table>
        <%}catch(Exception ex){%>
            <h3>Erro ao carregar a lista: <%= ex.getMessage()%></h3>
        <%}%>
    </body>
</html>