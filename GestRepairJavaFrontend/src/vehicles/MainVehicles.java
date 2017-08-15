/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vehicles;

import vehicles.vehicles.Table_Vehicles;
import vehicles.models.Table_Model;
import vehicles.models.AddModel;
import vehicles.brands.AddBrand;
import vehicles.brands.Table_Brand;
import java.awt.Toolkit;
import java.util.logging.Level;
import java.util.logging.Logger;
import vehicles.fuel.AddFuel;
import vehicles.fuel.Table_Fuel;

/**
 *
 * @author Convite
 */
public class MainVehicles extends javax.swing.JFrame {
    public String login;
    /**
     * Creates new form mainVehicles
     * @param login
     */
    public MainVehicles(String login) {
        initComponents();
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("../img/imageedit_4_8303763918.png")));
        this.login = login;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bt_showvehicles = new javax.swing.JButton();
        bt_model = new javax.swing.JButton();
        bt_addBrand = new javax.swing.JButton();
        bt_lBrand = new javax.swing.JButton();
        bt_lmodel = new javax.swing.JButton();
        bt_fuel_list = new javax.swing.JButton();
        bt_fuel_add = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("GestRepair - Menu Viaturas ");
        setResizable(false);

        bt_showvehicles.setText("Lista de Viaturas");
        bt_showvehicles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_showvehiclesActionPerformed(evt);
            }
        });

        bt_model.setText("Adicionar Modelo");
        bt_model.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_modelActionPerformed(evt);
            }
        });

        bt_addBrand.setText("Adicionar Marca");
        bt_addBrand.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_addBrandActionPerformed(evt);
            }
        });

        bt_lBrand.setText("Lista de Marcas");
        bt_lBrand.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_lBrandActionPerformed(evt);
            }
        });

        bt_lmodel.setText("Lista de Modelos");
        bt_lmodel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_lmodelActionPerformed(evt);
            }
        });

        bt_fuel_list.setText("Lista de Combustíveis");
        bt_fuel_list.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_fuel_listActionPerformed(evt);
            }
        });

        bt_fuel_add.setText("Adicionar Combustível");
        bt_fuel_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_fuel_addActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bt_lmodel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bt_fuel_list, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(bt_showvehicles, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(bt_model, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(bt_addBrand, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(bt_lBrand, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(bt_fuel_add, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(bt_showvehicles)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bt_lBrand)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bt_lmodel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bt_fuel_list)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bt_addBrand)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bt_model)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bt_fuel_add)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bt_addBrandActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_addBrandActionPerformed
        // TODO add your handling code here:
        new AddBrand(this.login).setVisible(true);
    }//GEN-LAST:event_bt_addBrandActionPerformed

    private void bt_modelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_modelActionPerformed
        try {
            new AddModel(this.login).setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(MainVehicles.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bt_modelActionPerformed

    private void bt_showvehiclesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_showvehiclesActionPerformed
        try {
            new Table_Vehicles(this.login).setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(MainVehicles.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bt_showvehiclesActionPerformed

    private void bt_lBrandActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_lBrandActionPerformed
        try {
            new Table_Brand(this.login).setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(MainVehicles.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bt_lBrandActionPerformed

    private void bt_lmodelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_lmodelActionPerformed
        try {
            new Table_Model(this.login).setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(MainVehicles.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bt_lmodelActionPerformed

    private void bt_fuel_listActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_fuel_listActionPerformed
        try {
            new Table_Fuel(this.login).setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(MainVehicles.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bt_fuel_listActionPerformed

    private void bt_fuel_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_fuel_addActionPerformed
        try {
            new AddFuel(this.login).setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(MainVehicles.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bt_fuel_addActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_addBrand;
    private javax.swing.JButton bt_fuel_add;
    private javax.swing.JButton bt_fuel_list;
    private javax.swing.JButton bt_lBrand;
    private javax.swing.JButton bt_lmodel;
    private javax.swing.JButton bt_model;
    private javax.swing.JButton bt_showvehicles;
    // End of variables declaration//GEN-END:variables
}
