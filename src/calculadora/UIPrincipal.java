/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculadora;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author giuli
 */
public class UIPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form UIPrincipal
     */
    AnalisadorLexico al = new AnalisadorLexico();
    public UIPrincipal() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        expressaoField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        startButton = new javax.swing.JButton();
        errorMessage = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        openFileButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Giulia Campos e Weslley Campos");

        jLabel1.setText("Insira aqui a expressão: ");

        startButton.setText("Analisar");
        startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startButtonActionPerformed(evt);
            }
        });

        errorMessage.setForeground(new java.awt.Color(255, 0, 0));
        errorMessage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Simbolo", "Análise"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(table);

        openFileButton.setText("Abrir Arquivo");
        openFileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openFileButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(errorMessage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                            .addComponent(expressaoField, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(18, 18, 18)
                        .addComponent(startButton)
                        .addGap(79, 79, 79)
                        .addComponent(openFileButton)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jSeparator1)
            .addGroup(layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(81, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(expressaoField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(startButton)
                    .addComponent(openFileButton))
                .addGap(18, 18, 18)
                .addComponent(errorMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void startButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startButtonActionPerformed
        try{
            al.cleanLexemas(); //limpa os lexemas para não exibir os antigos
            errorMessage.setText(""); //apaga a msg de erro
            DefaultTableModel model = (DefaultTableModel) table.getModel(); //pega a tabela
            
            if(table.getRowCount() > 0){ //se tem elementos na tabela, remove
                model.getDataVector().removeAllElements();
            }
            
            String exp = expressaoField.getText();     
            al.analizarExpressao(exp);
            
            expressaoField.setBackground(Color.green); //expressão valida
            ArrayList<Lexema> lexemasProduzidos = al.lexemas;
            
            for (int i = 0; i < lexemasProduzidos.size(); i++) {
                Object[] linha = new Object[2];
                linha[0] = lexemasProduzidos.get(i).descIdentificador;
                linha[1] = lexemasProduzidos.get(i).indentificador;
                model.addRow(linha); //adiciona linha a linha na tabela
            }
        }catch(Error e){
            String msg = e.toString(); 
            errorMessage.setText(msg); //exibir msg de erro
            expressaoField.setBackground(Color.red); //input vermelho
        }
    }//GEN-LAST:event_startButtonActionPerformed

    private void openFileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openFileButtonActionPerformed
        JFileChooser jFile = new JFileChooser();
        int returnVal = jFile.showOpenDialog(this);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = jFile.getSelectedFile();
            String a[] = file.getAbsolutePath().split("\\.");

            if ("txt".equals(a[a.length - 1])) {
                try{
                    String data = new String(Files.readAllBytes(file.toPath())); //Armazena os elementos do arquivo
                    String linhas[] = data.split("\r\n"); //Separa as linhas do arquivo
                    DefaultTableModel model = (DefaultTableModel) table.getModel(); //pega a tabela

                    for (String linha : linhas) {
                        try{
                            al.analizarExpressao(linha);
                            ArrayList<Lexema> lexemasProduzidos = al.lexemas;
                            
                            if(table.getRowCount() > 0){ //se tem elementos na tabela, remove
                                model.getDataVector().removeAllElements();
                            }
                            
                            for (int i = 0; i < lexemasProduzidos.size(); i++) {
                                Object[] linhaTable = new Object[2];
                                linhaTable[0] = lexemasProduzidos.get(i).descIdentificador;
                                linhaTable[1] = lexemasProduzidos.get(i).indentificador;
                                model.addRow(linhaTable); //adiciona linha a linha na tabela
                            }
                        }catch(Error e){
                            String msg = e.toString(); 
                            errorMessage.setText(msg); //exibir msg de erro
                        }
                    }
                }catch(IOException ex) {
                    JOptionPane.showMessageDialog(this, "Erro na leitura do arquivo " + ex);
                }
            }
            else{
                JOptionPane.showMessageDialog(this, "Erro na leitura do arquivo, deve ser do tipo txt");
            }
        }
    }//GEN-LAST:event_openFileButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UIPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UIPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UIPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UIPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UIPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel errorMessage;
    private javax.swing.JTextField expressaoField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton openFileButton;
    private javax.swing.JButton startButton;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
