package bancodados;

import gui.Visualizador;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author Anderson
 */
public abstract class BancoDados {

    protected Connection connection;
    protected Consulta consulta;
    protected String usuario;
    protected String senha;
    protected String nomeBanco;
    protected String url;
    protected boolean active = true;

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
     */
    public BancoDados(String user, String pass, String dbName) {
        this.usuario = user;
        this.senha = pass;
        this.nomeBanco = dbName;
        this.consulta = new Consulta();
    }

    /**
     * Efetua a criação da conexão com o banco de dados de acordo com os
     * parâmetros passados.
     *
     * Usuário padrão MySQL -> root; Usuário Padrão PostgreSQL -> postgre.
     */
    protected void criaConexao() {
        try {
            this.connection = DriverManager.getConnection(this.url, this.usuario, this.senha);
            setIsActive(!this.connection.isClosed());
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
        DefaultMutableTreeNode tables = new DefaultMutableTreeNode("TABLES");
        DefaultMutableTreeNode views = new DefaultMutableTreeNode("VIEWS");

        try {
            tables = geraSubArvoreTabelas(tables);
            
            for (int i = 0; i < tables.getChildCount(); i++) {
                DefaultMutableTreeNode no = (DefaultMutableTreeNode) tables.getChildAt(i);
                no = geraSubArvoreCamposTabela(no);
            }

            views = geraSubArvoreViews(views);
            for (int i = 0; i < views.getChildCount(); i++) {
                DefaultMutableTreeNode no = (DefaultMutableTreeNode) views.getChildAt(i);
                no = geraSubArvoreCamposViews(no);
            }

            raiz.add(tables);
            raiz.add(views);

        } catch (SQLException ex) {
            Logger.getLogger(BancoDados.class.getName()).log(Level.SEVERE, null, ex);
        }
        return raiz;
    }

    protected DefaultMutableTreeNode geraArvore(String query, String coluna, DefaultMutableTreeNode pai) throws SQLException {
        ResultSet rst;

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
     * Cria uma string de texto/html para exibição em campos de textos
     * formatados.
     *
     * @param nome_sgbdr Nome do SGBDR em que a conexão está estabelecida.
     * @return Texto/HTML formatado para ser exibido.
     */
    protected String montaInfoConexao(String nome_sgbdr) {
        String infos = "";
        infos += "<b>Banco de dados</b>: " + this.nomeBanco + "<br>"
                + "<b>Usuário</b>: " + this.usuario + "<br>"
                + "<b>SGBDR</b>: ";
        infos += nome_sgbdr + "<br>";

        infos += "<b>Status Conexão</b>: <b style=\"color:";
        
        infos += isActive() ? "green\">OK.</b>" : "red\">ERRO.</b>" ;

        return infos;
    }

    public JTable gerarConsulta(String queryConsulta, Visualizador tela) {
        JTable tabela = this.consulta.geraTabelaConsulta(queryConsulta, getConnection(), tela);
        return tabela;
    }

    /**
     * Retorna as informações sobre a conexão no banco de dados atual do
     * programa.
     *
     * @return String devidamente formatada com as informações sobre a conexão
     * atual do banco de dados.
     */
    public abstract String getInfoConexao();

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
     * Define o valor da variável que controla o limite de linhas a serem
     * exibidas por consulta.
     *
     * @param lr Novo limite de rows a ser atribuído.
     */
    public void setLimiteRowsConsulta(int lr) {
        this.consulta.setLimiteLinhas(lr);
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

    /**
     * Cria a URL de conexão de acordo com o nome do banco de dados a se
     * conectar e o Sistema Gerenciador de Banco de Dados Relacional escolhido.
     *
     * @return URL de conexão apropriada de acordo com o BD e o SGBD.
     */
    protected abstract String criaUrlApropriada();

    /**
     * Realiza o preenchimento dos nós no que se refere apenas às tabelas que
     * compõe o banco de dados, sem tratar dos atributos dessas tabelas.
     *
     * @param pai Nó que indica o banco ao qual a tabela pertence.
     * @return Objeto DefaultMutableTreeNode devidamente preenchido com as
     * tabelas componentes do banco de dados.
     * @throws SQLException Está sendo tratada no escopo de execução inicial.
     */
    protected abstract DefaultMutableTreeNode geraSubArvoreTabelas(DefaultMutableTreeNode pai) throws SQLException;

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
    protected abstract DefaultMutableTreeNode geraSubArvoreCamposTabela(DefaultMutableTreeNode pai) throws SQLException;

    protected abstract DefaultMutableTreeNode geraSubArvoreViews(DefaultMutableTreeNode views) throws SQLException;

    protected abstract DefaultMutableTreeNode geraSubArvoreCamposViews(DefaultMutableTreeNode pai) throws SQLException;

}
