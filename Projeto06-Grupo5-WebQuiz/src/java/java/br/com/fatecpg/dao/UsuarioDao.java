package java.br.com.fatecpg.dao;

import java.br.com.fatecpg.quiz.TipoUsuario;
import java.br.com.fatecpg.quiz.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
}
