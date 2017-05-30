package br.com.fatecpg.dao;

import br.com.fatecpg.quiz.Alternativa;
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

/**
 *
 * @author Felipe
 */
public class AlternativaDao {
    private Connection connection;
    
    public void insereAlternativa(Alternativa alternativa) throws SQLException{
        String sql ="INSERT INTO ALTERNATIVA (TEXTO_ALTERNATIVA, TIPO)"
                + "VALUES(?,?)";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1,alternativa.getTextoAlternativa());
            stmt.setBoolean(2,alternativa.isTipo());
            
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
    
    
    public List<Alternativa> pegaAlternativas(Alternativa alternativa) throws SQLException{
        try{
            List<Alternativa> alternativas = new ArrayList<Alternativa>();
            PreparedStatement stmt = this.connection.
                    prepareStatement("SELECT * FROM ALTERNATIVA WHERE ID_ALTERNATIVA=?");
                    stmt.setInt(1,alternativa.getIdAlternativa());
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                
                Alternativa alt  = new Alternativa();
                alt.setIdAlternativa(rs.getInt("ID_ALTERNATIVA"));
                alt.setTextoAlternativa(rs.getString("TEXTO_ALTERNATIVA"));
                alt.setTipo(rs.getBoolean("TIPO"));                
                
                PreparedStatement stmt2 = this.connection.prepareStatement("SELECT * FROM QUESTAO WHERE ID_QUESTAO=?");
                stmt2.setInt(1,alt.getQuestao().getIdQuestao());
                ResultSet rs2 = stmt2.executeQuery();
                
                Questao question = new Questao();
                question.setIdQuestao(rs2.getInt("ID_QUESTAO"));
                question.setTextoQuestao(rs2.getString("TEXTO_QUESTAO"));
                
                PreparedStatement stmt3 = this.connection.prepareStatement("SELECT * FROM TESTE WHERE ID_TESTE=?");
                stmt3.setInt(1,question.getTeste().getIdTeste());
                ResultSet rs3 = stmt3.executeQuery();
                
                Teste test = new Teste();
                test.setIdTeste(rs3.getInt("ID_TESTE"));
                test.setNomeTeste(rs3.getString("NM_TESTE"));
                test.setDescTeste(rs3.getString("DESC_TESTE"));
                
                question.setTeste(test);
                alt.setQuestao(question);
                
                alternativas.add(alt);
            }
            rs.close();
            stmt.close();
            return alternativas;
                    
        } catch(SQLException e){
            throw new RuntimeException(e);
        } finally{
            if(!connection.isClosed()){
                connection.close();
            }
        }
    }
    
    public void alteraAlternativa(Alternativa alternativa) throws SQLException{
        try{
            PreparedStatement stmt = connection
                    .prepareStatement("UPDATE ALTERNATIVA SET TEXTO_ALTERNATIVA=?, TIPO=? WHERE ID=?");
            stmt.setString(1,alternativa.getTextoAlternativa());
            stmt.setBoolean(2,alternativa.isTipo());
            stmt.setInt(3, alternativa.getIdAlternativa());
            
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
    
    public void excluiAlternativa(Alternativa alternativa) throws SQLException{
        try{
            PreparedStatement stmt = connection.prepareStatement("DELETE FROM USUARIO WHERE ID=?");
            
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
