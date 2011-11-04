import java.io.*;
import java.sql.*; 

class ConjuntoResultados{
    private ResultSet rs;
    
    public ConjuntoResultados(ResultSet rs){
        this.rs = rs;
    }
    
    public boolean next(){
        try{
            return rs.next();
        }catch(SQLException sqlError){
            System.out.println("Erro no SQL: "+sqlError);
            return false;
        }
    }
    
    public String getString(String t){
        try{
            return rs.getString(t);
        }catch(SQLException sqlError){
            System.out.println("Erro no SQL: "+sqlError);
            return "";
        }
    }
}