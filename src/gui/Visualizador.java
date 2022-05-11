/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gui;

import db.BancoDados;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.JOptionPane;

/**
 * Esta classe permite visualizar a estrutura do banco de dados através de uma
 * árvore, bem como realizar query's básicas e visualizar o seu resultado.
 *
 * @author Anderson
 */
public class Visualizador extends javax.swing.JFrame {

    private BancoDados banco;

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
     *  Constrói a árvore da estrutura do banco e exibe no JForm principal.
     */
    private void geraArvoreEstrutura() {
        JTree arvore = new JTree(banco.getArvoreEstrutura());
        arvore.setEditable(false);
        arvore.setShowsRootHandles(true);
        jScrollPaneArvore.setViewportView(arvore);
    }

    /**
     *  Exibe as informações de conexão no JForm principal.
     */
    private void setInfoConexao() {
        jTextPaneStatus.setEditable(false);
        jTextPaneStatus.setText(banco.getInfoConexao());
    }
    
    /**
     *  
     */
    private void geraTabelaConsulta(String query) {
        
        JTable tabela = this.banco.gerarConsulta(query);
        jScrollPaneTabela.setViewportView(tabela);
        tabela.setFillsViewportHeight(true);
        
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
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jButtonExecutar = new javax.swing.JButton();
        jScrollPaneArvore = new javax.swing.JScrollPane();
        jScrollPaneTabela = new javax.swing.JScrollPane();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

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

        jScrollPane2.setViewportView(jTextPane1);

        javax.swing.GroupLayout jPanelQueryLogLayout = new javax.swing.GroupLayout(jPanelQueryLog);
        jPanelQueryLog.setLayout(jPanelQueryLogLayout);
        jPanelQueryLogLayout.setHorizontalGroup(
            jPanelQueryLogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
        );
        jPanelQueryLogLayout.setVerticalGroup(
            jPanelQueryLogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
        );

        jButtonExecutar.setText("Executar");
        jButtonExecutar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExecutarActionPerformed(evt);
            }
        });

        jButton1.setText("Limite Registros");
        jButton1.setActionCommand("limRegs");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Exportar");
        jButton2.setActionCommand("export");

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
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelTabela)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 378, Short.MAX_VALUE)
                        .addComponent(jButton2)))
                .addContainerGap())
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
                                .addComponent(jButton1)
                                .addComponent(jButton2)))
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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String nome;
        StringBuilder mensagem = new StringBuilder();

        nome = JOptionPane.showInputDialog("Digite seu nome:");
        
        
        mensagem.append("Bem-vindo ").append(nome).append("!");
        JOptionPane.showMessageDialog(null, mensagem);
        this.banco.getConsulta().setLimiteLinhas(Integer.parseInt(nome));
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButtonExecutar;
    private javax.swing.JLabel jLabelArvore;
    private javax.swing.JLabel jLabelQuery;
    private javax.swing.JLabel jLabelQueryLog;
    private javax.swing.JLabel jLabelStatus;
    private javax.swing.JLabel jLabelTabela;
    private javax.swing.JPanel jPanelQueryLog;
    private javax.swing.JPanel jPanelStatus;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPaneArvore;
    private javax.swing.JScrollPane jScrollPaneQuery;
    private javax.swing.JScrollPane jScrollPaneTabela;
    private javax.swing.JTextArea jTextAreaQuery;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JTextPane jTextPaneStatus;
    // End of variables declaration//GEN-END:variables
}
