package java.br.com.fatecpg.dao;

import java.br.com.fatecpg.quiz.Teste;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*@author Felipe */
public class TesteDao {
    private Connection connection;
    
    
    public void insereTeste(Teste teste) throws SQLException{
        String sql ="INSERT INTO TESTE (NM_TESTE, DESC_TESTE, ID_PARTIDA)"
                + "VALUES(?,?,?,?)";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1,teste.getNomeTeste());
            stmt.setString(2,teste.getDescTeste());
            stmt.setInt(3,teste.getPartida().getIdPartida());
            
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
    
    
    public Teste pegaTeste(Teste teste) throws SQLException{
        try{
            
            PreparedStatement stmt = this.connection.
                    prepareStatement("SELECT * FROM TESTE WHERE ID=?");
            stmt.setInt(1, teste.getIdTeste());
            ResultSet rs = stmt.executeQuery();
            
            if(rs.next()){
                Teste user  = new Teste();
                user.setIdTeste(rs.getInt("ID_TESTE"));
                user.setNomeTeste(rs.getString("NM_TESTE"));
                user.setDescTeste(rs.getString("DESC_TESTE"));
                
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
    
    public void alteraTeste(Teste teste) throws SQLException{
        try{
            PreparedStatement stmt = connection
                    .prepareStatement("UPDATE TESTE SET NM_TESTE=?, DESC_TESTE=?, ID_PARTIDA=? WHERE ID_TESTE=?");
            stmt.setString(1,teste.getNomeTeste());
            stmt.setString(2,teste.getDescTeste());
            stmt.setInt(3,teste.getPartida().getIdPartida());
            stmt.setInt(4, teste.getIdTeste());
            
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
    
    public void excluiTeste(Teste teste) throws SQLException{
        try{
            PreparedStatement stmt = connection.prepareStatement("DELETE FROM TESTE WHERE ID=?");
            
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
