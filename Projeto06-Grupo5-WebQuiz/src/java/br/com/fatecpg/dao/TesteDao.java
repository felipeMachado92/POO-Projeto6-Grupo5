package br.com.fatecpg.dao;

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
    
    
    public List<Teste> pegaTestes() throws SQLException{
        try{
            List<Teste> testes = new ArrayList<Teste>();
            PreparedStatement stmt = this.connection.
                    prepareStatement("SELECT * FROM TESTE");
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Teste test  = new Teste();
                test.setIdTeste(rs.getInt("ID_TESTE"));
                test.setNomeTeste(rs.getString("NM_TESTE"));
                test.setDescTeste(rs.getString("DESC_TESTE"));
                
                PreparedStatement stmt2 = this.connection.prepareStatement("SELECT * FROM PARTIDA WHERE ID_PARTIDA=?");
                stmt2.setInt(1,test.getPartida().getIdPartida());
                ResultSet rs2 = stmt2.executeQuery();
                
                Partida match = new Partida();
                match.setIdPartida(rs2.getInt("ID_PARTIDA"));
                match.setPontuacao(rs2.getDouble("PONTUACAO"));
                Calendar data = Calendar.getInstance();
                data.setTime(rs2.getDate("DATA_HORA"));
                match.setDataHora(data);
                
                PreparedStatement stmt3 = this.connection.prepareStatement("SELECT * FROM USUARIO WHERE ID_USUARIO=?");
                stmt3.setInt(1,match.getUsuario().getIdUsuario());
                ResultSet rs3 = stmt3.executeQuery();
                
                Usuario user = new Usuario();
                user.setIdUsuario(rs3.getInt("ID_USUARIO"));
                user.setNome(rs3.getString("NM_USUARIO"));
                user.setLogin(rs3.getString("LOGIN"));
                
                TipoUsuario tpUser = new TipoUsuario();
                tpUser.setIdTipoUsuario(rs3.getInt("ID_TIPO_USUARIO"));
                
                user.setTpUsuario(tpUser);
                match.setUsuario(user);
                test.setPartida(match);
                
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
