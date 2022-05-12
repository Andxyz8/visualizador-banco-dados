/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gui;

import db.BancoDados;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;
import org.json.JSONObject;

/**
 * Esta classe permite visualizar a estrutura do banco de dados através de uma
 * árvore, bem como realizar query's básicas e visualizar o seu resultado.
 *
 * @author Anderson
 */
public class Visualizador extends javax.swing.JFrame {

    private BancoDados banco;
    JTable tabela = new JTable();

    /**
     * Creates new form Visualizador
     */
    public Visualizador() {
        initComponents();
    }

    /**
     * Cria uma janela visualizadora dos dados do banco de dados com o banco de
     * dados passado por parâmetro.
     *
     * @param bd Objeto da classe BancoDados cujas informações serão vistas.
     */
    public Visualizador(BancoDados bd) {
        this();
        this.banco = bd;
        setInfoBanco();
        setInfoConexao();
    }

    /**
     * Atualiza as informações do form Visualizador para corresponder com
     * aquelas do banco conectado atualmente.
     */
    private void setInfoBanco() {
        this.setTitle(this.banco.getNomeBanco());
        geraArvoreEstrutura();
    }

    /**
     * Constrói a árvore da estrutura do banco e exibe no JForm principal.
     */
    private void geraArvoreEstrutura() {
        JTree arvore = new JTree(banco.getArvoreEstrutura());
        arvore.setEditable(false);
        arvore.setShowsRootHandles(true);
        jScrollPaneArvore.setViewportView(arvore);
    }

    /**
     * Exibe as informações de conexão no JForm principal.
     */
    private void setInfoConexao() {
        jTextPaneStatus.setEditable(false);
        jTextPaneStatus.setText(banco.getInfoConexao());
    }

    /**
     *
     */
    private void geraTabelaConsulta(String query) {
        this.tabela = this.banco.gerarConsulta(query, this);
        jScrollPaneTabela.setViewportView(tabela);
        tabela.setFillsViewportHeight(true);
    }

      public void atualizaLog(String str) {
        System.out.println("AQQQUUIIII");
        
        jTextPaneStatusQuery.setEditable(false);
        jTextPaneStatusQuery.setText(str);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelArvore = new javax.swing.JLabel();
        jLabelQuery = new javax.swing.JLabel();
        jScrollPaneQuery = new javax.swing.JScrollPane();
        jTextAreaQuery = new javax.swing.JTextArea();
        jLabelTabela = new javax.swing.JLabel();
        jLabelStatus = new javax.swing.JLabel();
        jPanelStatus = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPaneStatus = new javax.swing.JTextPane();
        jLabelQueryLog = new javax.swing.JLabel();
        jPanelQueryLog = new javax.swing.JPanel();
        jScrollPaneStatusQuery = new javax.swing.JScrollPane();
        jTextPaneStatusQuery = new javax.swing.JTextPane();
        jButtonExecutar = new javax.swing.JButton();
        jScrollPaneArvore = new javax.swing.JScrollPane();
        jScrollPaneTabela = new javax.swing.JScrollPane();
        jButtonLimiteRows = new javax.swing.JButton();
        jButtonExportar = new javax.swing.JButton();
        jRadioButtonCSV = new javax.swing.JRadioButton();
        jRadioButtonJSON = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabelArvore.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabelArvore.setText("Árvore de Componentes");

        jLabelQuery.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabelQuery.setText("Digite suas queries neste espaço.");

        jTextAreaQuery.setColumns(20);
        jTextAreaQuery.setRows(5);
        jScrollPaneQuery.setViewportView(jTextAreaQuery);

        jLabelTabela.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabelTabela.setText("Resultados das querys realizadas");

        jLabelStatus.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jLabelStatus.setText("Log de status da conexão");

        jPanelStatus.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTextPaneStatus.setContentType("text/html"); // NOI18N
        jScrollPane1.setViewportView(jTextPaneStatus);

        javax.swing.GroupLayout jPanelStatusLayout = new javax.swing.GroupLayout(jPanelStatus);
        jPanelStatus.setLayout(jPanelStatusLayout);
        jPanelStatusLayout.setHorizontalGroup(
            jPanelStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE)
        );
        jPanelStatusLayout.setVerticalGroup(
            jPanelStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );

        jLabelQueryLog.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jLabelQueryLog.setText("Log de Informações da execução de querys");

        jPanelQueryLog.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTextPaneStatusQuery.setContentType("text/html"); // NOI18N
        jScrollPaneStatusQuery.setViewportView(jTextPaneStatusQuery);

        javax.swing.GroupLayout jPanelQueryLogLayout = new javax.swing.GroupLayout(jPanelQueryLog);
        jPanelQueryLog.setLayout(jPanelQueryLogLayout);
        jPanelQueryLogLayout.setHorizontalGroup(
            jPanelQueryLogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPaneStatusQuery)
        );
        jPanelQueryLogLayout.setVerticalGroup(
            jPanelQueryLogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPaneStatusQuery, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
        );

        jButtonExecutar.setText("Executar");
        jButtonExecutar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExecutarActionPerformed(evt);
            }
        });

        jButtonLimiteRows.setText("Limite Rows");
        jButtonLimiteRows.setToolTipText("Valor padrão é 1000");
        jButtonLimiteRows.setActionCommand("limRegs");
        jButtonLimiteRows.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLimiteRowsActionPerformed(evt);
            }
        });

        jButtonExportar.setText("Exportar");
        jButtonExportar.setActionCommand("export");
        jButtonExportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExportarExportarActionPerformed(evt);
            }
        });

        jRadioButtonCSV.setText("CSV");
        jRadioButtonCSV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonCSVActionPerformed(evt);
            }
        });

        jRadioButtonJSON.setText("JSON");
        jRadioButtonJSON.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonJSONActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabelArvore)
                    .addComponent(jLabelStatus)
                    .addComponent(jPanelStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPaneArvore))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPaneQuery)
                            .addComponent(jPanelQueryLog, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPaneTabela)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelQueryLog)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabelQuery)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButtonExecutar)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelTabela)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonLimiteRows)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 252, Short.MAX_VALUE)
                        .addComponent(jButtonExportar)
                        .addGap(18, 18, 18)
                        .addComponent(jRadioButtonCSV)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jRadioButtonJSON)
                        .addGap(27, 27, 27))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelQuery)
                    .addComponent(jLabelArvore)
                    .addComponent(jButtonExecutar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPaneQuery, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabelTabela)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButtonLimiteRows)
                                .addComponent(jButtonExportar)
                                .addComponent(jRadioButtonCSV)
                                .addComponent(jRadioButtonJSON)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPaneTabela, javax.swing.GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE))
                    .addComponent(jScrollPaneArvore))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelQueryLog)
                    .addComponent(jLabelStatus))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanelQueryLog, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(8, 8, 8))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonExecutarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExecutarActionPerformed
        String query = jTextAreaQuery.getText();
        geraTabelaConsulta(query);

    }//GEN-LAST:event_jButtonExecutarActionPerformed

    private void jButtonLimiteRowsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLimiteRowsActionPerformed
        int limiteRows = 0;
        boolean erro = false;
        boolean valido = false;

        try {
            limiteRows = Integer.parseInt(JOptionPane.showInputDialog("Limite de rows por consulta (0, 1000]: "));
            valido = true;
        } catch (NumberFormatException e) {
            erro = true;
            JOptionPane.showMessageDialog(null, "Digite um valor válido!", "ERRO!", JOptionPane.ERROR_MESSAGE);
        }

        if (!erro && limiteRows <= 0 || limiteRows >= 1001) {
            valido = false;
            JOptionPane.showMessageDialog(null, "Digite um valor entre (0,1000]!", "ERRO!", JOptionPane.ERROR_MESSAGE);
        }

        if (valido) {
            this.banco.setLimiteRowsConsulta(limiteRows);
        }
    }//GEN-LAST:event_jButtonLimiteRowsActionPerformed

    private void jButtonExportarExportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExportarExportarActionPerformed
        if ((!this.jRadioButtonCSV.isSelected()) && (!this.jRadioButtonJSON.isSelected())) {
            JOptionPane.showMessageDialog(null, "Nenhum formato selecionado!", "ERRO!", JOptionPane.ERROR_MESSAGE);
        } else if (this.jRadioButtonCSV.isSelected()) {
            if (exportToCSV(tabela, ".\\tabela.csv")) {
                JOptionPane.showMessageDialog(null, "Dados exportados com sucesso para um arquivo CSV.", "Dados exportados", JOptionPane.PLAIN_MESSAGE);
            }
        } else if (this.jRadioButtonJSON.isSelected()) {
            if (exportToJSON(tabela, ".\\tabela.json")) {
                JOptionPane.showMessageDialog(null, "Dados exportados com sucesso para um arquivo JSON.", "Dados exportados", JOptionPane.PLAIN_MESSAGE);
            }
        }
    }//GEN-LAST:event_jButtonExportarExportarActionPerformed

    public static boolean exportToCSV(JTable tableToExport, String pathToExportTo) {
        try {
            String str = "";

            TableModel model = tableToExport.getModel();
            FileWriter csv = new FileWriter(new File(pathToExportTo));

            for (int i = 0; i < model.getColumnCount(); i++) {
                //csv.write(model.getColumnName(i) + ",");
                str += (model.getColumnName(i) + ",");
            }

            //csv.write("\n");
            csv.write(str.substring(0, str.length() - 1) + "\n");
            str = "";

            for (int i = 0; i < model.getRowCount(); i++) {
                for (int j = 0; j < model.getColumnCount(); j++) {
                    //csv.write(model.getValueAt(i, j).toString() + ",");
                    str += (model.getValueAt(i, j).toString() + ",");
                }
                //csv.write("\n");
                csv.write(str.substring(0, str.length() - 1) + "\n");
                str = "";
            }

            csv.close();
            return true;
        } catch (IOException e) {
            System.out.println(e);
        }
        return false;
    }

    public static boolean exportToJSON(JTable tableToExport, String pathToExportTo) {
        File file = new File(pathToExportTo);

        try ( FileWriter fw = new FileWriter(pathToExportTo)) {
            boolean firstRow = true;
            fw.write("[");
            for (int i = 0; i < tableToExport.getRowCount(); i++) {
                JSONObject jsonObj = new JSONObject();
                for (int j = 0; j < tableToExport.getColumnCount(); j++) {
                    Object value = tableToExport.getValueAt(i, j);
                    String columnName = tableToExport.getColumnName(j);

                    jsonObj.put(columnName, value);

                }
                fw.write(firstRow ? jsonObj.toString() : (",\n" + jsonObj.toString()));
                firstRow = false;

            }
            fw.write("]");
        } catch (IOException e1) {
            System.out.println(e1);
        }
        return true;
    }

    private void jRadioButtonJSONActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonJSONActionPerformed
        this.jRadioButtonJSON.setSelected(true);
        this.jRadioButtonCSV.setSelected(false);
    }//GEN-LAST:event_jRadioButtonJSONActionPerformed

    private void jRadioButtonCSVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonCSVActionPerformed
        this.jRadioButtonCSV.setSelected(true);
        this.jRadioButtonJSON.setSelected(false);
    }//GEN-LAST:event_jRadioButtonCSVActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonExecutar;
    private javax.swing.JButton jButtonExportar;
    private javax.swing.JButton jButtonLimiteRows;
    private javax.swing.JLabel jLabelArvore;
    private javax.swing.JLabel jLabelQuery;
    private javax.swing.JLabel jLabelQueryLog;
    private javax.swing.JLabel jLabelStatus;
    private javax.swing.JLabel jLabelTabela;
    private javax.swing.JPanel jPanelQueryLog;
    private javax.swing.JPanel jPanelStatus;
    private javax.swing.JRadioButton jRadioButtonCSV;
    private javax.swing.JRadioButton jRadioButtonJSON;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPaneArvore;
    private javax.swing.JScrollPane jScrollPaneQuery;
    private javax.swing.JScrollPane jScrollPaneStatusQuery;
    private javax.swing.JScrollPane jScrollPaneTabela;
    private javax.swing.JTextArea jTextAreaQuery;
    private javax.swing.JTextPane jTextPaneStatus;
    private javax.swing.JTextPane jTextPaneStatusQuery;
    // End of variables declaration//GEN-END:variables
}
