package exportar;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import org.json.JSONObject;

/**
 * Classe que controla as ações de exportação dos dados consultados no banco de
 * dados que foi acessados.
 *
 * @author Anderson
 */
public class Exportador {

    protected JTable tabela;
    protected String caminho;
    protected FileWriter escritor;

    /**
     * Construtor padrão da classe Exportador.
     */
    public Exportador() {
    }

    /**
     * Atualiza os registro para serem exportados.
     * 
     * @param tabela Registros em uma JTable a serem exportados.
     * @param caminho Caminho para salver o arquivo gerado.
     */
    public void setDadosExportar(JTable tabela, String caminho) {
        this.tabela = tabela;
        this.caminho = caminho;

        try {
            this.escritor = new FileWriter(new File(caminho));
        } catch (IOException ioe) {
            System.out.println("Erro: " + ioe);
        }
    }

    /**
     * Realiza a exportação dos dados consultados de acordo com o formato
     * escolhido (.csv).
     *
     * @return true se a exportação foi bem sucedida, false caso contrário.
     */
    public boolean exportarCSV() {
        try {
            String str = "";

            TableModel model = tabela.getModel();

            for (int i = 0; i < model.getColumnCount(); i++) {
                str += (model.getColumnName(i) + ",");
            }

            escritor.write(str.substring(0, str.length() - 1) + "\n");
            str = "";

            for (int i = 0; i < model.getRowCount(); i++) {
                for (int j = 0; j < model.getColumnCount(); j++) {
                    str += (model.getValueAt(i, j).toString() + ",");
                }
                escritor.write(str.substring(0, str.length() - 1) + "\n");
                str = "";
            }
            escritor.close();
            return true;
        } catch (IOException e) {
            System.out.println(e);
        }
        return false;
    }

    /**
     * Realiza a exportação dos dados consultados de acordo com o formato
     * escolhido (.JSON).
     *
     * @return true se a exportação foi bem sucedida, false caso contrário.
     */
    public boolean exportarJSON() {
        try {
            boolean firstRow = true;
            JSONObject json = new JSONObject();

            escritor.write("[");

            for (int i = 0; i < tabela.getRowCount(); i++) {
                for (int j = 0; j < tabela.getColumnCount(); j++) {
                    Object value = tabela.getValueAt(i, j);
                    String columnName = tabela.getColumnName(j);
                    json.put(columnName, value);
                }
                escritor.write(firstRow ? json.toString() : (",\n" + json.toString()));
                firstRow = false;
            }
            escritor.write("]");
            escritor.close();
        } catch (IOException e1) {
            System.out.println(e1);
        }
        return true;
    }
}
