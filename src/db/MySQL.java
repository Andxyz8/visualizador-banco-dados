/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author Anderson
 */
public final class MySQL extends BancoDados {

    public MySQL() {
    }

    public MySQL(String user, String pass, String dbName) {
        super(user, pass, dbName);
        super.url = criaUrlApropriada();
        super.criaConexao();
    }

    @Override
    protected String criaUrlApropriada() {
        return "jdbc:mysql://localhost/" + super.getNomeBanco()
                + "?useTimezone=true&serverTimezone=UTC";
    }

    @Override
    protected DefaultMutableTreeNode geraSubArvoreTabelas(DefaultMutableTreeNode pai) throws SQLException {
        String query = "SHOW FULL TABLES WHERE TABLE_TYPE LIKE 'BASE TABLE';";
        String coluna = "Tables_in_" + getNomeBanco();

        return geraArvore(query, coluna, pai);
    }

    @Override
    protected DefaultMutableTreeNode geraSubArvoreCamposTabela(DefaultMutableTreeNode pai) throws SQLException {
        ResultSet rst;

        String query = "DESC " + pai.toString() + ";";
        String coluna = "Field";

        try ( Statement stmt = getConnection().createStatement()) {
            rst = stmt.executeQuery(query);
            while (rst.next()) {
                String nomeCampo = formataCampo(rst, rst.getString(coluna));
                DefaultMutableTreeNode no = new DefaultMutableTreeNode(nomeCampo);
                pai.add(no);
            }
        }
        rst.close();
        return pai;
    }

    /**
     * Realiza a formatação da String que será exibida na JTree da janela
     * principal para melhor visualização. Adiciona indicadores dos tipos dos
     * campos seus tamanhos e também se o campo se trata de uma chave primária
     * ou outro tipo de chave.
     *
     * @param rs Objeto ResultSet apontando para o campo em questão.
     * @param campo Nome do campo que será formatado.
     * @return String do campo em questão devidamente formatada para exibição.
     * @throws SQLException Está sendo tratada no escopo de execução inicial.
     */
    protected String formataCampo(ResultSet rs, String campo) throws SQLException {
        String campoFormatado = "", tipoF = "", tipoNF;

        tipoNF = rs.getString("Type");
        if (tipoNF.contains("'")) {
            String aux = tipoNF.split("\\(")[0].toUpperCase();
            int pos = tipoNF.indexOf("(");
            tipoF += aux + tipoNF.substring(pos);
        } else {
            tipoF += tipoNF.toUpperCase();
        }

        if (rs.getString("Key").equals("PRI")) {
            campoFormatado += "{" + rs.getString("Key") + "} ";
        }
        campoFormatado += campo + " [" + tipoF + "]";
        return campoFormatado;
    }

    @Override
    protected DefaultMutableTreeNode geraSubArvoreViews(DefaultMutableTreeNode views) throws SQLException {
        String query = "SHOW FULL TABLES WHERE TABLE_TYPE LIKE 'VIEW';";
        String coluna = "Tables_in_" + getNomeBanco();

        return geraArvore(query, coluna, views);
    }

    @Override
    protected DefaultMutableTreeNode geraSubArvoreCamposViews(DefaultMutableTreeNode pai) throws SQLException {
        ResultSet rst;
        String query, coluna;

        query = "DESC " + pai.toString() + ";";
        coluna = "Field";

        try ( Statement stmt = getConnection().createStatement()) {
            rst = stmt.executeQuery(query);
            while (rst.next()) {
                String nomeCampo = formataCampo(rst, rst.getString(coluna));
                DefaultMutableTreeNode no = new DefaultMutableTreeNode(nomeCampo);
                pai.add(no);
            }
        }

        rst.close();
        return pai;
    }

    @Override
    public String getInfoConexao() {
        return super.montaInfoConexao("MySQL");
    }

}
