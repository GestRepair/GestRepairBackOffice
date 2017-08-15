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
import vehicles.brands.APIBrand;

/**
 *
 * @author Convite
 */
public final class AddModel extends javax.swing.JFrame {

    public String login;
    APIModel api = new APIModel();
    APIBrand apiBrand = new APIBrand();
    /**
     * Creates new form addBrand
     * @param login
     * @throws java.lang.Exception
     */
    public AddModel(String login) throws Exception {
        this.login = login;
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("../../img/imageedit_4_8303763918.png")));
        initComponents();
        showBrand(apiBrand.Brand(login));
    }
    public void showBrand(String[][] list) {
        CB_Brands.removeAllItems();
        for (int i = 0; i < list.length; i++) {
            CB_Brands.addItem(list[i][1]);
        }
    }
    public int newIdCb(int val,String[][] list) {
        if (val == 0){
            return 1;
        }else{
            return parseInt(list[val][0]);
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

        tf_model = new javax.swing.JTextField();
        bt_addModel = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        CB_Brands = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("GestRepair - Adicionar Modelo");

        bt_addModel.setText("Adicionar Modelo");
        bt_addModel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_addModelActionPerformed(evt);
            }
        });

        jLabel1.setText("Modelo");

        CB_Brands.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(bt_addModel))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tf_model, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                            .addComponent(jLabel1)
                            .addComponent(CB_Brands, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CB_Brands, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tf_model, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bt_addModel)
                .addGap(23, 23, 23))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bt_addModelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_addModelActionPerformed
        try {
            int x = JOptionPane.showConfirmDialog(this, "Tem a certeza que quer adicionar uma nova viatura?", "Confirmação", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                if ("ok".equals(api.PostModel(this.login,newIdCb(CB_Brands.getSelectedIndex(),apiBrand.Brand(this.login)),tf_model.getText()))) {
                    JOptionPane.showMessageDialog(this, "Viatura Adicionada com sucesso");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Erro Interno");
                }
            } else if (x == JOptionPane.NO_OPTION) {
                JOptionPane.showMessageDialog(this, "A Viatura não foi adicionada!");
            }
        } catch (Exception ex) {
            Logger.getLogger(AddModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bt_addModelActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox CB_Brands;
    private javax.swing.JButton bt_addModel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField tf_model;
    // End of variables declaration//GEN-END:variables
}
