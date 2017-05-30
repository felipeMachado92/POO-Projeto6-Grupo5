package br.com.fatecpg.dao;

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
    
    
    public List<Questao> pegaQuestoes(Questao questao) throws SQLException{
        try{
            List<Questao> questoes = new ArrayList<Questao>();
            PreparedStatement stmt = this.connection.
                    prepareStatement("SELECT * FROM QUESTAO WHERE ID=?");
            stmt.setInt(1, questao.getIdQuestao());
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
                
                PreparedStatement stmt3 = this.connection.prepareStatement("SELECT * FROM PARTIDA WHERE ID_PARTIDA=?");
                stmt3.setInt(1,test.getPartida().getIdPartida());
                ResultSet rs3 = stmt3.executeQuery();
                
                Partida match = new Partida();
                match.setIdPartida(rs3.getInt("ID_PARTIDA"));
                match.setPontuacao(rs3.getDouble("PONTUACAO"));
                Calendar data = Calendar.getInstance();
                data.setTime(rs3.getDate("DATA_HORA"));
                match.setDataHora(data);
                
                PreparedStatement stmt4 = this.connection.prepareStatement("SELECT * FROM USUARIO WHERE ID_USUARIO=?");
                stmt4.setInt(1,match.getUsuario().getIdUsuario());
                ResultSet rs4 = stmt4.executeQuery();
                
                Usuario user = new Usuario();
                user.setIdUsuario(rs4.getInt("ID_USUARIO"));
                user.setNome(rs4.getString("NM_USUARIO"));
                user.setLogin(rs4.getString("LOGIN"));
                
                TipoUsuario tpUser = new TipoUsuario();
                tpUser.setIdTipoUsuario(rs4.getInt("ID_TIPO_USUARIO"));
                
                user.setTpUsuario(tpUser);
                match.setUsuario(user);
                test.setPartida(match);
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
