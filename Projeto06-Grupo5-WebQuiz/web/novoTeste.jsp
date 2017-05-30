<%-- 
    Document   : novoTeste
    Created on : 30/05/2017, 15:15:29
    Author     : Felipe
--%>

<%@page import="br.com.fatecpg.dao.QuestaoDao"%>
<%@page import="br.com.fatecpg.dao.AlternativaDao"%>
<%@page import="br.com.fatecpg.quiz.Alternativa"%>
<%@page import="br.com.fatecpg.quiz.Questao"%>
<%@page import="br.com.fatecpg.dao.TesteDao"%>
<%@page import="br.com.fatecpg.quiz.Teste"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Novo teste</h1>
        <p>Preencha as informações para criar um novo teste</p>
        <%if(request.getParameter("inserir") != null){
            try{
                int idTeste = Integer.parseInt(request.getParameter("idTeste"));
                String nomeTeste = request.getParameter("nomeTeste");
                String descTeste = request.getParameter("descTeste");
                
                Teste t = new Teste();
                t.setIdTeste(idTeste);
                t.setNomeTeste(nomeTeste);
                t.setDescTeste(descTeste);
                
                TesteDao daoT = new TesteDao();
                daoT.insereTeste(t);
                
                for(int i=0; i < 3; i++){
                    int idQuestao = Integer.parseInt(request.getParameter("idQuestao"+i));
                    String textoQuestao = request.getParameter("textoQuestao"+i);
                    Questao q = new Questao();
                    q.setIdQuestao(idQuestao);
                    q.setTextoQuestao(textoQuestao);
                    q.setTeste(t);
                    
                    QuestaoDao daoQ = new QuestaoDao();
                    daoQ.insereQuestao(q);
                    for(int j = 0; j < 3; j++){
                        
                        String textoAlternativa = request.getParameter("textoAlternativa"+i+j);
                        boolean tipo;
                        if(request.getParameter("tipo"+i+j) == "true"){
                            tipo = true;
                        } else{
                            tipo = false;
                        }
                        
                        Alternativa a = new Alternativa();
                        a.setTextoAlternativa(textoAlternativa);
                        a.setTipo(tipo);
                        a.setQuestao(q);
                        
                        AlternativaDao daoA = new AlternativaDao();
                        daoA.insereAlternativa(a);
                    }
                }
                response.sendRedirect("testes.jsp");
            }catch(Exception ex){%>
                <h3>Erro: <%=ex.getMessage()%></h3>
            <%}
        } else if(request.getParameter("cancelar") != null) {
            response.sendRedirect("testes.jsp");
        }%>
        <form>
            <label>Id do Teste:</label><br/>
            <input type="text" name="idTeste" value="${param.idTeste}"><br/>
            <label>Nome do teste:</label><br/>
            <input type="text" name="nomeTeste" value="${param.nomeTeste}"><br/>
            <label>Descrição do teste</label><br/>
            <textarea name="descTeste" rows="4" cols="50" maxlength="200" value="${param.descTeste}"></textarea><br/>
            <hr/>
            <%for(int i = 0; i < 3; i++){%>
                <label>Questão <%=i+1%></label></br>
                <label>Id Questão</label><br/>
                <input type="text" name="idQuestao<%=i%>" value="${param.idQuestao}"><br/>
                <textarea name="textoQuestao<%=i%>" rows="5" cols="100" maxlength="500" value="${param.textoQuestao}"></textarea><br/>
                <%for(int j=0; j<3; j++){%>
                    <labe>Alternativa <%=j+1%></label></br>
                    <input type="text" name="textoAlternativa<%=i%><%=j%>" value="${param.textoAlternaiva}"/><br/>
                    <input type="radio" name="tipo<%=i%><%=j%>" value="true">certa
                    <input type="radio" name="tipo<%=i%><%=j%>" value="false">errada<br/>
                <%}%>
                <hr/>
            <%}%>
            <input type="submit" name="inserir" value="Criar">
            <input type="submit" name="cancela" value="Cancelar">
        </form>
    </body>
</html>
