package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Anderson
 */
public class BancoDados {

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    private String usuario;
    private String senha;
    private String nomeBanco;
    private String url;
    private boolean isMysql;
    private boolean active = false;

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

        createConnection();

        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(BancoDados.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Cria a URL de conexão de acordo com o nome do banco de dados a se
     * conectar e o Sistema Gerenciador de Banco de Dados escolhido.
     *
     * @return URL de conexão apropriada de acordo com o BD e o SGBD.
     */
    private String criaUrlApropriada() {
        if (isMysql) {
            return "jdbc:mysql://localhost/" + this.nomeBanco + "?useTimezone=true&serverTimezone=UTC";
        }
        return "jdbc:postgresql://localhost:5432/" + this.nomeBanco + "?useTimezone=true&serverTimezone=UTC";
    }

    /**
     * Efetua a criação da conexão com o banco de dados de acordo com os
     * parâmetros passados.
     *
     * Usuário padrão MySQL -> root; Usuário Padrão PostgreSQL -> postgre.
     */
    private void createConnection() {
        try {
            System.out.println("Usuario: " + this.usuario);
            System.out.println("Senha: " + this.senha);
            this.connection = DriverManager.getConnection(this.url, this.usuario, this.senha);
            this.active = !connection.isClosed();
        } catch (SQLException ex) {
            System.out.println("Excessão ao criar a conexão com o banco de dados.\nExcessão: " + ex);
            System.exit(0);
        }
    }

    /**
     * Retorna o valor da variável active.
     *
     * @return Status da conexão com o BD.
     */
    public boolean isActive() {
        int a = 1;
        return this.active;
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
}
