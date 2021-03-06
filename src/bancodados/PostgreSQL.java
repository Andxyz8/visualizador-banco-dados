/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bancodados;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author Anderson
 */
public final class PostgreSQL extends BancoDados {

    public PostgreSQL() {
    }

    public PostgreSQL(String user, String pass, String dbName) {
        super(user, pass, dbName);
        super.url = criaUrlApropriada();
        criaConexao();
    }

    @Override
    protected String criaUrlApropriada() {
        return "jdbc:postgresql://localhost:5432/" + super.getNomeBanco()
                + "?useTimezone=true&serverTimezone=UTC";
    }

    @Override
    protected DefaultMutableTreeNode geraSubArvoreTabelas(DefaultMutableTreeNode pai) throws SQLException {
        String query, coluna;

        query = "SELECT tablename "
                + "FROM pg_catalog.pg_tables "
                + "WHERE schemaname != 'pg_catalog' "
                + "AND schemaname != 'information_schema';";
        coluna = "tablename";

        return geraArvore(query, coluna, pai);
    }

    @Override
    protected DefaultMutableTreeNode geraSubArvoreCamposTabela(DefaultMutableTreeNode pai) throws SQLException {
        ResultSet rst;
        String query, coluna;
        ArrayList<String> pkeys = new ArrayList<>();

        query = "SELECT column_name, constraint_name FROM information_schema.key_column_usage WHERE table_name = \'" + pai.toString() + "\';";

        try ( Statement stmt = getConnection().createStatement()) {
            rst = stmt.executeQuery(query);
            while (rst.next()) {
                if (!rst.getString(2).replaceAll("pkey", "").equals(rst.getString(2))) {
                    pkeys.add(rst.getString(1));
                }
            }
        }

        query = "SELECT column_name, data_type, character_maximum_length, numeric_precision "
                + "FROM information_schema.columns WHERE table_name = \'" + pai.toString() + "\';";
        coluna = "column_name";

        try ( Statement stmt = getConnection().createStatement()) {
            rst = stmt.executeQuery(query);
            while (rst.next()) {
                String nomeCampo = formataCampo(rst, rst.getString(coluna), pkeys);
                DefaultMutableTreeNode no = new DefaultMutableTreeNode(nomeCampo);
                pai.add(no);
            }
        }
        rst.close();
        return pai;
    }

    /**
     * Realiza a formata????o da String que ser?? exibida na JTree da janela
     * principal para melhor visualiza????o. Adiciona indicadores dos tipos dos
     * campos seus tamanhos e tamb??m se o campo se trata de uma chave prim??ria
     * ou outro tipo de chave.
     *
     * @param rs Objeto ResultSet apontando para o campo em quest??o.
     * @param campo Nome do campo que ser?? formatado.
     * @param pkeys Exclusivo da implementa????o para o PostgreSQL. Chaves
     * prim??rias da tabela.
     * @return String do campo em quest??o devidamente formatada para exibi????o.
     * @throws SQLException Est?? sendo tratada no escopo de execu????o inicial.
     */
    private String formataCampo(ResultSet rs, String campo, ArrayList<String> pkeys) throws SQLException {
        String campoFormatado = "", tipoF = "";

        tipoF += rs.getString("data_type").toUpperCase();
        if (rs.getString(4) != null) {
            tipoF += "(" + rs.getString(4) + ")";
        } else {
            tipoF += "(" + rs.getString(3) + ")";
        }

        if (isChavePrimaria(pkeys, campo)) {
            campoFormatado += "{PK} ";
        }

        campoFormatado += campo + " [" + tipoF + "]";
        return campoFormatado;
    }

    /**
     * Fun????o espec??fica da implementa????o do PostgreSQL para verifcar se o campo
     * an??lisado ?? uma chave prim??ria ou n??o.
     *
     * @param pkeys Chaves prim??rias existentes na tabela.
     * @param campo campo a ser comparado com as chaves prim??rias j?? encontradas
     * da tabela.
     * @return true se o campo for uma chave prim??ria, false caso contr??rio.
     */
    private boolean isChavePrimaria(ArrayList<String> pkeys, String campo) {
        for (String pk : pkeys) {
            if (pk.equals(campo)) {
                return true;
            }
        }

        return false;
    }

    @Override
    protected DefaultMutableTreeNode geraSubArvoreViews(DefaultMutableTreeNode views) throws SQLException {
        String query = "SELECT table_name "
                + "FROM information_schema.views "
                + "WHERE table_schema = 'public'";
        String coluna = "table_name";
        
        return geraArvore(query, coluna, views);
    }

    @Override
    protected DefaultMutableTreeNode geraSubArvoreCamposViews(DefaultMutableTreeNode pai) throws SQLException {
        ResultSet rst;
        String query, coluna;
        ArrayList<String> pkeys = new ArrayList<>();

        query = "SELECT column_name, constraint_name FROM information_schema.key_column_usage WHERE table_name = '" + pai.toString() + "';";

        try ( Statement stmt = getConnection().createStatement()) {
            rst = stmt.executeQuery(query);
            while (rst.next()) {
                if (!rst.getString(2).replaceAll("pkey", "").equals(rst.getString(2))) {
                    pkeys.add(rst.getString(1));
                }
            }
        }

        query = "SELECT column_name, data_type, character_maximum_length, numeric_precision "
                + "FROM information_schema.columns WHERE table_name = \'" + pai.toString() + "\';";
        coluna = "column_name";

        try ( Statement stmt = getConnection().createStatement()) {
            rst = stmt.executeQuery(query);
            while (rst.next()) {
                String nomeCampo = formataCampo(rst, rst.getString(coluna), pkeys);
                DefaultMutableTreeNode no = new DefaultMutableTreeNode(nomeCampo);
                pai.add(no);
            }
        }

        rst.close();
        return pai;
    }

    @Override
    public String getInfoConexao() {
        return super.montaInfoConexao("PostgreSQL");
    }

}
