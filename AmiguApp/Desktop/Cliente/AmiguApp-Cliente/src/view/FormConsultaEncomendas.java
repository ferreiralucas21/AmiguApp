/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import view.tableModel.EncomendaTableModel;
import modelDominio.Encomenda;
/**
 *
 * @author ADMIN
 */
public class FormConsultaEncomendas extends javax.swing.JDialog {

    private EncomendaTableModel encomendaModel;
    String status;
    
    public FormConsultaEncomendas() {
        initComponents();
        status = "";
        atualizaTabela(status);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jbtVoltar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jrbProcessamento = new javax.swing.JRadioButton();
        jrbFinalizado = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtEncomendas = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(226, 102, 63));

        jbtVoltar.setBackground(new java.awt.Color(226, 102, 63));
        jbtVoltar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagem/icons8-voltar-24 (1).png"))); // NOI18N
        jbtVoltar.setBorder(null);
        jbtVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtVoltarActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(226, 102, 63));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagem/icons8-produto-novo-24 (1).png"))); // NOI18N
        jButton1.setBorder(null);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(226, 102, 63));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagem/icons8-usuário-24 (1).png"))); // NOI18N
        jButton2.setBorder(null);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Source Sans Pro Semibold", 1, 26)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(250, 218, 218));
        jLabel1.setText("AmiguApp");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2)
                .addGap(18, 18, 18))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jbtVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(241, 241, 241)
                        .addComponent(jLabel1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jbtVoltar)
                .addGap(8, 8, 8)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Source Sans Pro Light", 1, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(226, 102, 63));
        jLabel2.setText("Encomendas");

        jrbProcessamento.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(jrbProcessamento);
        jrbProcessamento.setFont(new java.awt.Font("Source Sans Pro Light", 0, 12)); // NOI18N
        jrbProcessamento.setText("Em precessamento");
        jrbProcessamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbProcessamentoActionPerformed(evt);
            }
        });

        jrbFinalizado.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(jrbFinalizado);
        jrbFinalizado.setFont(new java.awt.Font("Source Sans Pro Light", 0, 12)); // NOI18N
        jrbFinalizado.setText("Finalizado");
        jrbFinalizado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbFinalizadoActionPerformed(evt);
            }
        });

        jtEncomendas.setBackground(new java.awt.Color(250, 218, 218));
        jtEncomendas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jtEncomendas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtEncomendasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtEncomendas);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jrbProcessamento)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jrbFinalizado)
                .addGap(103, 103, 103))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(255, 255, 255)
                        .addComponent(jLabel2)))
                .addContainerGap(82, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(jLabel2)
                .addGap(27, 27, 27)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jrbProcessamento)
                    .addComponent(jrbFinalizado))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(83, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jbtVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtVoltarActionPerformed
        dispose();
    }//GEN-LAST:event_jbtVoltarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        FormConsultaProdutos formConsultaProdutos = new FormConsultaProdutos();
        formConsultaProdutos.setModal(true);
        formConsultaProdutos.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        FormPerfilVendedor formPerfilVendedor = new FormPerfilVendedor(null);
        formPerfilVendedor.setModal(true);
        formPerfilVendedor.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jtEncomendasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtEncomendasMouseClicked
        System.out.println(jtEncomendas.getSelectedRow());
        Encomenda encomenda = encomendaModel.getEncomenda(jtEncomendas.getSelectedRow());
        FormEncomendaDatalhada formEncomendaDetalhada = new FormEncomendaDatalhada(encomenda);
        formEncomendaDetalhada.setModal(true);
        formEncomendaDetalhada.setVisible(true);
        atualizaTabela(status);
    }//GEN-LAST:event_jtEncomendasMouseClicked

    private void jrbProcessamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbProcessamentoActionPerformed
        status = "Em processamento";
        atualizaTabela(status);
    }//GEN-LAST:event_jrbProcessamentoActionPerformed

    private void jrbFinalizadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbFinalizadoActionPerformed
        status = "Enviado";
        atualizaTabela(status);
    }//GEN-LAST:event_jrbFinalizadoActionPerformed

    public void atualizaTabela(String status) {
        //if (jcbFiltro.getSelectedIndex() == 0) {
            encomendaModel = new EncomendaTableModel(AmiguAppCliente.ccont.encomendaLista(status));
        //} else {
       //     encomendaModel = new EncomendaTableModel(AmiguAppCliente.ccont.encomendaLista());
       // }
        
        jtEncomendas.setModel(encomendaModel);              
    }
    
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
            java.util.logging.Logger.getLogger(FormConsultaEncomendas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormConsultaEncomendas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormConsultaEncomendas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormConsultaEncomendas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormConsultaEncomendas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbtVoltar;
    private javax.swing.JRadioButton jrbFinalizado;
    private javax.swing.JRadioButton jrbProcessamento;
    private javax.swing.JTable jtEncomendas;
    // End of variables declaration//GEN-END:variables
}
