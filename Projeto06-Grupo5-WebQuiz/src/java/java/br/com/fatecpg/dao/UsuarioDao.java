package java.br.com.fatecpg.dao;

import java.br.com.fatecpg.quiz.TipoUsuario;
import java.br.com.fatecpg.quiz.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*@author Felipe*/
public class UsuarioDao {
    
    private Connection connection;
    
    
    public void insereUsuario(Usuario usuario) throws SQLException{
        String sql ="INSERT INTO USUARIO (NM_USUARIO, SENHA, LOGIN, ID_TIPO_USUARIO)"
                + "VALUES(?,?,?,?)";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1,usuario.getNome());
            stmt.setString(2,usuario.getSenha());
            stmt.setString(3,usuario.getLogin());
            stmt.setInt(4,usuario.getTpUsuario().getIdTipoUsuario());
            
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
    
    
    public Usuario pegaUsuario(Usuario usuario) throws SQLException{
        try{
            
            PreparedStatement stmt = this.connection.
                    prepareStatement("SELECT * FROM USUARIO WHERE ID=?");
            stmt.setInt(1, usuario.getIdUsuario());
            ResultSet rs = stmt.executeQuery();
            
            if(rs.next()){
                Usuario user  = new Usuario();
                user.setIdUsuario(rs.getInt("ID_USUARIO"));
                user.setNome(rs.getString("NM_USUARIO"));
                user.setLogin(rs.getString("LOGIN"));
                user.setSenha(rs.getString("SENHA"));
                
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
    
    public void alteraUsuario(Usuario usuario) throws SQLException{
        try{
            PreparedStatement stmt = connection
                    .prepareStatement("UPDATE USUARIO (NM_USUARIO, SENHA, LOGIN, ID_TIPO_USUARIO) VALUES(?,?,?,?)");
            stmt.setString(1,usuario.getNome());
            stmt.setString(2,usuario.getSenha());
            stmt.setString(3,usuario.getLogin());
            stmt.setInt(4,usuario.getTpUsuario().getIdTipoUsuario());
            stmt.setInt(5, usuario.getIdUsuario());
            
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
    
    public void excluiUsuario(Usuario usuario) throws SQLException{
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
