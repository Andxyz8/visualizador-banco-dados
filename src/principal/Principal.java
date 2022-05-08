package principal;

import gui.Conexao;

/**
 *
 * @author Anderson
 */
public class Principal {
    int a = 1;
    /**
     * Método principal de execução do programa.
     * 
     * @param args Parâmetros de linha de comando.
     */
    public static void main(String[] args) {
        Conexao janela = new Conexao();
        janela.setVisible(true);
    }
    
}
