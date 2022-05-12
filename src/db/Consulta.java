package db;

import gui.Visualizador;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
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
        this.limiteLinhas = 1000;
    }

    public JTable geraTabelaConsulta(String query, Connection connection, Visualizador tela) {
        String[] columnNames;
        String[][] data;
        int colunas;
        int aux = 0;

        try {
            Statement resultados1 = connection.createStatement();
            this.resultados = resultados1.executeQuery(query);

            ResultSetMetaData resultadosMetadata = this.resultados.getMetaData();
            colunas = resultadosMetadata.getColumnCount();

            columnNames = new String[colunas];
            data = new String[getLimiteLinhas()][resultadosMetadata.getColumnCount()];

            while (this.resultados.next() && aux <= limiteLinhas) {
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
            tela.atualizaLog("<b style=\"color:red\">Erro durante a execução da query.</b>" + sql1);
            JTable tabela = new JTable();
            return tabela;
        }

        JTable tabela = new JTable(getDataOnly(data, --aux, colunas), columnNames);

        tela.atualizaLog("<b style=\"color:green\">Query executada com sucesso.</b>");
        
        return tabela;
    }

    private String[][] getDataOnly(String[][] dtNF, int linhas, int colunas) {
        String[][] dataOnly = new String[linhas][colunas];
        
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                if (dtNF[i][j] != null) {
                    dataOnly[i][j] = dtNF[i][j];
                } else {
                    dataOnly[i][j] = "null";
                }
            }
        }

        return dataOnly;
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
