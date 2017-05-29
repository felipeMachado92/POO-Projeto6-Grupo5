package java.br.com.fatecpg.dao;

import java.br.com.fatecpg.quiz.Alternativa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
    
    
    public Alternativa pegaAlternativa(Alternativa alternativa) throws SQLException{
        try{
            
            PreparedStatement stmt = this.connection.
                    prepareStatement("SELECT * FROM USUARIO WHERE ID=?");
            stmt.setInt(1, alternativa.getIdAlternativa());
            ResultSet rs = stmt.executeQuery();
            
            if(rs.next()){
                Alternativa user  = new Alternativa();
                user.setIdAlternativa(rs.getInt("ID_ALTERNATIVA"));
                user.setTextoAlternativa(rs.getString("TEXTO_ALTERNATIVA"));
                user.setTipo(rs.getBoolean("TIPO"));
                
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
