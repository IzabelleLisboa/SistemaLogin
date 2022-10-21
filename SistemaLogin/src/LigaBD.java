
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class LigaBD {
    public static Connection ligacao(){
        String url= "jdbc:mysql://localhost:3306/rhcencal?useSSL=false&serverTimezone=UTC";
        String user="root";
        String password = "";
        Connection liga = null;
        try {
            liga = DriverManager.getConnection(url,user,password);
        
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Erro na tentativa de ligação á base de dados");
    }
        return liga;
    }
    public static void registraUtilizador(String nome,String email,String morada, int telefone,int nif,String login,String password) throws SQLException{
        
        String query = "INSERT INTO utilizador(nome,email,morada,telefone,nif,login,password)"+ "VALUES(?,?,?,?,?,?,?)";
        Connection liga = ligacao();
        PreparedStatement ps = liga.prepareStatement(query);
        ps.setString(1, nome);
        ps.setString(2, email);
        ps.setString(3, morada);
        ps.setInt(4, telefone);
        ps.setInt(5, nif);
        ps.setString(6, login);
        ps.setString(7, password);
        ps.execute();      
    }
   
}
