package br.com.fatecpg.dao;

import br.com.fatecpg.quiz.Partida;
import br.com.fatecpg.quiz.TipoUsuario;
import br.com.fatecpg.quiz.Usuario;
import java.sql.Connection;
import java.sql.Date;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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
    
    public List<Partida> pegaPartidas() throws SQLException{
        try{
            List<Partida> partidas = new ArrayList<Partida>();
            PreparedStatement stmt = this.connection.
                    prepareStatement("SELECT * FROM PARTIDA");
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Partida match  = new Partida();
                match.setIdPartida(rs.getInt("ID_USUARIO"));
                match.setPontuacao(rs.getDouble("PONTUACAO"));
                Calendar data = Calendar.getInstance();
                data.setTime(rs.getDate("DATA_HORA"));
                match.setDataHora(data);
                
                PreparedStatement stmt2 = this.connection.prepareStatement("SELECT * FROM USUARIO WHERE ID_USUARIO=?");
                stmt2.setInt(1,match.getUsuario().getIdUsuario());
                ResultSet rs2 = stmt2.executeQuery();
                
                Usuario user = new Usuario();
                user.setIdUsuario(rs2.getInt("ID_USUARIO"));
                user.setNome(rs2.getString("NM_USUARIO"));
                user.setLogin(rs2.getString("LOGIN"));
                
                TipoUsuario tpUser = new TipoUsuario();
                tpUser.setIdTipoUsuario(rs2.getInt("ID_TIPO_USUARIO"));
                
                user.setTpUsuario(tpUser);
                match.setUsuario(user);
                
                partidas.add(match);
            }
            rs.close();
            stmt.close();
            return partidas;
                    
        } catch(SQLException e){
            throw new RuntimeException(e);
        } finally{
            if(!connection.isClosed()){
                connection.close();
            }
        }
    }
}