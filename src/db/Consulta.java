package db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Anderson
 */
public class Consulta  {
    private Statement statement;
    private ResultSet resultados;
    
    public Consulta(){
        
    }
    
    public Consulta(Statement stmt){
        this.statement = stmt;
    }
}
