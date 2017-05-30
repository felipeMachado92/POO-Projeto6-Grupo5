package br.com.fatecpg.dao;

import br.com.fatecpg.quiz.ConnectionFactory;
import br.com.fatecpg.quiz.Resposta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/*@author Felipe*/
public class RespostaDao {
    
      private Connection connection;
      public RespostaDao() throws Exception{
        this.connection = new ConnectionFactory().getConnection();
    }
      public void insereResposta(Resposta resposta) throws SQLException{
        String sql ="INSERT INTO RESPOSTA (ID_RESPOSTA, ID_USUARIO, ID_ALTERNATIVA)"
                + "VALUES(?,?,?)";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1,resposta.getIdResposta());
            stmt.setInt(2,resposta.getUsuario().getIdUsuario());
            stmt.setInt(3,resposta.getAlternativa().getIdAlternativa());
            
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
