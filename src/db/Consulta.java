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

    public Consulta() {

    }

    public JTable Consulta(String query, Connection connection) {
        String resultQuery = "";
        String[] columnNames = null;
        String[][] data1 = null;
        //ArrayList<String> t = new ArrayList<>(); // Array de tamanho variavel?
        try {
            Statement resultados1 = connection.createStatement();
            this.resultados = resultados1.executeQuery("select * from autor");
            ResultSetMetaData resultadosMetadata = this.resultados.getMetaData();
            int totalRegs = 10;
            int aux = 0;

            columnNames = new String[resultadosMetadata.getColumnCount()];
            data1 = new String[totalRegs][resultadosMetadata.getColumnCount()];
  
            while (this.resultados.next()) {
                int i = 1;
                while (i < (resultadosMetadata.getColumnCount() + 1)) {
                    try {
                        data1[aux][i-1] = this.resultados.getString(i);
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
        }

        JTable tabela = new JTable(data1, columnNames);

        return tabela;
    }
}
