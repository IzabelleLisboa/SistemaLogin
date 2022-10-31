
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

    static void atualizaUtilizador(String nome, String morada, String email, String telefone, String nif, String pass) {
       
      String sql = "UPDATE utilizador SET nome=?, email=?, morada=?, telefone=?, nif=?,  password=? WHERE login=?";
    Connection conn=LigaBD.ligacao();
    PreparedStatement ps;
        try {
            ps = conn.prepareStatement(sql);
            ps=conn.prepareStatement(sql);
    ps.setString(1, nome);
    ps.setString(2, email);
    ps.setString(3,morada);
    ps.setInt(4, Integer.parseInt(telefone));
    ps.setInt(5, Integer.parseInt(nif));
    ps.setString(6,pass);
     ps.setString(7,Login.nomeUser);  
    //Executa a instrução
    int retorno = ps.executeUpdate();
    if(retorno > 0){
    System.out.printf("Novo registro alterado: ");
    }else{
    System.out.println("Não foi possível alterar os registros!");
    }
    
        } catch (SQLException ex) {
            Logger.getLogger(LigaBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }

    static void remove(int p) {
  try {
            PreparedStatement ps=null;
            Connection conexao = LigaBD.ligacao();
            ps = conexao.prepareStatement("DELETE FROM utilizador WHERE login ='" +Login.nomeUser+"'");
            ps.executeUpdate();
            System.out.println("Removed User :" + Login.nomeUser);
            System.out.println("DELETADO!!");
        } catch (SQLException e) {System.out.println("Error: " + e.getMessage()); }
}
}
