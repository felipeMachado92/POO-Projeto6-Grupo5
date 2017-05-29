package java.br.com.fatecpg.dao;

import java.br.com.fatecpg.quiz.Questao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*@author Felipe*/
public class QuestaoDao {
    private Connection connection;
    
    public void insereQuestao(Questao questao) throws SQLException{
        String sql ="INSERT INTO QUESTAO (TEXTO_QUESTAO, ID_TESTE)"
                + "VALUES(?,?)";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1,questao.getTextoQuestao());
            stmt.setInt(2,questao.getTeste().getIdTeste());
            
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
    
    
    public Questao pegaQuestao(Questao questao) throws SQLException{
        try{
            
            PreparedStatement stmt = this.connection.
                    prepareStatement("SELECT * FROM QUESTAO WHERE ID=?");
            stmt.setInt(1, questao.getIdQuestao());
            ResultSet rs = stmt.executeQuery();
            
            if(rs.next()){
                Questao user  = new Questao();
                user.setIdQuestao(rs.getInt("ID_QUESTAO"));
                user.setTextoQuestao(rs.getString("TEXTO_QUESTAO"));
                
                rs.close();
                stmt.close();
                return user;
            }
            rs.close();
            stmt.close();
            
        } catch(SQLException e){
            throw new RuntimeException(e);
        } finally{
            if(!connection.isClosed()){
                connection.close();
            }
        }
        return null;
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
