/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import modelDominio.Produto;
import view.tableModel.ProdutoTableModel;

/**
 *
 * @author ADMIN
 */
public class FormConsultaProdutos extends javax.swing.JDialog {

    private ProdutoTableModel produtoModel;
    
    public FormConsultaProdutos() {
        initComponents();
        atualizaTabela();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jbtVoltar = new javax.swing.JButton();
        jtfFiltro = new javax.swing.JTextField();
        jcbFiltro = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtProdutos = new javax.swing.JTable();
        jbtCadastrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Produtos");

        jPanel1.setBackground(new java.awt.Color(226, 102, 63));

        jbtVoltar.setBackground(new java.awt.Color(226, 102, 63));
        jbtVoltar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagem/icons8-voltar-24 (1).png"))); // NOI18N
        jbtVoltar.setBorder(null);
        jbtVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtVoltarActionPerformed(evt);
            }
        });

        jtfFiltro.setBackground(new java.awt.Color(250, 218, 218));
        jtfFiltro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfFiltroKeyReleased(evt);
            }
        });

        jcbFiltro.setBackground(new java.awt.Color(250, 218, 218));
        jcbFiltro.setFont(new java.awt.Font("Source Sans Pro Light", 0, 12)); // NOI18N
        jcbFiltro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nenhum", "Nome" }));
        jcbFiltro.setBorder(null);
        jcbFiltro.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcbFiltroItemStateChanged(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Source Sans Pro Light", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Filtro:");

        jButton1.setBackground(new java.awt.Color(226, 102, 63));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagem/icons8-usuário-24 (1).png"))); // NOI18N
        jButton1.setBorder(null);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(226, 102, 63));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagem/icons8-carrinho-de-mão-de-entrega-24 (1).png"))); // NOI18N
        jButton2.setBorder(null);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(89, 89, 89)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jcbFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jtfFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addGap(8, 8, 8)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jbtVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(jbtVoltar)
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Source Sans Pro Light", 1, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(226, 102, 63));
        jLabel1.setText("Meus Produtos");

        jtProdutos.setBackground(new java.awt.Color(250, 218, 218));
        jtProdutos.setModel(new javax.swing.table.DefaultTableModel(
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
        jtProdutos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtProdutosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtProdutos);

        jbtCadastrar.setBackground(new java.awt.Color(226, 102, 63));
        jbtCadastrar.setFont(new java.awt.Font("Source Sans Pro Light", 1, 14)); // NOI18N
        jbtCadastrar.setForeground(new java.awt.Color(255, 255, 255));
        jbtCadastrar.setText("Adicionar novo produto");
        jbtCadastrar.setBorder(null);
        jbtCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtCadastrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(268, 268, 268)
                        .addComponent(jLabel1))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(89, 89, 89)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 461, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(215, 215, 215)
                        .addComponent(jbtCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(95, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(jLabel1)
                .addGap(30, 30, 30)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(jbtCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(50, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jbtCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtCadastrarActionPerformed
       FormCadastroProduto formCadastroProduto = new FormCadastroProduto(null);
       formCadastroProduto.setModal(true);
       formCadastroProduto.setVisible(true);
       atualizaTabela();
    }//GEN-LAST:event_jbtCadastrarActionPerformed

    private void jbtVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtVoltarActionPerformed
        dispose();
    }//GEN-LAST:event_jbtVoltarActionPerformed

    private void jtProdutosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtProdutosMouseClicked
        //Criando o formulário de cadastro passando o Produto selecionado como parâmetro
        //O formulário de cadastro recebe uma Raça como parâmetro, quando for para editar 
        // um registro. Note que o método getProduto foi implementado anteriormente no 
        // TableModelo
        Produto produto = produtoModel.getProduto(jtProdutos.getSelectedRow());
        FormCadastroProduto formCadastroProduto = new FormCadastroProduto(produto);
        // abrindo formulario modal (Só é possível quando o formulário for do tipo JDialog
        formCadastroProduto.setModal(true);
        formCadastroProduto.setVisible(true);
        atualizaTabela();
    }//GEN-LAST:event_jtProdutosMouseClicked

    private void jtfFiltroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfFiltroKeyReleased
        atualizaTabela();
    }//GEN-LAST:event_jtfFiltroKeyReleased

    private void jcbFiltroItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcbFiltroItemStateChanged
        atualizaTabela();
    }//GEN-LAST:event_jcbFiltroItemStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        FormPerfilVendedor formPerfilVendedor = new FormPerfilVendedor(null);
        formPerfilVendedor.setModal(true);
        formPerfilVendedor.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    public void atualizaTabela() {
        if (jcbFiltro.getSelectedIndex() == 0) {
            produtoModel = new ProdutoTableModel(AmiguAppCliente.ccont.produtoLista());
        } else {
            produtoModel = new ProdutoTableModel(AmiguAppCliente.ccont.produtoListaNome(jtfFiltro.getText()));
        }
        
        jtProdutos.setModel(produtoModel);              
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
            java.util.logging.Logger.getLogger(FormConsultaProdutos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormConsultaProdutos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormConsultaProdutos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormConsultaProdutos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormConsultaProdutos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbtCadastrar;
    private javax.swing.JButton jbtVoltar;
    private javax.swing.JComboBox<String> jcbFiltro;
    private javax.swing.JTable jtProdutos;
    private javax.swing.JTextField jtfFiltro;
    // End of variables declaration//GEN-END:variables
}
