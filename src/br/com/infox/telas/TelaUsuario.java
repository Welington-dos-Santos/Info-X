/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.infox.telas;

/**
 *
 * @author cti4
 *
 */
import java.sql.*;
import br.com.infox.dal.ModuloConexao;
import javax.swing.JOptionPane;

public class TelaUsuario extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    /**
     * Creates new form TelaUsuario
     */
    public TelaUsuario() {
        initComponents();
        conexao = ModuloConexao.conector();
    }

    //metodo para consultar usuários
    private void consultar() {
        String sql = "select * from tbusuarios where IDUSER = ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, TextId.getText());
            rs = pst.executeQuery();
            if (rs.next()) {
                TextNome.setText(rs.getString(2));
                TextFone.setText(rs.getString(3));
                TextLogin.setText(rs.getString(4));
                TextSenha.setText(rs.getString(5));
                //a linha abaixo se refere ao combobox
                BoxPerm.setSelectedItem(rs.getString(6));
            } else {
                JOptionPane.showMessageDialog(null, "Usuário não cadastrado");
                //as linhas abaixo "limpam" os campos
                TextNome.setText(null);
                TextFone.setText(null);
                TextLogin.setText(null);
                TextSenha.setText(null);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    //método para adicionar usuários
    private void adicionar() {
        String sql = "insert into tbusuarios(IDUSER,USUARIO,FONE,LOGIN,SENHA,perfil) values(?,?,?,?,?,?)";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, TextId.getText());
            pst.setString(2, TextNome.getText());
            pst.setString(3, TextFone.getText());
            pst.setString(4, TextLogin.getText());
            pst.setString(5, TextSenha.getText());
            pst.setString(6, BoxPerm.getSelectedItem().toString());

            if ((TextId.getText().isEmpty())
                    || (TextNome.getText().isEmpty())
                    || (TextLogin.getText().isEmpty())
                    || (TextSenha.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha todos os Campos Obrigatórios");
            } else {
                //a linha abaixo atualiza a tabela usuários com os dados do formulário
                //a estrutura abaixo é usada para confirmar a inserção dos dados na tabela
                int adicionado = pst.executeUpdate();
                //a linha abaixo serve de apoio ao entendimento da lógica
                //System.out.eprintln(adicionado);
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Usuário adicionado com sucesso!");
                    TextId.setText(null);
                    TextNome.setText(null);
                    TextFone.setText(null);
                    TextLogin.setText(null);
                    TextSenha.setText(null);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    //criando o método para alterar os dados do usuário:
    private void alterar() {
        String sql = "update tbusuarios set usuario=?, fone=?, login=?,senha=?,perfil=? where iduser=?";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, TextNome.getText());
            pst.setString(2, TextFone.getText());
            pst.setString(3, TextLogin.getText());
            pst.setString(4, TextSenha.getText());
            pst.setString(5, BoxPerm.getSelectedItem().toString());
            pst.setString(6, TextId.getText());

            if ((TextId.getText().isEmpty())
                    || (TextNome.getText().isEmpty())
                    || (TextLogin.getText().isEmpty())
                    || (TextSenha.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha todos os Campos Obrigatórios");
            } else {
                //a linha abaixo atualiza a tabela usuários com os dados do formulário
                //a estrutura abaixo é usada para alteração dos dados na tabela
                int adicionado = pst.executeUpdate();
                //a linha abaixo serve de apoio ao entendimento da lógica
                //System.out.eprintln(adicionado);
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Dados do usuário alterados com sucesso!");
                    TextId.setText(null);
                    TextNome.setText(null);
                    TextFone.setText(null);
                    TextLogin.setText(null);
                    TextSenha.setText(null);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    //criando o método para deletar o usuário:
    private void remover() {
        //a estrutura abaixo confirma a remoção do usuário
        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover este usuário", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            String sql = "delete from tbusuarios where iduser=?";
            
            try {
                pst=conexao.prepareStatement(sql);
                pst.setString(1, TextId.getText());
                int apagado = pst.executeUpdate();
                
                if(apagado>0){
                    JOptionPane.showMessageDialog(null, "Usuário removido com sucesso!");
                    TextId.setText(null);
                    TextNome.setText(null);
                    TextFone.setText(null);
                    TextLogin.setText(null);
                    TextSenha.setText(null);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        TextId = new javax.swing.JTextField();
        TextNome = new javax.swing.JTextField();
        TextLogin = new javax.swing.JTextField();
        TextSenha = new javax.swing.JTextField();
        BoxPerm = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        TextFone = new javax.swing.JTextField();
        BtnUsuCreate = new javax.swing.JButton();
        BtnUsuRead = new javax.swing.JButton();
        BtnUsuUpdate = new javax.swing.JButton();
        btnUsuDelete = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();

        setClosable(true);
        setForeground(java.awt.Color.black);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Usuários");
        setToolTipText("");
        setPreferredSize(new java.awt.Dimension(640, 480));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("*ID");
        jLabel1.setToolTipText("");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("*Nome");
        jLabel2.setToolTipText("");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("*Login");
        jLabel3.setToolTipText("");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("*Senha");
        jLabel4.setToolTipText("");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("*Perfil");
        jLabel5.setToolTipText("");

        TextLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextLoginActionPerformed(evt);
            }
        });

        TextSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextSenhaActionPerformed(evt);
            }
        });

        BoxPerm.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Admin", "User" }));
        BoxPerm.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                BoxPermComponentHidden(evt);
            }
        });
        BoxPerm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BoxPermActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Fone");
        jLabel6.setToolTipText("");

        BtnUsuCreate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/create.png"))); // NOI18N
        BtnUsuCreate.setToolTipText("Adicionar");
        BtnUsuCreate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnUsuCreate.setPreferredSize(new java.awt.Dimension(80, 80));
        BtnUsuCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnUsuCreateActionPerformed(evt);
            }
        });

        BtnUsuRead.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/read.png"))); // NOI18N
        BtnUsuRead.setToolTipText("Consultar");
        BtnUsuRead.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnUsuRead.setPreferredSize(new java.awt.Dimension(80, 80));
        BtnUsuRead.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnUsuReadActionPerformed(evt);
            }
        });

        BtnUsuUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/update.png"))); // NOI18N
        BtnUsuUpdate.setToolTipText("Alterar");
        BtnUsuUpdate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnUsuUpdate.setPreferredSize(new java.awt.Dimension(80, 80));
        BtnUsuUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnUsuUpdateActionPerformed(evt);
            }
        });

        btnUsuDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/delete.png"))); // NOI18N
        btnUsuDelete.setToolTipText("Remover");
        btnUsuDelete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUsuDelete.setPreferredSize(new java.awt.Dimension(80, 80));
        btnUsuDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuDeleteActionPerformed(evt);
            }
        });

        jLabel7.setText("* Campos Obrigatórios");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel2)
                                .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addGap(3, 3, 3)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(BoxPerm, 0, 114, Short.MAX_VALUE)
                                    .addComponent(TextFone))
                                .addGap(177, 177, 177)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(23, 23, 23)
                                        .addComponent(TextLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(18, 18, 18)
                                        .addComponent(TextSenha))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(TextId, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel7))
                            .addComponent(TextNome)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(BtnUsuCreate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(58, 58, 58)
                        .addComponent(BtnUsuRead, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(58, 58, 58)
                        .addComponent(BtnUsuUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 73, Short.MAX_VALUE)
                        .addComponent(btnUsuDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {BtnUsuCreate, BtnUsuRead, BtnUsuUpdate, btnUsuDelete});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TextNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(TextLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(TextFone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(BoxPerm)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(TextSenha)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(71, 71, 71)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BtnUsuCreate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnUsuRead, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnUsuUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUsuDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(85, Short.MAX_VALUE))
        );

        setBounds(0, 0, 640, 480);
    }// </editor-fold>//GEN-END:initComponents

    private void TextLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextLoginActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextLoginActionPerformed

    private void BoxPermActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BoxPermActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BoxPermActionPerformed

    private void BtnUsuReadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnUsuReadActionPerformed
        // Chamando o método consultar:
        consultar();
    }//GEN-LAST:event_BtnUsuReadActionPerformed

    private void BtnUsuCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnUsuCreateActionPerformed
        // Chamando o método criar:
        adicionar();
    }//GEN-LAST:event_BtnUsuCreateActionPerformed

    private void TextSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextSenhaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextSenhaActionPerformed

    private void BoxPermComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_BoxPermComponentHidden
        // TODO add your handling code here:
    }//GEN-LAST:event_BoxPermComponentHidden

    private void BtnUsuUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnUsuUpdateActionPerformed
        //Chamando o método alterar:
        alterar();
    }//GEN-LAST:event_BtnUsuUpdateActionPerformed

    private void btnUsuDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuDeleteActionPerformed
        //Chamando o método remover:
        remover();
    }//GEN-LAST:event_btnUsuDeleteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> BoxPerm;
    private javax.swing.JButton BtnUsuCreate;
    private javax.swing.JButton BtnUsuRead;
    private javax.swing.JButton BtnUsuUpdate;
    private javax.swing.JTextField TextFone;
    private javax.swing.JTextField TextId;
    private javax.swing.JTextField TextLogin;
    private javax.swing.JTextField TextNome;
    private javax.swing.JTextField TextSenha;
    private javax.swing.JButton btnUsuDelete;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    // End of variables declaration//GEN-END:variables

}
