/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package users;

import java.awt.Toolkit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Rui Barcelos
 */
public class EditPassword extends javax.swing.JFrame {

    private String login, password;
    private int id;
    APIUsers api = new APIUsers();

    /**
     * Creates new form EditPassword
     *
     * @param login
     * @throws java.lang.Exception
     */
    public EditPassword(String login, int id) throws Exception {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("../img/imageedit_4_8303763918.png")));
        initComponents();
        JSONObject auth = (JSONObject) new JSONParser().parse(login);
        this.password = (String) auth.get("password");
        this.login = login;
        this.id = id;
    }

    public void VerifyPass(String login, String password) throws Exception {
        int x = JOptionPane.showConfirmDialog(this, "Tem a certeza que quer alterar a sua password?", "GestRepair", JOptionPane.YES_NO_OPTION);
        if (x == JOptionPane.YES_OPTION) {
            if (this.password.equals(tf_pass_act.getText())) {
                if (!tf_npass.getText().equals(this.password) || !tf_npass.getText().equals(this.password)) {
                    if (tf_npass.getText().equals(tf_cpass.getText())) {
                        if ("ok".equals(api.PutPassword(login, id, password, tf_npass.getText()))) {
                            JOptionPane.showMessageDialog(this, "A Password foi alterada com sucesso\nO sistema irá desligar!");
                            System.exit(0);
                        } else {
                            JOptionPane.showMessageDialog(this, "A Password não foi alterada");
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "A nova password não coicide com a de confirmação");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "A nova password é igual à actual");
                }
            } else {
                JOptionPane.showMessageDialog(this, "A Password atual é não é igual à do sistema");
            }
        } else if (x == JOptionPane.NO_OPTION) {
            JOptionPane.showMessageDialog(this, "A Password não foi alterada");
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

        tf_pass_act = new javax.swing.JPasswordField();
        tf_npass = new javax.swing.JPasswordField();
        tf_cpass = new javax.swing.JPasswordField();
        l_pass_act = new javax.swing.JLabel();
        l_npass = new javax.swing.JLabel();
        l_cpass = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("GestRepair - Alterar Password");

        l_pass_act.setText("Password Atual:");

        l_npass.setText("Nova Password:");

        l_cpass.setText("Confirmação Password:");

        jButton1.setText("Alterar Password");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(l_pass_act)
                    .addComponent(l_npass)
                    .addComponent(l_cpass))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                    .addComponent(tf_cpass)
                    .addComponent(tf_npass)
                    .addComponent(tf_pass_act))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(l_pass_act)
                    .addComponent(tf_pass_act, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_npass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(l_npass))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_cpass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(l_cpass))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            VerifyPass(this.login, this.password);
        } catch (Exception ex) {
            Logger.getLogger(EditPassword.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel l_cpass;
    private javax.swing.JLabel l_npass;
    private javax.swing.JLabel l_pass_act;
    private javax.swing.JPasswordField tf_cpass;
    private javax.swing.JPasswordField tf_npass;
    private javax.swing.JPasswordField tf_pass_act;
    // End of variables declaration//GEN-END:variables
}
