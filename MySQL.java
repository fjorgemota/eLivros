import java.io.*;
import java.sql.*; 
import javax.swing.*;

public class MySQL{
    Statement statement;    
    String user = "root";// Sete a senha do seu usuário MySQL aqui
    String pass = "suasenhaaqui";//sete a senha do seu MySQL aqui
    String database = "elivros";
    String host = "localhost";
   
    public MySQL()
    {
       String url = "jdbc:mysql://"+host+":3306/"+database;             

       try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url,user,pass);
            statement = conn.createStatement();
        } catch (ClassNotFoundException e){
            System.out.println("Driver MySQL não encontrado.");
        } catch (SQLException e){
            System.out.println("Erro na conexão com a base de dados: "+e);
        }        
    }

    public boolean executaInsert(String insert)
    {
        try {
            statement.execute(insert);
            return true;
        } catch (SQLException e){
            System.out.println("Erro na Inclusão "+e);
            return false;
        }
    }
    
    public boolean executaUpdate(String insert)
    {
        try {
            statement.execute(insert);
            return true;
        } catch (SQLException e){
            System.out.println("Erro na Inclusão "+e);
            return false;
        }
    }
    
    public boolean executaDelete(String delete)
    {
        try {
            statement.execute(delete);
            return true;
        } catch (SQLException e){
            System.out.println("Erro na exclusão "+e);
            return false;
        }
    }
    
    public ConjuntoResultados executaSelect(String select)
    {
        try {
            ResultSet rs = statement.executeQuery(select);
            ConjuntoResultados cr = new ConjuntoResultados(rs);
            return cr;
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, ""+e, "Erro no SELECT", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
            return null;
        }
    }       
    
}
