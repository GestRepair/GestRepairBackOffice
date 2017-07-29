/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package users;

import java.io.IOException;
import vehicles.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Convite
 */
public class MainUsers extends javax.swing.JFrame {
    public String log;
    /**
     * Creates new form mainVehicles
     */
    public MainUsers(String login) {
        initComponents();
        log = login;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bt_showUsers = new javax.swing.JButton();
        bt_addUser = new javax.swing.JButton();
        bt_showUsersType = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        bt_showUsers.setText("Lista de  Utilizador");
        bt_showUsers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_showUsersActionPerformed(evt);
            }
        });

        bt_addUser.setText("Adicionar Utilizador");
        bt_addUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_addUserActionPerformed(evt);
            }
        });

        bt_showUsersType.setText("Lista de Utilizadores Por Categoria");
        bt_showUsersType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_showUsersTypeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bt_showUsersType, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bt_showUsers, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bt_addUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(bt_showUsers)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bt_showUsersType)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bt_addUser)
                .addContainerGap(189, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bt_addUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_addUserActionPerformed
        // TODO add your handling code here:
        new AddUser(log).setVisible(true);
    }//GEN-LAST:event_bt_addUserActionPerformed

    private void bt_showUsersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_showUsersActionPerformed
        try {
            new Table_Users(log).setVisible(true);
        } catch (IOException | ParseException ex) {
            Logger.getLogger(MainUsers.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bt_showUsersActionPerformed

    private void bt_showUsersTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_showUsersTypeActionPerformed
        try {
            new Table_Users_Type(log).setVisible(true);
        } catch (IOException | ParseException ex) {
            Logger.getLogger(MainUsers.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bt_showUsersTypeActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_addUser;
    private javax.swing.JButton bt_showUsers;
    private javax.swing.JButton bt_showUsersType;
    // End of variables declaration//GEN-END:variables
}
