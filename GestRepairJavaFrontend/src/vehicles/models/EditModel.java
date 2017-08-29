/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vehicles.models;

import java.awt.Toolkit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static javax.xml.bind.DatatypeConverter.parseInt;

/**
 *
 * @author Rui Barcelos
 */
public final class EditModel extends javax.swing.JFrame {

    APIModel api = new APIModel();
    private final String login;

    /**
     * Creates new form ChangeModel
     *
     * @param login
     * @param id
     * @param brand
     * @throws java.lang.Exception
     */
    public EditModel(String login, int id, String brand) throws Exception {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("../../img/imageedit_4_8303763918.png")));
        initComponents();
        l_brand.setText(brand);
        InfoModel(login, id);
        this.login = login;
    }

    private void InfoModel(String login, int id) throws Exception {
        String model[] = api.InfoModel(login, id);
        l_id.setText(model[0]);
        tf_name.setText(model[1]);
    }

    private void ChangeModel(String login, int id, String name) throws Exception {
        String modelName =api.InfoModel(login, id)[1];
        int x = JOptionPane.showConfirmDialog(this, "Quer modificar a marca " + modelName + " para " + tf_name.getText() + "?", "Confirmação", JOptionPane.YES_NO_OPTION);
        if (x == JOptionPane.YES_OPTION) {
            String[] value = api.PutModel(login, id, name);
            JOptionPane.showMessageDialog(this, value[1]);
            if ("ok".equals(value[0])) {
                new Table_Model(login).setVisible(true);
                dispose();
            }
        } else if (x == JOptionPane.NO_OPTION) {
            JOptionPane.showMessageDialog(this, "A Marca " + modelName + " não foi modificada");
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
        l_id = new javax.swing.JLabel();
        tf_name = new javax.swing.JTextField();
        bt_edit = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        l_brand = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("GestRepair - Editar Modelo");

        jLabel1.setText("ID:");

        jLabel2.setText("Modelo:");

        l_id.setText("id");

        bt_edit.setText("Editar");
        bt_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_editActionPerformed(evt);
            }
        });

        jLabel3.setText("Marca:");

        l_brand.setText("jLabel4");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(bt_edit))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(l_id)
                                    .addComponent(tf_name, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(l_brand)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(l_id))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(l_brand))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tf_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bt_edit)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bt_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_editActionPerformed
        try {
            ChangeModel(this.login, parseInt(l_id.getText()), tf_name.getText());
        } catch (Exception ex) {
            Logger.getLogger(EditModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bt_editActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_edit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel l_brand;
    private javax.swing.JLabel l_id;
    private javax.swing.JTextField tf_name;
    // End of variables declaration//GEN-END:variables
}
