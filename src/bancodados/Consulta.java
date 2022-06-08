package bancodados;

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

    private ResultSet resultados;
    private int limiteLinhas;

    /**
     * Construtor padrão da classe Consulta.
     */
    public Consulta() {
        this.limiteLinhas = 1000;
    }

    /**
     * Gera uma JTable no formato para ser inserida no JForm principal do
     * Visualizador como resultado de uma consulta.
     *
     * @param query Query a ser realizada para gerar a tabela de registros.
     * @param connection Dados da conexão em um objeto Connection.
     * @param tela JForm principal do Visualizador para update do log de
     * execução das querys. (retirar daqui posteriormente).
     * @return
     */
    public JTable geraTabelaConsulta(String query, Connection connection, Visualizador tela) {
        String[] columnNames;
        String[][] data;
        int colunas;
        int numRegistros = 0;

        try {
            Statement resultados1 = connection.createStatement();
            this.resultados = resultados1.executeQuery(query);

            ResultSetMetaData resultadosMetadata = this.resultados.getMetaData();
            colunas = resultadosMetadata.getColumnCount();

            columnNames = new String[colunas];
            data = new String[getLimiteLinhas()][resultadosMetadata.getColumnCount()];

            while (this.resultados.next() && numRegistros <= limiteLinhas) {
                int i = 1;
                while (i < (resultadosMetadata.getColumnCount() + 1)) {
                    try {
                        data[numRegistros][i - 1] = this.resultados.getString(i);
                        columnNames[i - 1] = resultadosMetadata.getColumnName(i);
                    } catch (SQLException e) {
                        break;
                    }
                    i++;
                }
                numRegistros++;
            }
        } catch (SQLException sql1) {
            tela.atualizaLog("<b style=\"color:red\">Erro durante a execução da query.</b>" + sql1);
            JTable tabela = new JTable();
            return tabela;
        }

        JTable tabela = new JTable(getSomenteInfos(data, --numRegistros, colunas), columnNames);

        tela.atualizaLog("<b style=\"color:green\">Query executada com sucesso.</b>");

        return tabela;
    }

    /**
     * Gera uma matriz de Strings com as informações da consulta realizada, onde
     * cada linha da matriz representa um registro e cada coluna um campo.
     *
     * @param dadosNaoFormatados Dados não formatados provenientes da consulta
     * realizada.
     * @param linhas Quantidade de registros.
     * @param colunas Quantidade de campos.
     * @return Matriz de String com as informações da consulta realizada.
     */
    private String[][] getSomenteInfos(String[][] dadosNaoFormatados, int linhas, int colunas) {
        String[][] infos = new String[linhas][colunas];

        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                if (dadosNaoFormatados[i][j] != null) {
                    infos[i][j] = dadosNaoFormatados[i][j];
                } else {
                    infos[i][j] = "null";
                }
            }
        }

        return infos;
    }

    /**
     * Atribui um novo valor ao limite de registros que podem ser exibidos no
     * resultado de uma consulta.
     *
     * @param limiteLinhas Valor a ser atribuído para o limite de registros a
     * serem exibidos.
     */
    public void setLimiteLinhas(int limiteLinhas) {
        this.limiteLinhas = limiteLinhas;
    }

    /**
     * Retorna o valor do limite de registros que podem ser exibidos no
     * resultado de uma consulta.
     *
     * @return O valor do limite de registros atual.
     */
    public int getLimiteLinhas() {
        return limiteLinhas+1;
    }

}
