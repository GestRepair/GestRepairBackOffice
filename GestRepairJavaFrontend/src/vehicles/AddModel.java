/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vehicles;

import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.xml.bind.DatatypeConverter.parseInt;

/**
 *
 * @author Convite
 */
public final class AddModel extends javax.swing.JFrame {

    public String log;
    SendAPIVehicles sav = new SendAPIVehicles();

    /**
     * Creates new form addBrand
     * @param login
     * @throws java.lang.Exception
     */
    public AddModel(String login) throws Exception {
        log = login;
        initComponents();
        showBrand(sav.Brand(login));
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
            sav.PostModel(log,newIdCb(CB_Brands.getSelectedIndex(),sav.Brand(log)),tf_model.getText());
            dispose();
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
