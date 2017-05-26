package java.br.com.fatecpg.quiz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*@author Felipe*/
public class ConnectionFactory {
    public Connection getConnection() throws Exception{
        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            return DriverManager.getConnection("jdbc:derby://localhost:1527/quizdb", "root", "root");
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
}
