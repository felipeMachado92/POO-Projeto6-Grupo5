package java.br.com.fatecpg.dao;

import java.br.com.fatecpg.quiz.Partida;
import java.sql.Connection;
import java.sql.Date;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*@author Felipe */
public class PartidaDao {
    private Connection connection;
    
    public void inserePartida(Partida partida) throws SQLException{
        String sql ="INSERT INTO PARTIDA (PONTUACAO, DATA_HORA, ID_USUARIO, ID_TESTE)"
                + "VALUES(?,?,?,?)";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setDouble(1,partida.getPontuacao());
            stmt.setDate(2, new Date(partida.getDataHora().getTimeInMillis()));
            stmt.setInt(3,partida.getUsuario().getIdUsuario());
            stmt.setInt(4,partida.getTeste().getIdTeste());
            
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
    
    public Partida pegaUsuario(Partida partida) throws SQLException{
        try{
            
            PreparedStatement stmt = this.connection.
                    prepareStatement("SELECT * FROM USUARIO WHERE ID=?");
            stmt.setInt(1, partida.getIdPartida());
            ResultSet rs = stmt.executeQuery();
            
            if(rs.next()){
                Partida match  = new Partida();
                match.setIdPartida(rs.getInt("ID_USUARIO"));
                match.setPontuacao(rs.getDouble("PONTUACAO"));
                
                rs.close();
                stmt.close();
                return match;
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
}
