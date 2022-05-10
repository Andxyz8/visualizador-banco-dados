package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author Anderson
 */
public class BancoDados {

    private Connection connection;
    private String usuario;
    private String senha;
    private String nomeBanco;
    private String url;
    private boolean isMysql;
    private boolean active = true;

    public BancoDados() {
    }

    /**
     * Método construtor da classe BancoDados que efetua toda a atribuição dos
     * parâmetros passados aos atributos da classe.
     *
     * @param user Usuário que está tentando se conectar ao banco de dados.
     * @param pass Senha do respectivo user para conexão ao banco de dados
     * especificado.
     * @param dbName Nome do banco de dados a ser conectado.
     * @param isMysql Verdadeiro se o banco a ser visualizado é MySQL, falso
     * caso contrário.
     */
    public BancoDados(String user, String pass, String dbName, boolean isMysql) {
        this.usuario = user;
        this.senha = pass;
        this.nomeBanco = dbName;
        this.isMysql = isMysql;
        this.url = criaUrlApropriada();

        criaConexao();
    }

    /**
     * Cria a URL de conexão de acordo com o nome do banco de dados a se
     * conectar e o Sistema Gerenciador de Banco de Dados Relacional escolhido.
     *
     * @return URL de conexão apropriada de acordo com o BD e o SGBD.
     */
    private String criaUrlApropriada() {
        if (isMysql) {
            return "jdbc:mysql://localhost/" + getNomeBanco() + "?useTimezone=true&serverTimezone=UTC";
        }
        return "jdbc:postgresql://localhost:5432/" + getNomeBanco() + "?useTimezone=true&serverTimezone=UTC";
    }

    /**
     * Efetua a criação da conexão com o banco de dados de acordo com os
     * parâmetros passados.
     *
     * Usuário padrão MySQL -> root; Usuário Padrão PostgreSQL -> postgre.
     */
    private void criaConexao() {
        try {
            System.out.println("Usuario: " + this.usuario);
            System.out.println("Senha: " + this.senha);
            this.connection = DriverManager.getConnection(this.url, this.usuario, this.senha);
            setIsActive(!connection.isClosed());
        } catch (SQLException ex) {
            System.out.println("Excessão ao criar a conexão com o banco de dados.\nExcessão: " + ex);
            System.exit(0);
        }
    }

    /**
     * Função geradora da árvore que será exibida na barra esquerda da janela
     * principal.
     *
     * @return Árvore estrutural do banco de dados com tabelas e seus atributos.
     */
    @SuppressWarnings("UnusedAssignment")
    public DefaultMutableTreeNode getArvoreEstrutura() {
        DefaultMutableTreeNode raiz = new DefaultMutableTreeNode(getNomeBanco());

        try {
            raiz = (geraSubArvoreTabelas(raiz));

            for (int i = 0; i < raiz.getChildCount(); i++) {
                DefaultMutableTreeNode no = (DefaultMutableTreeNode) raiz.getChildAt(i);
                no = geraSubArvoreCampos(no);
            }

        } catch (SQLException ex) {
            Logger.getLogger(BancoDados.class.getName()).log(Level.SEVERE, null, ex);
        }
        return raiz;
    }

    /**
     * Realiza o preenchimento dos nós no que se refere apenas às tabelas que
     * compões o banco de dados, sem tratar dos atributos dessas tabelas.
     *
     * @param pai Nó que indica o banco ao qual a tabela pertence.
     * @return Objeto DefaultMutableTreeNode devidamente preenchido com as
     * tabelas componentes do banco de dados.
     * @throws SQLException Está sendo tratada no escopo de execução inicial.
     */
    private DefaultMutableTreeNode geraSubArvoreTabelas(DefaultMutableTreeNode pai) throws SQLException {
        ResultSet rst;
        String query = "SHOW TABLES;";
        String coluna = "Tables_in_" + getNomeBanco();

        try ( Statement stmt = getConnection().createStatement()) {
            rst = stmt.executeQuery(query);
            while (rst.next()) {
                String tabela = rst.getString(coluna);
                DefaultMutableTreeNode no = new DefaultMutableTreeNode(tabela);
                pai.add(no);
            }
        }
        rst.close();
        return pai;
    }

    /**
     * Realiza o preenchimento dos nós das tabelas com os seus campos
     * devidamente formatados.
     *
     * @param pai Nó que indica a tabela com as quais os campos serão
     * preenchidos.
     * @return Objeto DefaultMutableTreeNode devidamente preenchido com os
     * campos da tabela.
     * @throws SQLException Está sendo tratada no escopo de execução inicial.
     */
    private DefaultMutableTreeNode geraSubArvoreCampos(DefaultMutableTreeNode pai) throws SQLException {
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
//| Field   | Type | Null | Key | Default | Extra

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
    private String formataCampo(ResultSet rs, String campo) throws SQLException {
        String campoFormatado = "", tipoF = "";
        String tipoNF = rs.getString("Type");
        if (tipoNF.contains("'")) {
            String aux = tipoNF.split("\\(")[0].toUpperCase();
            int pos = tipoNF.indexOf("(");
            tipoF += aux + tipoNF.substring(pos);
        } else {
            tipoF += tipoNF.toUpperCase();
        }

        if (!rs.getString("Key").equals("")) {
            campoFormatado += "{" + rs.getString("Key") + "} ";
        }
        campoFormatado += campo + " [" + tipoF + "]";
        return campoFormatado;
    }

    /**
     * Retorna o objeto que possui as informações de conexão atual.
     *
     * @return Objeto connection de conexão ao banco de dados.
     */
    public Connection getConnection() {
        return this.connection;
    }
    
    /**
     * Retorna o valor da variável nomeBanco.
     *
     * @return Nome do banco de dados conectado.
     */
    public String getNomeBanco() {
        return this.nomeBanco;
    }

    /**
     * Retorna o valor da variável usuario.
     *
     * @return Nome do usuário conectado.
     */
    public String getUsuario() {
        return this.usuario;
    }

    /**
     * Retorna o valor da variável active.
     *
     * @return Status da conexão com o BD.
     */
    public boolean isActive() {
        return this.active;
    }

    /**
     * Altera o estado da variável indicadora da conexão com o banco.
     *
     * @param status Estado a ser atribuído à conexão com o banco.
     */
    private void setIsActive(boolean status) {
        this.active = status;
    }
}
