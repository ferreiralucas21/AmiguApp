/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import modelDominio.Produto;
import view.util.Imagem;

/**
 *
 * @author ADMIN
 */
public class FormCadastroProduto extends javax.swing.JDialog {

    // Quando o código for -1 significa CADASTRO NOVO
    // Quando for diferente de -1 é ALTERAÇÃO
    private int codigo = -1;
    
    public FormCadastroProduto(Produto produto) {
        initComponents();
        
        if (produto == null) {
            codigo = -1;
            jbtExcluir.setEnabled(false);
        } else {
            codigo = produto.getIdProduto();
            jtfNome.setText(produto.getNome());
            jtfPreco.setText(Float.toString(produto.getPreco()));
            jtfTamanho.setText(Float.toString(produto.getTamanho()));
            jtfDescricao.setText(produto.getDescricao());
            if (produto.getImagem() != null) {
                Imagem imagem = new Imagem(produto.getImagem());
                jlImagem.setIcon(imagem.getImageIcon());
            }           
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFileChooser1 = new javax.swing.JFileChooser();
        jPanel1 = new javax.swing.JPanel();
        jbtVoltar = new javax.swing.JButton();
        jbtUploadImagem = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jtfNome = new javax.swing.JTextField();
        jtfPreco = new javax.swing.JTextField();
        jtfTamanho = new javax.swing.JTextField();
        jtfDescricao = new javax.swing.JTextField();
        jbtSalvar = new javax.swing.JButton();
        jbtExcluir = new javax.swing.JButton();
        jlImagem = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de Produto");

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));

        jbtVoltar.setText("VOLTAR");
        jbtVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtVoltarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jbtVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jbtVoltar)
                .addContainerGap(39, Short.MAX_VALUE))
        );

        jbtUploadImagem.setText("UPLOAD DE IMAGEM");
        jbtUploadImagem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtUploadImagemActionPerformed(evt);
            }
        });

        jLabel1.setText("Nome");

        jLabel2.setText("Preço");

        jLabel3.setText("Tamanho");

        jLabel4.setText("Descrição");

        jbtSalvar.setText("SALVAR");
        jbtSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtSalvarActionPerformed(evt);
            }
        });

        jbtExcluir.setText("EXCLUIR");
        jbtExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtExcluirActionPerformed(evt);
            }
        });

        jlImagem.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jbtUploadImagem)
                    .addComponent(jlImagem, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(108, 108, 108)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jtfNome)
                            .addComponent(jtfPreco, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(93, 93, 93)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jbtSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jbtExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jtfTamanho)
                                .addComponent(jtfDescricao, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)))))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jtfNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jtfPreco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jtfTamanho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jlImagem, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbtUploadImagem)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(jtfDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtSalvar)
                    .addComponent(jbtExcluir))
                .addGap(0, 59, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jbtSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtSalvarActionPerformed
        Imagem imagem = new Imagem(jFileChooser1.getSelectedFile());
        
        if (!jtfNome.getText().equals("")) {           
            if (!jtfPreco.getText().equals("")) {              
                if (!jtfTamanho.getText().equals("")) {                   
                    if (!jtfDescricao.getText().equals("")) {
                        //if (imagem != null) { // Mesma coisa que nada, if só pra representar que deve haver uma verificação da imagem, que ainda não sei fazer
                        
                            int codVendedor = AmiguAppCliente.ccont.vendedor.getIdVendedor();
                            Produto produto = new Produto(codigo,jtfNome.getText(),Float.parseFloat(jtfPreco.getText()),Float.parseFloat(jtfTamanho.getText()),jtfDescricao.getText(), imagem.getImagem(),codVendedor);
                            System.out.println(produto);
                            
                            String msg;
                            if (codigo == -1) {
                                msg = AmiguAppCliente.ccont.inserirProduto(produto); //chama a conexão controller para ter acesso ao método inserirVendedor
                            } else {
                                produto.setIdProduto(codigo);
                                msg = AmiguAppCliente.ccont.alterarProduto(produto);
                                if (msg.equals("ok")) {
                                    dispose(); // Se deu certo a alteração, a janela pode ser fechada.
                                }
                            }
            
                            //se retornar o ok o vendedor será inserido com sucesso
                            if (msg.equals("ok")) {
                                JOptionPane.showMessageDialog(this, "Produto cadastrado com sucesso!", this.getTitle(), JOptionPane.INFORMATION_MESSAGE);
                                jtfNome.setText("");
                                jtfPreco.setText("");
                                jtfTamanho.setText("");
                                jtfDescricao.setText("");
                                jlImagem.setIcon(null);
                                //imagem = null;
                            } else {
                                JOptionPane.showMessageDialog(this, "Erro ao cadastrar produto!", this.getTitle(), JOptionPane.ERROR_MESSAGE);
                            }
                           
                        //} else {
                            //JOptionPane.showMessageDialog(this, "Preencha o campo de imagem!", this.getTitle(), JOptionPane.ERROR_MESSAGE);
                            //jlImagem.requestFocus();
                       // }    
                        
                    } else {
                       JOptionPane.showMessageDialog(this, "Preencha o campo descrição!", this.getTitle(), JOptionPane.ERROR_MESSAGE);
                       jtfDescricao.requestFocus(); 
                    }    
                } else {
                    JOptionPane.showMessageDialog(this, "Preencha o campo tamanho!", this.getTitle(), JOptionPane.ERROR_MESSAGE);
                    jtfTamanho.requestFocus();
                }       
                 
            } else {
                JOptionPane.showMessageDialog(this, "Preencha o campo preço!", this.getTitle(), JOptionPane.ERROR_MESSAGE);
                jtfPreco.requestFocus();
            } 
            
        } else {
            JOptionPane.showMessageDialog(this, "Preencha o campo nome do produto!", this.getTitle(), JOptionPane.ERROR_MESSAGE);
            jtfNome.requestFocus();
        }
    }//GEN-LAST:event_jbtSalvarActionPerformed

    private void jbtVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtVoltarActionPerformed
        dispose();
    }//GEN-LAST:event_jbtVoltarActionPerformed

    private void jbtExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtExcluirActionPerformed
        int retorno = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja excluir?", this.getTitle(), JOptionPane.YES_NO_OPTION);
        // Se o vendedor respondeu SIM então EXCLUI
        if (retorno == JOptionPane.YES_OPTION) {
            Produto produto = new Produto(codigo);
            String msg = AmiguAppCliente.ccont.excluirProduto(produto);
            if (msg.equals("ok")) {
                JOptionPane.showMessageDialog(this, "Produto excluído com sucesso!");
            } else {
                JOptionPane.showMessageDialog(this, "Não foi possível excluir", this.getTitle(),JOptionPane.ERROR_MESSAGE);
            }
            dispose();
        }
    }//GEN-LAST:event_jbtExcluirActionPerformed

    private void jbtUploadImagemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtUploadImagemActionPerformed
        FileNameExtensionFilter imageFilter = new FileNameExtensionFilter("Image files", ImageIO.getReaderFileSuffixes());
        jFileChooser1.addChoosableFileFilter(imageFilter);
        if (jFileChooser1.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
           Imagem imagem = new Imagem(jFileChooser1.getSelectedFile());
           jlImagem.setIcon(imagem.getImageIcon());
        }
    }//GEN-LAST:event_jbtUploadImagemActionPerformed

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
            java.util.logging.Logger.getLogger(FormCadastroProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormCadastroProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormCadastroProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormCadastroProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormCadastroProduto(null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton jbtExcluir;
    private javax.swing.JButton jbtSalvar;
    private javax.swing.JButton jbtUploadImagem;
    private javax.swing.JButton jbtVoltar;
    private javax.swing.JLabel jlImagem;
    private javax.swing.JTextField jtfDescricao;
    private javax.swing.JTextField jtfNome;
    private javax.swing.JTextField jtfPreco;
    private javax.swing.JTextField jtfTamanho;
    // End of variables declaration//GEN-END:variables
}
