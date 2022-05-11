package db;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.util.ArrayList;
import javax.swing.JTable;

/**
 *
 * @author Anderson
 */
public class Consulta {

    private Statement statement;
    private ResultSet resultados;
    private int limiteLinhas;

    public Consulta() {
        this.limiteLinhas = 10;
    }

    public JTable Consulta(String query, Connection connection) {
        String resultQuery = "";
        String[] columnNames = null;
        String[][] data = null;

        try {
            Statement resultados1 = connection.createStatement();
            this.resultados = resultados1.executeQuery(query);
            ResultSetMetaData resultadosMetadata = this.resultados.getMetaData();
            int aux = 0;

            columnNames = new String[resultadosMetadata.getColumnCount()];
            data = new String[getLimiteLinhas()][resultadosMetadata.getColumnCount()];

            while (this.resultados.next()) {
                int i = 1;
                while (i < (resultadosMetadata.getColumnCount() + 1)) {
                    try {
                        data[aux][i - 1] = this.resultados.getString(i);
                        //resultQuery += this.resultados.getString(i) + " | ";
                        columnNames[i - 1] = resultadosMetadata.getColumnName(i);
                    } catch (Exception e) {
                        break;
                    }
                    i++;
                }
                aux++;
            }
        } catch (SQLException sql1) {
            System.out.println(sql1);
            JTable tabela = new JTable();
            return tabela;
        }

        JTable tabela = new JTable(data, columnNames);

        return tabela;
    }        
  
    /**
     * @param limiteLinhas the limiteLinhas to set
     */
    public void setLimiteLinhas(int limiteLinhas) {
        this.limiteLinhas = limiteLinhas;
    }
    
    /**
     * @return 
     */
    public int getLimiteLinhas() {
        return limiteLinhas;
    }

}
