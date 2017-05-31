<%-- 
    Document   : teste
    Created on : 30/05/2017, 03:18:27
    Author     : Felipe
--%>

<%@page import="br.com.fatecpg.quiz.Alternativa"%>
<%@page import="br.com.fatecpg.dao.AlternativaDao"%>
<%@page import="br.com.fatecpg.quiz.Teste"%>
<%@page import="br.com.fatecpg.dao.TesteDao"%>
<%@page import="br.com.fatecpg.dao.QuestaoDao"%>
<%@page import="br.com.fatecpg.quiz.Questao"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%int id =0;//= Integer.parseInt(request.getParameter("id"));
        int i = 0;
        TesteDao daoT = new TesteDao();
        Teste test = daoT.pegaTeste(id);
        QuestaoDao daoQ = new QuestaoDao();
        AlternativaDao daoA = new AlternativaDao();%>
        
        <h1><%=test.getNomeTeste()%></h1>
        <hr/>
        <%for(Questao q: daoQ.pegaQuestoes(test)){%>
            <input type="hidden" name="questao" value="<%=q.getTextoQuestao()%>">
            <%for(Alternativa a: daoA.pegaAlternativas(q)){%>
                <input type="radio" name="questao<%=i%>" value="<%=a.getTextoAlternativa()%>"><%=a.getTextoAlternativa()%>
                i++;
            <%}%>
        <%}%>
    </body>
</html>
