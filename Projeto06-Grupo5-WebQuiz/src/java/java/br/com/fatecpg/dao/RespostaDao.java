package java.br.com.fatecpg.dao;

import java.br.com.fatecpg.quiz.Resposta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/*@author Felipe*/
public class RespostaDao {
    
      private Connection connection;
      
      public void insereResposta(Resposta resposta) throws SQLException{
        String sql ="INSERT INTO RESPOSTA (ID_USUARIO, ID_ALTERNATIVA)"
                + "VALUES(?,?)";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1,resposta.getUsuario().getIdUsuario());
            stmt.setInt(2,resposta.getAlternativa().getIdAlternativa());
            
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
