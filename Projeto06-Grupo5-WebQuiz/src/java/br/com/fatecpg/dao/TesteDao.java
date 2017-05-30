package br.com.fatecpg.dao;

import br.com.fatecpg.quiz.ConnectionFactory;
import br.com.fatecpg.quiz.Partida;
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

/*@author Felipe */
public class TesteDao {
    private Connection connection;
    public TesteDao() throws Exception{
        this.connection = new ConnectionFactory().getConnection();
    }
    
    public void insereTeste(Teste teste) throws SQLException{
        String sql ="INSERT INTO TESTE (NM_TESTE, DESC_TESTE, ID_PARTIDA)"
                + "VALUES(?,?,?,?)";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1,teste.getNomeTeste());
            stmt.setString(2,teste.getDescTeste());
            
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
    
    
    public List<Teste> pegaTestes() throws SQLException{
        try{
            List<Teste> testes = new ArrayList<>();
            PreparedStatement stmt = this.connection.
                    prepareStatement("SELECT * FROM TESTE");
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Teste test  = new Teste();
                test.setIdTeste(rs.getInt("ID_TESTE"));
                test.setNomeTeste(rs.getString("NM_TESTE"));
                test.setDescTeste(rs.getString("DESC_TESTE"));
                
                testes.add(test);
            }
            rs.close();
            stmt.close();
            return testes;
                    
        } catch(SQLException e){
            throw new RuntimeException(e);
        } finally{
            if(!connection.isClosed()){
                connection.close();
            }
        }
    }
    
    public void alteraTeste(Teste teste) throws SQLException{
        try{
            PreparedStatement stmt = connection
                    .prepareStatement("UPDATE TESTE SET NM_TESTE=?, DESC_TESTE=?, ID_PARTIDA=? WHERE ID_TESTE=?");
            stmt.setString(1,teste.getNomeTeste());
            stmt.setString(2,teste.getDescTeste());
            stmt.setInt(3, teste.getIdTeste());
            
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
