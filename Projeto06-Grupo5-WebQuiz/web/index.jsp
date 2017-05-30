<%-- 
    Document   : index
    Created on : 29/05/2017, 21:28:48
    Author     : Felipe
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>WebQuiz</h1>
        <hr/>
        
        <%
            if(request.getParameter("inserir")!=null){
                try{
                    String nome = request.getParameter("nome");
                    String login = request.getParameter("login");
                    String senha = request.getParameter("senha");
                } catch(Exception ex){%>
                    <h3>Erro: Não vai da não!</h3>
                <%}
            }
        %>
        <h2>Bem vindo ao WebQuiz!</h2>
        <p>Nesse WebApp você poderá testar seus conhecimnetos em diversos testes criado pelos administradores.</p>
        <p>Também é possível verificar seu histórico de partidas no sistema além de acompanhar o rank com as 10 maiores pontuações.</p>
        <p>É necessário ter cadastro antes de poder realizar nossos testes.</p>
        <hr/>
        <h2>Login</h2>
        <form>
            <label>Nome</label>
            <input type="text" name="nome"><br/>
            <label>Login</label>
            <input type="text" name="login"><br/>
            <label>Senha</label>
            <input type="text" name="senha"><br/>
            <input type="submit" name="inserir" value="Casdastrar-se">
            <input type="submit" name="logar" value="Entrar">
        </form>
        <hr/>
        <h2>Equipe</h2>
        <h3>Felipe Machado</h3>
        <p>Até o momento é quem fez a p**** toda, mas quem sabe isso muda até quarta-feira?!</p>
    </body>
</html>
