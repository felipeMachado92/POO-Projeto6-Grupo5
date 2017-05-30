package br.com.fatecpg.dao;

import br.com.fatecpg.quiz.ConnectionFactory;
import br.com.fatecpg.quiz.Partida;
import br.com.fatecpg.quiz.Questao;
import br.com.fatecpg.quiz.Teste;
import br.com.fatecpg.quiz.TipoUsuario;
import br.com.fatecpg.quiz.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/*@author Felipe*/
public class QuestaoDao {
    private Connection connection;
    public QuestaoDao() throws Exception{
        this.connection = new ConnectionFactory().getConnection();
    }
    
    public void insereQuestao(Questao questao) throws SQLException{
        String sql ="INSERT INTO QUESTAO (ID_QUESTAO,TEXTO_QUESTAO, ID_TESTE)"
                + "VALUES(?,?,?)";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1,questao.getIdQuestao());
            stmt.setString(2,questao.getTextoQuestao());
            stmt.setInt(3,questao.getTeste().getIdTeste());
            
            stmt.execute();
            stmt.close();
        } catch(SQLException e){
            throw new RuntimeException(e);
        } finally{
            if(!connection.isClosed()){
                connection.close();
            }
        }
    }
    
    
    public List<Questao> pegaQuestoes(Teste teste) throws SQLException{
        try{
            List<Questao> questoes = new ArrayList<Questao>();
            PreparedStatement stmt = this.connection.
                    prepareStatement("SELECT * FROM QUESTAO WHERE ID_TESTE=?");
            stmt.setInt(1, teste.getIdTeste());
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Questao question = new Questao();
                question.setIdQuestao(rs.getInt("ID_QUESTAO"));
                question.setTextoQuestao(rs.getString("TEXTO_QUESTAO"));
                
                PreparedStatement stmt2 = this.connection.prepareStatement("SELECT * FROM TESTE WHERE ID_TESTE=?");
                stmt2.setInt(1,question.getTeste().getIdTeste());
                ResultSet rs2 = stmt2.executeQuery();
                
                Teste test = new Teste();
                test.setIdTeste(rs2.getInt("ID_TESTE"));
                test.setNomeTeste(rs2.getString("NM_TESTE"));
                test.setDescTeste(rs2.getString("DESC_TESTE"));
                
                question.setTeste(test);
                questoes.add(question);
            }
            rs.close();
            stmt.close();
            return questoes;
                    
        } catch(SQLException e){
            throw new RuntimeException(e);
        } finally{
            if(!connection.isClosed()){
                connection.close();
            }
        }
    }
    
    public void alteraQuestao(Questao questao) throws SQLException{
        try{
            PreparedStatement stmt = connection
                    .prepareStatement("UPDATE QUESTAO SET TEXTO_QUESTAO=?, ID_TESTE=? WHERE ID_QUESTAO=?");
            stmt.setString(1,questao.getTextoQuestao());
            stmt.setInt(2,questao.getTeste().getIdTeste());
            stmt.setInt(3, questao.getIdQuestao());
            
            stmt.execute();
            stmt.close();
        } catch(SQLException e){
            throw new RuntimeException(e);
        } finally{
            if(!connection.isClosed()){
                connection.close();
            }
        }
    }
    
    public void excluiQuestao(Questao questao) throws SQLException{
        try{
            PreparedStatement stmt = connection.prepareStatement("DELETE FROM QUESTAO WHERE ID=?");
            
            stmt.execute();
            stmt.close();
        } catch(SQLException e){
            throw new RuntimeException(e);
        } finally{
            if(!connection.isClosed()){
                connection.close();
            }
        }
    }
}
